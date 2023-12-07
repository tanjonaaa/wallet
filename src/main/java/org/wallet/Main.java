package org.wallet;

import org.wallet.CrudOperations.CurrencyCrudOperations;
import org.wallet.CrudOperations.TransactionCrudOperations;
import org.wallet.Models.Currency;
import org.wallet.Models.Transaction;

import java.util.List;

// Press Shift twice to open the Search Everywhere dialog and type `show whitespaces`,
// then press Enter. You can now see whitespace characters in your code.
public class Main {
    public static void main(String[] args) {
        CurrencyCrudOperations currencyDAO = new CurrencyCrudOperations(); // Remplacez CurrencyDAO par le nom réel de votre classe

        List<Currency> currencies = currencyDAO.findAll();

        System.out.println("List of Currencies:");
        for (Currency currency : currencies) {
            System.out.println(currency); // Supposant que vous avez une méthode toString dans votre classe Currency
        }
    }
}


