package org.wallet;

import org.wallet.CrudOperations.*;
import org.wallet.Models.Account;
import org.wallet.Models.Transaction;
import org.wallet.Models.Types.AccountType;
import org.wallet.Models.Types.TransactionType;

import java.time.LocalDateTime;


// Press Shift twice to open the Search Everywhere dialog and type `show whitespaces`,
// then press Enter. You can now see whitespace characters in your code.
public class Main {
    public static void main(String[] args) {
        var accountCrud = new AccountCrudOperations();
        System.out.println(accountCrud.save(
                Account.builder()
                        .name("Compte courant")
                        .currencyId("5389071c-4c8e-49aa-be10-db69c5db76cc")
                        .accountType(AccountType.CASH)
                        .build()
        ));
    }
}


