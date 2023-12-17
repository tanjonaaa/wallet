package org.wallet;

import org.wallet.CrudOperations.*;
import org.wallet.Models.Account;
import org.wallet.Models.Balance;
import org.wallet.Models.Currency;
import org.wallet.Models.Transaction;
import org.wallet.Models.Types.AccountType;
import org.wallet.Models.Types.TransactionType;

import javax.swing.text.DateFormatter;
import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.Arrays;
import java.util.List;

// Press Shift twice to open the Search Everywhere dialog and type `show whitespaces`,
// then press Enter. You can now see whitespace characters in your code.
public class Main {
    public static void main(String[] args) {
        var accountCrud = new AccountCrudOperations();
        var balanceCrud = new BalanceCrudOperations();

        System.out.println(balanceCrud.getAmountByDate(
                "6f167b81-13dc-476d-a2a2-531b4b483cc0",
                LocalDateTime.parse("2023-12-17 12:15", DateTimeFormatter.ofPattern("yyyy-MM-dd HH:mm"))
        ));

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


