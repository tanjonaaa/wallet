package org.wallet.CrudOperations;

import org.wallet.Components.TransactionComponent;
import org.wallet.Models.Transaction;
import org.wallet.connectionDB.ConnectionDB;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;

public class TransactionCrudOperations implements CrudOperations<Transaction> {
    public static final String TRANSACTION_ID_COLUMN = "transaction_id";
    public static final String DESCRIPTION_COLUMN = "description";
    public static final String AMOUNT_COLUMN = "amount";
    public static final String TRANSACTION_DATE_COLUMN = "transaction_date";
    public static final String TRANSACTION_TYPE_COLUMN = "transaction_type";
    public static final String ACCOUNT_ID_COLUMN = "account_id";
    public static final String CATEGORIE_ID_COLUMN = "category_id";




    @Override
    public List<Transaction> findAll() {
        List<Transaction> transactions = new ArrayList<>();
        try {
            Connection connection = org.wallet.connectionDB.ConnectionDB.getConnection();
            String sql = "SELECT * FROM transaction";
            Statement statement = connection.createStatement();

            ResultSet resultSet = statement.executeQuery(sql);

            while(resultSet.next()){
                Transaction transaction = mapResultSet(resultSet);
                transactions.add(transaction);
            }

            resultSet.close();
            statement.close();
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
        return transactions;
    }

    @Override
    public List<Transaction> saveAll(List<Transaction> toSave) {
        List<Transaction> saved = new ArrayList<>();
        for (Transaction transaction : toSave) {
            Transaction savedTransaction = this.save(transaction);
            saved.add(savedTransaction);
        }
        return saved;
    }

    @Override
    public Transaction save(Transaction toSave) {
        Transaction saved;

        String sql;
        if(toSave.getTransactionId() == null){
            sql = "INSERT INTO transaction ( description,  amount, account_id,transaction_type,category_id) " +
                    "VALUES (?, ?, ?, CAST(? AS transaction_type),?) RETURNING *";
        }else {
            sql = "UPDATE transaction " +
                    "SET description = ?,  amount = ?, account_id = ?, transaction_type = CAST(? AS transaction_type)" +
                    " , category_id = ? WHERE transaction_id = ? RETURNING *";
        }


        try {
            Connection connection = org.wallet.connectionDB.ConnectionDB.getConnection();

            PreparedStatement statement = connection.prepareStatement(sql);

            statement.setString(1, toSave.getDescription());
            statement.setObject(2, toSave.getAmount());
            statement.setString(3, toSave.getAccountId());
            statement.setString(4, toSave.getTransactionType());
            statement.setString(5,toSave.getCategoryId());

            if(toSave.getTransactionId() != null){
                statement.setString(5, toSave.getTransactionId());
            }

            statement.execute();

            ResultSet resultSet = statement.getResultSet();
            resultSet.next();
            saved = mapResultSet(resultSet);

            statement.close();
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
        return saved;
    }

    @Override
    public Transaction delete(Transaction toDelete) {
        Transaction deleted;
        try {
            Connection connection = org.wallet.connectionDB.ConnectionDB.getConnection();
            String sql = "DELETE FROM transaction WHERE transaction_id = ? RETURNING *";

            PreparedStatement statement = connection.prepareStatement(sql);

            statement.setString(1, toDelete.getTransactionId());

            statement.execute();

            ResultSet resultSet = statement.getResultSet();
            resultSet.next();
            deleted = mapResultSet(resultSet);

            statement.close();
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
        return deleted;
    }

    public List<TransactionComponent> getTransactionByAccountId(String accountId){
        List<TransactionComponent> transactions = new ArrayList<>();
        Connection connection = ConnectionDB.getConnection();
        String sql = "SELECT * FROM \"transaction\" WHERE account_id = ?";

        try {
            PreparedStatement statement = connection.prepareStatement(sql);

            statement.setString(1, accountId);

            ResultSet resultSet = statement.executeQuery();

            while(resultSet.next()){
                Transaction transaction = mapResultSet(resultSet);

                TransactionComponent component = new TransactionComponent(
                        transaction.getTransactionId(),
                        transaction.getDescription(),
                        transaction.getAmount(),
                        transaction.getTransactionDate(),
                        transaction.getTransactionType(),
                        transaction.getCategoryId()
                );

                transactions.add(component);
            }

        } catch (SQLException e) {
            throw new RuntimeException(e);
        }

        return transactions;
    }

    public Transaction getById(String id){
        Connection connection = ConnectionDB.getConnection();
        String sql = "SELECT * FROM \"transaction\" WHERE transaction_id = ?";
        Transaction transaction = null;

        try {
            PreparedStatement statement = connection.prepareStatement(sql);

            statement.setString(1, id);

            ResultSet resultSet = statement.executeQuery();

            if(resultSet.next()){
                transaction = mapResultSet(resultSet);
            }

            statement.close();
            connection.close();
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }

        return transaction;
    }

    private Transaction mapResultSet(ResultSet resultSet) throws SQLException {
        Transaction transaction = new Transaction();
        transaction.setTransactionId(resultSet.getString(TRANSACTION_ID_COLUMN));
        transaction.setDescription(resultSet.getString(DESCRIPTION_COLUMN));
        transaction.setAmount(resultSet.getDouble(AMOUNT_COLUMN));
        transaction.setTransactionDate(resultSet.getTimestamp(TRANSACTION_DATE_COLUMN).toLocalDateTime());
        transaction.setTransactionType(resultSet.getString(TRANSACTION_TYPE_COLUMN));
        transaction.setAccountId(resultSet.getString(ACCOUNT_ID_COLUMN));
        transaction.setCategoryId(resultSet.getString(CATEGORIE_ID_COLUMN));

        return transaction;
    }

}
