package org.wallet.CrudOperations;

import org.wallet.Models.Account;
import org.wallet.connectionDB.ConnectionDB;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;

public class AccountCrudOperations implements CrudOperations<Account> {
    @Override
    public List<Account> findAll() {
        List<Account> accounts = new ArrayList<>();
        Connection connection = ConnectionDB.getConnection();
        try {
            String sql = "SELECT * FROM \"account\"";
            Statement statement = connection.createStatement();

            ResultSet resultSet = statement.executeQuery(sql);

            while(resultSet.next()){
                Account account = new Account();
                account.setAccountId(resultSet.getString("account_id"));
                account.setBalance(resultSet.getFloat("balance"));
                account.setCurrencyId(resultSet.getString("currency_id"));
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
        Account savedAccount = new Account();
        Connection connection = ConnectionDB.getConnection();

        String sql = "INSERT INTO \"account\" (balance, currency_id) " +
                "VALUES(?, ?)";
        try {
            PreparedStatement statement = connection.prepareStatement(sql);

            statement.setFloat(1, toSave.getBalance());
            statement.setString(2, toSave.getCurrencyId());

            int affectedRows = statement.executeUpdate();

            if(affectedRows != 0){
                savedAccount = toSave;
            }

            statement.close();
            connection.close();
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }


        return savedAccount;
    }

    @Override
    public Account delete(Account toDelete) {
        Account deletedAccount = new Account();
        Connection connection = ConnectionDB.getConnection();

        try {
            String sql = "DELETE FROM \"account\" WHERE id = ?";
            PreparedStatement statement = connection.prepareStatement(sql);

            statement.setString(1, toDelete.getAccountId());

            int affectedRows = statement.executeUpdate();

            if(affectedRows != 0){
                deletedAccount = toDelete;
            }

            statement.close();
            connection.close();
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }

        return deletedAccount;
    }
}
