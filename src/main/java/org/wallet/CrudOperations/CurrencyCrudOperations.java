package org.wallet.CrudOperations;

import org.wallet.Components.CurrencyComponent;
import org.wallet.Models.Currency;
import org.wallet.ConnectionDB.ConnectionDB;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;

public class CurrencyCrudOperations extends AutoCrudOperations<Currency> {

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
                currency.getCurrencyCode()
        );
    }

}
