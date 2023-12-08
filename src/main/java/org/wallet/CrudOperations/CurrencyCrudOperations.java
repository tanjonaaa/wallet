package org.wallet.CrudOperations;

import org.wallet.Components.CurrencyComponent;
import org.wallet.Models.Currency;
import org.wallet.connectionDB.ConnectionDB;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;

public class CurrencyCrudOperations implements CrudOperations<Currency> {
    public static final String CURRENCY_ID_COLUMN = "currency_id";
    public static final String NAME_COLUMN = "name";
    public static final String CODE_COLUMN = "code";
    @Override
    public List<Currency> findAll() {
        List<Currency> currencies = new ArrayList<>();
        Connection connection = ConnectionDB.getConnection();
        try {
            String sql = "SELECT * FROM \"currency\"";
            Statement statement = connection.createStatement();

            ResultSet resultSet = statement.executeQuery(sql);

            while(resultSet.next()){
                Currency currency = this.mapResultSet(resultSet);

                currencies.add(currency);
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
        Currency savedCurrency;
        Connection connection = ConnectionDB.getConnection();

        String sql;

        if(toSave.getCurrencyId() == null){
            sql = "INSERT INTO \"currency\" (name, code) " +
                    "VALUES(?, CAST(? AS currency_code)) RETURNING *";
        }else{
            sql = "UPDATE \"currency\" " +
                    "SET name = ?, code = CAST(? AS currency_code) " +
                    "WHERE currency_id = ? RETURNING *";
        }

        try {
            PreparedStatement statement = connection.prepareStatement(sql);

            statement.setString(1, toSave.getName());
            statement.setString(2, toSave.getCode());

            if(toSave.getCurrencyId() != null){
                statement.setString(3, toSave.getCurrencyId());
            }

            statement.execute();

            ResultSet resultSet = statement.getResultSet();
            resultSet.next();
            savedCurrency = mapResultSet(resultSet);

            statement.close();
            connection.close();
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }


        return savedCurrency;
    }

    @Override
    public Currency delete(Currency toDelete) {
        Currency deletedCurrency;
        Connection connection = ConnectionDB.getConnection();

        try {
            String sql = "DELETE FROM \"currency\" WHERE currency_id = ? RETURNING *";
            PreparedStatement statement = connection.prepareStatement(sql);

            statement.setString(1, toDelete.getCurrencyId());

            statement.execute();

            ResultSet resultSet = statement.getResultSet();
            resultSet.next();
            deletedCurrency = mapResultSet(resultSet);

            statement.close();
            connection.close();
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }

        return deletedCurrency;
    }

    public CurrencyComponent getCurrencyById(String id){
        Currency currency;

        Connection connection = ConnectionDB.getConnection();

        String sql = "SELECT * FROM \"currency\" WHERE currency_id = ?";

        try {
            PreparedStatement statement = connection.prepareStatement(sql);

            statement.setString(1, id);

            ResultSet resultSet = statement.executeQuery();

            if(resultSet.next()){
                currency = this.mapResultSet(resultSet);
            }else {
                currency = null;
            }

        } catch (SQLException e) {
            throw new RuntimeException(e);
        }

        return new CurrencyComponent(
                currency.getCurrencyId(),
                currency.getName(),
                currency.getCode()
        );
    }

    private Currency mapResultSet(ResultSet resultSet) throws SQLException {
        Currency currency = new Currency();
        currency.setCurrencyId(resultSet.getString(CURRENCY_ID_COLUMN));
        currency.setName(resultSet.getString(NAME_COLUMN));
        currency.setCode(resultSet.getString(CODE_COLUMN));

        return currency;
    }
}
