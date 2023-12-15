package org.wallet;

import org.wallet.CrudOperations.*;

import java.time.LocalDateTime;


// Press Shift twice to open the Search Everywhere dialog and type `show whitespaces`,
// then press Enter. You can now see whitespace characters in your code.
public class Main {
    public static void main(String[] args) {
        var accountCrud = new AccountCrudOperations();
        var balanceCrud = new BalanceCrudOperations();

        System.out.println(balanceCrud.getAmountByDate("6f167b81-13dc-476d-a2a2-531b4b483cc0", LocalDateTime.now()));
        System.out.println(balanceCrud.getAmountByDate("a103aa3d-60e2-4b65-b6e4-7254338ac5ff", LocalDateTime.now()));

        /*
        System.out.println(accountCrud.makeTransaction(
                new Transaction(
                        "Kids",
                        15_000d,
                        TransactionType.EXPENSE,
                        "6f167b81-13dc-476d-a2a2-531b4b483cc0",
                        "1f35f7fd-2f52-4d8b-aab7-b7c0fae4597c"
                )
        ).toString());
         */
    }
}


