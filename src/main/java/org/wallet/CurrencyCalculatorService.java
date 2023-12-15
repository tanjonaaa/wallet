package org.wallet;

import org.wallet.Models.CurrencyValue;
import org.wallet.connectionDB.ConnectionDB;

import java.sql.*;
import java.time.LocalDateTime;
import java.util.List;

public class CurrencyCalculatorService {
    public enum CalculationType {
        AVERAGE, MINIMUM, MAXIMUM, MEDIAN
    }

    private static final String SELECT_WEIGHTED_AVERAGE_QUERY =
                    "SELECT AVG(change_rate) AS weighted_average" +
                    "FROM currency_value" +
                    "WHERE currency_value_date >= ? " +
                    "AND currency_value_date < ? ";

    public static double calculateCurrencyValue(List<CurrencyValue> currencyValues, LocalDateTime date1,LocalDateTime date2 ,  CalculationType calculationType) {

        switch (calculationType) {
            case AVERAGE:
                return calculateWeightedAverage(date1, date2);
            /*case MINIMUM:
                return calculateMinimum(filteredValues);
            case MAXIMUM:
                return calculateMaximum(filteredValues);
            case MEDIAN:
                return calculateMedian(filteredValues);*/
            default:
                throw new IllegalArgumentException("Invalid CalculationType");
        }
    }

    static double calculateWeightedAverage(LocalDateTime date1 , LocalDateTime date2) {
        try (
                Connection connection =   ConnectionDB.getConnection();
                PreparedStatement preparedStatement = connection.prepareStatement(SELECT_WEIGHTED_AVERAGE_QUERY)
        ) {
            preparedStatement.setDate(1, Date.valueOf(date1.toLocalDate()));
            preparedStatement.setDate(1, Date.valueOf(date2.toLocalDate()));


            try (ResultSet resultSet = preparedStatement.executeQuery()) {
                if (resultSet.next()) {
                    return resultSet.getDouble("weighted_average_change_rate");
                }
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }

        return 0.0;
    }
    }

