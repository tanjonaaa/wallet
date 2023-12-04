package org.wallet.CrudOperations;

import org.wallet.Models.Currency;
import org.wallet.connectionDB.ConnectionDB;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;

public class CurrencyCrudOperations implements CrudOperations<Currency> {
    @Override
    public List<Currency> findAll() {
        List<Currency> currencies = new ArrayList<>();
        Connection connection = ConnectionDB.getConnection();
        try {
            String sql = "SELECT * FROM \"currency\"";
            Statement statement = connection.createStatement();

            ResultSet resultSet = statement.executeQuery(sql);

            while(resultSet.next()){
                Currency currency = new Currency();
                currency.setCurrencyId(resultSet.getString("currency_id"));
                currency.setName(resultSet.getString("name"));
                currency.setSymbol(resultSet.getString("symbol"));
            }

            resultSet.close();
            statement.close();
            connection.close();
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
        return currencies;
    }

    @Override
    public List<Currency> saveAll(List<Currency> toSave) {
        List<Currency> savedCurrencies = new ArrayList<>();

        for (Currency currency : toSave) {
            Currency savedCurrency = this.save(currency);
            savedCurrencies.add(savedCurrency);
        }

        return savedCurrencies;
    }

    @Override
    public Currency save(Currency toSave) {
        Currency savedCurrency = new Currency();
        Connection connection = ConnectionDB.getConnection();

        String sql = "INSERT INTO \"currency\" (name, symbol) " +
                "VALUES(?, ?)";
        try {
            PreparedStatement statement = connection.prepareStatement(sql);

            statement.setString(1, toSave.getName());
            statement.setString(2, toSave.getSymbol());

            int affectedRows = statement.executeUpdate();

            if(affectedRows != 0){
                savedCurrency = toSave;
            }

            statement.close();
            connection.close();
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }


        return savedCurrency;
    }

    @Override
    public Currency delete(Currency toDelete) {
        Currency deletedCurrency = new Currency();
        Connection connection = ConnectionDB.getConnection();

        try {
            String sql = "DELETE FROM \"currency\" WHERE id = ?";
            PreparedStatement statement = connection.prepareStatement(sql);

            statement.setString(1, toDelete.getCurrencyId());

            int affectedRows = statement.executeUpdate();

            if(affectedRows != 0){
                deletedCurrency = toDelete;
            }

            statement.close();
            connection.close();
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }

        return deletedCurrency;
    }
}
