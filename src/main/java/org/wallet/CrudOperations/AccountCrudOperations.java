package org.wallet.CrudOperations;

import org.wallet.Components.AccountComponent;
import org.wallet.Components.BalanceComponent;
import org.wallet.Models.Account;
import org.wallet.Models.Balance;
import org.wallet.Models.TranferHistory;
import org.wallet.Models.Transaction;
import org.wallet.connectionDB.ConnectionDB;

import java.sql.*;
import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;
import java.util.Objects;

public class AccountCrudOperations implements CrudOperations<Account> {
    private static final TransactionCrudOperations transactionCrud = new TransactionCrudOperations();
    private static final TransferHistoryCrudOperations transferHistoryCrud = new TransferHistoryCrudOperations();
    public static final String ACCOUNT_ID_COLUMN = "account_id";
    public static final String CURRENCY_ID_COLUMN = "currency_id";
    public static final String ACCOUNT_TYPE_COLUMN = "account_type";
    @Override
    public List<Account> findAll() {
        List<Account> accounts = new ArrayList<>();
        Connection connection = ConnectionDB.getConnection();
        try {
            String sql = "SELECT * FROM \"account\"";
            Statement statement = connection.createStatement();

            ResultSet resultSet = statement.executeQuery(sql);

            while(resultSet.next()){
                Account account = mapResultSet(resultSet);

                accounts.add(account);
            }

            resultSet.close();
            statement.close();
            connection.close();
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
        return accounts;
    }

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
    public Account save(Account toSave) {
        Account savedAccount;
        Connection connection = ConnectionDB.getConnection();

        String sql;

        if(toSave.getAccountId() == null){
            sql = "INSERT INTO \"account\" (account_type, currency_id) " +
                    "VALUES(CAST(? AS account_type), ?) RETURNING *";
        }else {
            sql = "UPDATE \"account\" " +
                    "SET account_type = CAST(? AS account_type), currency_id = ? " +
                    "WHERE account_id = ? RETURNING *";
        }

        try {
            PreparedStatement statement = connection.prepareStatement(sql);

            statement.setString(1, toSave.getAccountType());
            statement.setString(2, toSave.getCurrencyId());

            if(toSave.getAccountId() != null){
                statement.setString(3, toSave.getAccountId());
            }

            statement.execute();

            ResultSet resultSet = statement.getResultSet();
            resultSet.next();
            savedAccount = mapResultSet(resultSet);

            statement.close();
            connection.close();
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }


        return savedAccount;
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

        if(accountBalance == null){
            balanceCRUD.save(
                    new Balance(
                            LocalDateTime.now(),
                            transaction.getTransactionId(),
                            0.0
                    )
            );
            if(Objects.equals(transaction.getTransactionType(), "income")){
                Transaction saved = transactionCRUD.save(transaction);
                accountComponent = this.getAccountById(saved.getAccountId());
            }
        }else {
            if(Objects.equals(transaction.getTransactionType(), "expense")){
                if(accountBalance.getAmount() >= transaction.getAmount()){
                    balanceCRUD.save(
                        new Balance(
                                LocalDateTime.now(),
                                transaction.getTransactionId(),
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
                                transaction.getTransactionId(),
                                accountBalance.getAmount() + transaction.getAmount()
                        )
                );
                if(Objects.equals(transaction.getTransactionType(), "income")){
                    transactionCRUD.save(transaction);
                }
            }
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
                transactionCRUD.getTransactionByAccountId(account.getAccountId()),
                account.getAccountType()
        );
    }

    public TranferHistory makeTransfer(String debitAccount, String creditAccount, Double amount){
        if(!debitAccount.equals(creditAccount)){
            Connection connection = ConnectionDB.getConnection();
            Transaction debitTransaction = Transaction.builder()
                    .description("Transfer of "+amount+" to "+creditAccount)
                    .amount(amount)
                    .transactionType("expense")
                    .accountId(debitAccount)
                    .build();

            Transaction creditTransaction = Transaction.builder()
                    .description("Transfer of "+amount+" from "+debitAccount)
                    .amount(amount)
                    .transactionType("income")
                    .accountId(debitAccount)
                    .build();

            Transaction savedDebit = transactionCrud.save(debitTransaction);
            Transaction savedCredit = transactionCrud.save(creditTransaction);

            return transferHistoryCrud.save(
                    TranferHistory.builder()
                            .debitTransactionId(savedDebit.getTransactionId())
                            .creditTransactionId(savedCredit.getTransactionId())
                            .build()
            );
        }else {
            return null;
        }
    }

    private Account mapResultSet(ResultSet resultSet) throws SQLException {
        Account account = new Account();
        account.setAccountId(resultSet.getString(ACCOUNT_ID_COLUMN));
        account.setCurrencyId(resultSet.getString(CURRENCY_ID_COLUMN));
        account.setAccountType(resultSet.getString(ACCOUNT_TYPE_COLUMN));

        return account;
    }
}
