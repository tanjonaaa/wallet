package org.wallet;

import org.wallet.CrudOperations.BalanceCrudOperations;
import org.wallet.CrudOperations.CurrencyCrudOperations;
import org.wallet.CrudOperations.TransactionCrudOperations;
import org.wallet.Models.Balance;
import org.wallet.Models.Currency;
import org.wallet.Models.Transaction;

import java.time.LocalDateTime;
import java.util.List;

// Press Shift twice to open the Search Everywhere dialog and type `show whitespaces`,
// then press Enter. You can now see whitespace characters in your code.
public class Main {
    public static void main(String[] args) {
        BalanceCrudOperations balanceCrudOperations = new BalanceCrudOperations();
        String accountId = "d701dc54-911f-11ee-b9d1-0242ac120002";
        LocalDateTime date = LocalDateTime.parse("2023-12-08T15:00");

        Balance balance = balanceCrudOperations.getAmountByDate(accountId, date);

        if (balance != null) {
            System.out.println("Amount : " + balance.getAmount());
        } else {
            System.out.println("error");
        }
    }
}


