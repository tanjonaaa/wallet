package org.wallet.CrudOperations;

import org.springframework.stereotype.Repository;
import org.wallet.Components.AccountComponent;
import org.wallet.Components.BalanceComponent;
import org.wallet.Models.Account;
import org.wallet.Models.Balance;
import org.wallet.Models.TransferHistory;
import org.wallet.Models.Transaction;
import org.wallet.Models.Types.TransactionType;
import org.wallet.ConnectionDB.ConnectionDB;

import java.sql.*;
import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

@Repository
public class AccountCrudOperations extends AutoCrudOperations<Account> {
    private static final TransactionCrudOperations transactionCrud = new TransactionCrudOperations();
    private static final TransferHistoryCrudOperations transferHistoryCrud = new TransferHistoryCrudOperations();
    private static final CategoryCrudOperations categoryCrud = new CategoryCrudOperations();
    private static final BalanceCrudOperations balanceCrud = new BalanceCrudOperations();

    @Override
    public List<Account> saveAll(List<Account> toSave) {
        List<Account> savedAccounts = new ArrayList<>();

        for (Account account : toSave) {
            Account savedAccount = this.save(account);
            savedAccounts.add(savedAccount);
        }

        return savedAccounts;
    }

    @Override
    public Account delete(Account toDelete) {
        Account deletedAccount;
        Connection connection = ConnectionDB.getConnection();

        try {
            String sql = "DELETE FROM \"account\" WHERE account_id = ? RETURNING *";
            PreparedStatement statement = connection.prepareStatement(sql);

            statement.setString(1, toDelete.getAccountId());

            statement.execute();

            ResultSet resultSet = statement.getResultSet();
            resultSet.next();
            deletedAccount = mapResultSet(resultSet);

            statement.close();
            connection.close();
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }

        return deletedAccount;
    }

    public AccountComponent makeTransaction(Transaction transaction){
        AccountComponent accountComponent = null;
        BalanceCrudOperations balanceCRUD = new BalanceCrudOperations();
        TransactionCrudOperations transactionCRUD = new TransactionCrudOperations();

        Balance accountBalance = balanceCRUD.getLastBalanceOfAccount(transaction.getAccountId());

            if(transaction.getTransactionType() == TransactionType.EXPENSE){
                if(accountBalance.getAmount() >= transaction.getAmount()){
                    balanceCRUD.save(
                        new Balance(
                                LocalDateTime.now(),
                                transaction.getAccountId(),
                                accountBalance.getAmount() - transaction.getAmount()
                        )
                    );
                    Transaction saved = transactionCRUD.save(transaction);
                    accountComponent = this.getAccountById(saved.getAccountId());
                }
            }else {
                balanceCRUD.save(
                        new Balance(
                                LocalDateTime.now(),
                                transaction.getAccountId(),
                                accountBalance.getAmount() + transaction.getAmount()
                        )
                );
                Transaction saved = transactionCRUD.save(transaction);
                accountComponent = this.getAccountById(saved.getAccountId());
            }
        return accountComponent;
    }

    public AccountComponent getAccountById(String id){
        Account account = new Account();
        BalanceCrudOperations balanceCrud = new BalanceCrudOperations();        
        CurrencyCrudOperations currencyCRUD = new CurrencyCrudOperations();
        TransactionCrudOperations transactionCRUD = new TransactionCrudOperations();
        
        Connection connection = ConnectionDB.getConnection();

        String sql = "SELECT * FROM \"account\" WHERE account_id = ?";

        try {
            PreparedStatement statement = connection.prepareStatement(sql);

            statement.setString(1, id);

            ResultSet resultSet = statement.executeQuery();

            if(resultSet.next()){
                account = mapResultSet(resultSet);
            }

            statement.close();
            connection.close();
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }

        BalanceComponent accountBalance = new BalanceComponent(
                balanceCrud.getLastBalanceOfAccount(account.getAccountId()).getAmount(),
                balanceCrud.getLastBalanceOfAccount(account.getAccountId()).getBalanceTimestamp()
        );

        return new AccountComponent(
                account.getAccountId(),
                account.getName(),
                accountBalance,
                currencyCRUD.getCurrencyById(account.getCurrencyId()),
                transactionCRUD.getTransactionByAccountId(account.getAccountId(), null),
                account.getAccountType().toString()
        );
    }

    public TransferHistory makeTransfer(String debitAccount, String creditAccount, Double amount){
        List<String> ids = Arrays.asList(
                categoryCrud.getCategoryId("Transfer", TransactionType.INCOME),
                categoryCrud.getCategoryId("Transfer", TransactionType.EXPENSE)
        );

        if(!debitAccount.equals(creditAccount)){
            Transaction debitTransaction = Transaction.builder()
                    .description("Transfer of "+amount+" to "+creditAccount)
                    .amount(amount)
                    .transactionType(TransactionType.EXPENSE)
                    .accountId(debitAccount)
                    .categoryId(ids.get(1))
                    .build();

            Transaction creditTransaction = Transaction.builder()
                    .description("Transfer of "+amount+" from "+debitAccount)
                    .amount(amount)
                    .transactionType(TransactionType.INCOME)
                    .accountId(creditAccount)
                    .categoryId(ids.get(0))
                    .build();

            Transaction savedDebit = transactionCrud.save(debitTransaction);
            Transaction savedCredit = transactionCrud.save(creditTransaction);

            return transferHistoryCrud.save(
                    TransferHistory.builder()
                            .debitTransactionId(savedDebit.getTransactionId())
                            .creditTransactionId(savedCredit.getTransactionId())
                            .build()
            );
        }else {
            return null;
        }
    }

    private Double getExchangeRate(LocalDateTime transactionTimestamp, String calculationType) {
        Connection connection = ConnectionDB.getConnection();
        String sql;

        switch (calculationType) {
            case "average":
                sql = "SELECT AVG(change_rate) FROM currency_value WHERE date_trunc('day', currency_value_date) = ?";
                break;
            case "minimum":
                sql = "SELECT MIN(change_rate) FROM currency_value WHERE date_trunc('day', currency_value_date) = ?";
                break;
            case "maximum":
                sql = "SELECT MAX(change_rate) FROM currency_value WHERE date_trunc('day', currency_value_date) = ?";
                break;
            case "median":
                sql = "SELECT percentile_cont(0.5) WITHIN GROUP (ORDER BY change_rate) FROM" +
                        " currency_value WHERE date_trunc('day', currency_value_date) = ?";
                break;
            default:
                throw new IllegalArgumentException("Invalid calculation type");
        }

        try {
            PreparedStatement statement = connection.prepareStatement(sql);

            statement.setObject(1, transactionTimestamp);

            ResultSet resultSet = statement.executeQuery();

            if (resultSet.next()) {
                return resultSet.getDouble(1);
            }

            statement.close();
            connection.close();
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }

        return 1.0;
    }
}
