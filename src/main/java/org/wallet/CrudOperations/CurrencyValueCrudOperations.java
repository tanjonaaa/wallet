package org.wallet.CrudOperations;

import org.springframework.stereotype.Repository;
import org.wallet.Models.CurrencyValue;
import org.wallet.ConnectionDB.ConnectionDB;

import java.sql.*;
import java.time.LocalDate;
import java.util.List;

@Repository
public class CurrencyValueCrudOperations extends AutoCrudOperations<CurrencyValue> {

    @Override
    public List<CurrencyValue> saveAll(List<CurrencyValue> toSave) {
        return null;
    }

    @Override
    public CurrencyValue delete(CurrencyValue toDelete) {
        return null;
    }

    public double getChangeRate(LocalDate date){
        Connection connection = ConnectionDB.getConnection();
        double result;
        String sql = "SELECT change_rate FROM currency_value WHERE currency_value_date <= ? LIMIT 1";
        try {
            PreparedStatement statement = connection.prepareStatement(sql);

            statement.setDate(1, Date.valueOf(date));

            ResultSet resultSet = statement.executeQuery();
            resultSet.next();
            result = resultSet.getDouble("change_rate");

            statement.close();
            connection.close();
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
        return result;
    }
}
