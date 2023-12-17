package org.wallet;

import org.wallet.CrudOperations.*;

import java.time.LocalDateTime;


// Press Shift twice to open the Search Everywhere dialog and type `show whitespaces`,
// then press Enter. You can now see whitespace characters in your code.
public class Main {
    public static void main(String[] args) {
        var accountCrud = new AccountCrudOperations();
        var balanceCrud = new BalanceCrudOperations();

        System.out.println(balanceCrud.getAmountWithChangeRate(
                "a103aa3d-60e2-4b65-b6e4-7254338ac5ff",
                LocalDateTime.now()
        ));

    }
}


