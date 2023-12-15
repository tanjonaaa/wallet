package org.wallet;

import org.wallet.CrudOperations.BalanceCrudOperations;
import org.wallet.CrudOperations.CategoryCrudOperations;
import org.wallet.CrudOperations.CurrencyCrudOperations;
import org.wallet.CrudOperations.TransactionCrudOperations;
import org.wallet.Models.Balance;
import org.wallet.Models.Currency;
import org.wallet.Models.Transaction;

import java.time.LocalDate;
import java.util.List;

// Press Shift twice to open the Search Everywhere dialog and type `show whitespaces`,
// then press Enter. You can now see whitespace characters in your code.
public class Main {
    public static void main(String[] args) {
        var categoryCrudOperations = new CategoryCrudOperations();
        var startDate = LocalDate.parse("2023-01-01");
        var endDate = LocalDate.parse("2023-12-31");

        System.out.println(categoryCrudOperations.getSumOfTransactions(
                "c9179a01-e126-4c96-96ad-92118a131349",
                startDate,
                endDate
        ));
    }
}


