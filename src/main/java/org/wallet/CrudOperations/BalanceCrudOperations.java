package org.wallet.CrudOperations;

import org.wallet.Components.TransactionComponent;
import org.wallet.Models.Account;
import org.wallet.Models.Balance;
import org.wallet.Models.TranferHistory;
import org.wallet.Models.Transaction;
import org.wallet.Models.Types.TransactionType;
import org.wallet.connectionDB.ConnectionDB;

import java.sql.*;
import java.time.LocalDate;
import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;
import java.util.Objects;
import java.util.stream.Collectors;

public class BalanceCrudOperations implements CrudOperations<Balance> {
    private static final AccountCrudOperations accountCrud = new AccountCrudOperations();
                         TransferHistoryCrudOperations transferHistoryCrud = new TransferHistoryCrudOperations();
                         CurrencyCrudOperations currencyCrud = new CurrencyCrudOperations();
                         TransactionCrudOperations transactionCrud = new TransactionCrudOperations();
                         CurrencyValueCrudOperations currencyValueCrud = new CurrencyValueCrudOperations();
    public static final String BALANCE_ID_COLUMN = "balance_id";
    public static final String TIMESTAMP_COLUMN = "balance_timestamp";
    public static final String ACCOUNT_ID_COLUMN = "account_id";
    public static final String AMOUNT_COLUMN = "amount";

