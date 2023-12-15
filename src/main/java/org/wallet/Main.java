package org.wallet;

import org.wallet.CrudOperations.*;
import org.wallet.Models.Account;
import org.wallet.Models.Types.AccountType;

import java.time.LocalDateTime;


// Press Shift twice to open the Search Everywhere dialog and type `show whitespaces`,
// then press Enter. You can now see whitespace characters in your code.
public class Main {
    public static void main(String[] args) {
        var accountCrud = new AccountCrudOperations();
        var balanceCrud = new BalanceCrudOperations();

        System.out.println(accountCrud.save(
                new Account(
                        "143342a0-7351-46d3-a216-54ac0e7533c7",
                        "Compte courant",
                        "6cc9845f-d195-4958-a8e8-6d4813879030",
                        AccountType.CASH
                )
        ));

    }
}


