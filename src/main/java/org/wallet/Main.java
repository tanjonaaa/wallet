package org.wallet;

import org.wallet.CrudOperations.BalanceCrudOperations;
import org.wallet.Models.CurrencyValue;


import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;

// Press Shift twice to open the Search Everywhere dialog and type `show whitespaces`,
// then press Enter. You can now see whitespace characters in your code.
public class Main {
    public static void main(String[] args) {
        LocalDateTime date1 = LocalDateTime.of(2023, 12, 6, 0, 0);
        LocalDateTime date2 = LocalDateTime.of(2023, 12, 6, 23, 0);

        double weightedAverage = CurrencyCalculatorService.calculateWeightedAverage(date1, date2);
        System.out.println("Weighted Average: " + weightedAverage);
    }
    }