    @Override
    public List<Balance> findAll() {
        List<Balance> balances = new ArrayList<>();
        Connection connection = ConnectionDB.getConnection();
        try {
            String sql = "SELECT * FROM \"balance\"";
            Statement statement = connection.createStatement();

            ResultSet resultSet = statement.executeQuery(sql);

            while (resultSet.next()) {
                Balance balance = this.mapResultSet(resultSet);
                balances.add(balance);
            }

            resultSet.close();
            statement.close();
            connection.close();
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
        return balances;
    }

    @Override
    public List<Balance> saveAll(List<Balance> toSave) {
        List<Balance> savedBalances = new ArrayList<>();

        for (Balance balance : toSave) {
            Balance savedBalance = this.save(balance);
            savedBalances.add(savedBalance);
        }

        return savedBalances;
    }

    @Override
    public Balance save(Balance toSave) {
        Balance savedBalance;
        Connection connection = ConnectionDB.getConnection();

        String sql;

        if (toSave.getBalanceId() == null) {
            sql = "INSERT INTO \"balance\" (balance_timestamp, account_id, amount) " +
                    "VALUES(?, ?, ?) RETURNING *";
        } else {
            sql = "UPDATE \"balance\" " +
                    "SET balance_timestamp = ?, account_id = ?, amount = ? " +
                    "WHERE balance_id = ? RETURNING *";
        }

        try {
            PreparedStatement statement = connection.prepareStatement(sql);

            statement.setTimestamp(1, Timestamp.valueOf(toSave.getBalanceTimestamp()));
            statement.setString(2, toSave.getAccountId());
            statement.setDouble(3, toSave.getAmount());

            if (toSave.getBalanceId() != null) {
                statement.setString(3, toSave.getBalanceId());
            }

            statement.execute();

            ResultSet resultSet = statement.getResultSet();
            resultSet.next();
            savedBalance = mapResultSet(resultSet);

            statement.close();
            connection.close();
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }

        return savedBalance;
    }

    @Override
    public Balance delete(Balance toDelete) {
        Balance deletedBalance;
        Connection connection = ConnectionDB.getConnection();

        try {
            String sql = "DELETE FROM \"balance\" WHERE balance_id = ? RETURNING *";
            PreparedStatement statement = connection.prepareStatement(sql);

            statement.setString(1, toDelete.getBalanceId());

            statement.execute();

            ResultSet resultSet = statement.getResultSet();
            resultSet.next();
            deletedBalance = mapResultSet(resultSet);

            statement.close();
            connection.close();
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }

        return deletedBalance;
    }

    public Balance getLastBalanceOfAccount(String accountId) {
        Balance balance;
        Connection connection = ConnectionDB.getConnection();

        String sql = "SELECT * FROM \"balance\" WHERE account_id = ? ORDER BY balance_timestamp DESC LIMIT 1";

        try {
            PreparedStatement statement = connection.prepareStatement(sql);

            statement.setString(1, accountId);

            ResultSet resultSet = statement.executeQuery();

            if (resultSet.next()) {
                balance = mapResultSet(resultSet);
            } else {
                balance = null;
            }

            statement.close();
            connection.close();
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }

        return balance;
    }

    public double getAmountByDate(String accountId, LocalDateTime amountUpdated) {
        List<TransactionComponent> transactions = transactionCrud.getTransactionByAccountId(accountId, amountUpdated);
        double balance = 0.0;

        List<TransactionComponent> mappedTransactions = transactions.stream().map(
          (transaction) -> {
              if(transaction.getTransactionType().equals(TransactionType.EXPENSE.toString())){
                  return TransactionComponent.builder()
                          .transactionId(transaction.getTransactionId())
                          .amount(-transaction.getAmount())
                          .description(transaction.getDescription())
                          .transactionDate(transaction.getTransactionDate())
                          .transactionType(transaction.getTransactionType())
                          .categoryId(transaction.getCategoryId())
                          .build();
              }else {
                  return transaction;
              }
          }
        ).toList();

        for (TransactionComponent mappedTransaction : mappedTransactions) {
            balance += mappedTransaction.getAmount();
        }

        return balance;
    }

    public double getAmountWithChangeRate(String accountId, LocalDateTime amountUpdated){
        List<TransactionComponent> transactions = transactionCrud.getTransactionByAccountId(accountId, amountUpdated);
        double balance = 0.0;

        List<TransactionComponent> incomes = transactions.stream().filter(
                transaction -> transaction.getTransactionType().equals(TransactionType.INCOME.toString())
        ).toList();
        List<TransactionComponent> expenses = transactions.stream().filter(
                transaction -> transaction.getTransactionType().equals(TransactionType.EXPENSE.toString())
        ).toList();


        for (TransactionComponent income : incomes) {
            TranferHistory transfer = transferHistoryCrud.getByCreditTransaction(income.getTransactionId(), income.getTransactionDate());
            if(transfer != null){
                String currency = currencyCrud.getCurrencyById(
                    accountCrud.getAccountById(
                            transactionCrud.getById(
                                    transfer.getDebitTransactionId()
                            ).getAccountId()
                    ).getCurrency().getCurrencyId()
                ).getCode();

                if(Objects.equals(currency, "EUR")){
                    double changeRate = currencyValueCrud.getChangeRate(income.getTransactionDate().toLocalDate());
                    balance += income.getAmount() * changeRate;
                }else {
                    balance += income.getAmount();
                }
            }else {
                balance += income.getAmount();
            }
        }

        for (TransactionComponent expense : expenses) {
            balance += expense.getAmount();
        }

        return balance;
    }

        public List<Balance> getBalanceHistory(String accountId, LocalDateTime startDate, LocalDateTime endDate) {
            List<Balance> balanceHistory = new ArrayList<>();
            Connection connection = ConnectionDB.getConnection();

            String sql = "SELECT * FROM \"balance\" WHERE account_id = ? " +
                    "AND balance_timestamp BETWEEN ? AND ? ORDER BY balance_timestamp";

            try {
                PreparedStatement statement = connection.prepareStatement(sql);
                statement.setString(1, accountId);
                statement.setTimestamp(2, Timestamp.valueOf(startDate));
                statement.setTimestamp(3, Timestamp.valueOf(endDate));

                ResultSet resultSet = statement.executeQuery();

                while (resultSet.next()) {
                    Balance balance = mapResultSet(resultSet);
                    balanceHistory.add(balance);
                }

                statement.close();
                connection.close();
            } catch (SQLException e) {
                throw new RuntimeException(e);
            }

            return balanceHistory;
        }


    private Balance mapResultSet(ResultSet resultSet) throws SQLException {
        Balance balance = new Balance();
            balance.setBalanceId(resultSet.getString(BALANCE_ID_COLUMN));
            balance.setBalanceTimestamp(resultSet.getTimestamp(TIMESTAMP_COLUMN).toLocalDateTime());
            balance.setAccountId(resultSet.getString(ACCOUNT_ID_COLUMN));
            balance.setAmount(resultSet.getDouble(AMOUNT_COLUMN));
        return balance;
    }
}
