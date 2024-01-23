package org.wallet.CrudOperations;

import org.wallet.Components.TransactionComponent;
import org.wallet.Models.Transaction;
import org.wallet.Models.Types.TransactionType;
import org.wallet.ConnectionDB.ConnectionDB;

import java.sql.*;
import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;

public class TransactionCrudOperations extends AutoCrudOperations<Transaction> {

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
    public Transaction delete(Transaction toDelete) {
        Transaction deleted;
        try {
            Connection connection = org.wallet.ConnectionDB.ConnectionDB.getConnection();
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

    public List<TransactionComponent> getTransactionByAccountId(String accountId, LocalDateTime date){
        List<TransactionComponent> transactions = new ArrayList<>();
        Connection connection = ConnectionDB.getConnection();

        String sql = (date == null) ? "SELECT * FROM \"transaction\" WHERE account_id = ?" : "SELECT * FROM \"transaction\" WHERE account_id = ? AND transaction_date <= ?";

        try {
            PreparedStatement statement = connection.prepareStatement(sql);

            statement.setString(1, accountId);

            if(date != null){
                statement.setTimestamp(2, Timestamp.valueOf(date));
            }

            ResultSet resultSet = statement.executeQuery();

            while(resultSet.next()){
                Transaction transaction = mapResultSet(resultSet);

                TransactionComponent component = new TransactionComponent(
                        transaction.getTransactionId(),
                        transaction.getDescription(),
                        transaction.getAmount(),
                        transaction.getTransactionDate(),
                        transaction.getTransactionType().toString(),
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

}
