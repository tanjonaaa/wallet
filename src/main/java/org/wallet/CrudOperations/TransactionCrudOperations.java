package org.wallet.CrudOperations;

import org.wallet.Models.Transaction;

import java.sql.*;
import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;

public class TransactionCrudOperations implements CrudOperations<Transaction> {

    @Override
    public List<Transaction> findAll() {
        List<Transaction> transactions = new ArrayList<>();
        try {
            Connection connection = org.wallet.connectionDB.ConnectionDB.getConnection();
            String sql = "SELECT * FROM transaction";
            Statement statement = connection.createStatement();

            ResultSet resultSet = statement.executeQuery(sql);

            while(resultSet.next()){
                Transaction transaction = extractAuthorFromResultSet(resultSet);
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
        Transaction saved = new Transaction();
        try {
            Connection connection = org.wallet.connectionDB.ConnectionDB.getConnection();
            String sql = "INSERT INTO transaction ( description,  amount, account_id,transaction_type) " +
                    "VALUES (?, ?,?, CAST(? AS transaction_type))";

            PreparedStatement statement = connection.prepareStatement(sql);

            statement.setString(1, toSave.getDescription());
            statement.setObject(2, toSave.getAmount());
            statement.setString(3, toSave.getAccount_id());
            statement.setString(4, toSave.getTransaction_type());

            int row = statement.executeUpdate();

            if(row != 0){
                saved = toSave;
            }

            statement.close();
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
        return saved;
    }

    @Override
    public Transaction delete(Transaction toDelete) {
        Transaction deleted = new Transaction();
        try {
            Connection connection = org.wallet.connectionDB.ConnectionDB.getConnection();
            String sql = "DELETE FROM transaction WHERE id = ?";

            PreparedStatement statement = connection.prepareStatement(sql);

            statement.setString(1, toDelete.getTransaction_id());

            int row = statement.executeUpdate();

            if(row != 0){
                deleted = toDelete;
            }

            statement.close();
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
        return deleted;
    }

    private Transaction extractAuthorFromResultSet(ResultSet resultSet) throws SQLException {
        String transaction_id = resultSet.getString("transaction_id");
        String description = resultSet.getString("description");
        Double amount = resultSet.getDouble("amount");
        LocalDateTime transaction_date= resultSet.getTimestamp("transaction_date").toLocalDateTime();
        String transaction_type = resultSet.getString("transaction_type");
        String account_id = resultSet.getString("account_id");

        return new Transaction(transaction_id,description,amount,transaction_date,transaction_type,account_id);

    }

}
