package org.wallet.CrudOperations;

import org.wallet.Components.BalanceComponent;
import org.wallet.Models.Balance;
import org.wallet.connectionDB.ConnectionDB;

import java.sql.*;
import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;

public class BalanceCrudOperations implements CrudOperations<Balance> {
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

    public Double getAmountByDate(String accountId, LocalDateTime amountUpdated) {
        Double totalAmount = 0.00; // Initialisez la somme à zéro
        Connection connection = ConnectionDB.getConnection();

        String sql = "SELECT SUM(amount) AS balance FROM \"balance\" WHERE account_id = ? AND balance_timestamp <= ?";

        try {
            PreparedStatement statement = connection.prepareStatement(sql);

            statement.setString(1, accountId);

            statement.setTimestamp(2, Timestamp.valueOf(amountUpdated));

            ResultSet resultSet = statement.executeQuery();

            if (resultSet.next()) {
                totalAmount = resultSet.getDouble("balance"); // Obtenez la somme directement du ResultSet
            }

            statement.close();
            connection.close();
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
        return totalAmount;
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
