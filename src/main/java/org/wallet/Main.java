package org.wallet;

import org.wallet.CrudOperations.*;
import org.wallet.Models.Account;
import org.wallet.Models.Balance;
import org.wallet.Models.Currency;
import org.wallet.Models.Transaction;
import org.wallet.Models.Types.AccountType;
import org.wallet.Models.Types.TransactionType;

import java.time.LocalDate;
import java.util.Arrays;
import java.util.List;

// Press Shift twice to open the Search Everywhere dialog and type `show whitespaces`,
// then press Enter. You can now see whitespace characters in your code.
public class Main {
    public static void main(String[] args) {
        TransactionCrudOperations transactionCrudOperations = new TransactionCrudOperations();

        System.out.println(transactionCrudOperations.saveAll(
                Arrays.asList(
                        new Transaction(
                                "Salary",
                                300_000d,
                                TransactionType.INCOME,
                                "18ef4570-bfbf-413c-9d6e-eaee240d0f64",
                                "597f799b-6796-4699-a16b-546156c85acf"
                        ),
                        new Transaction(
                                "Dinner",
                                50_000d,
                                TransactionType.EXPENSE,
                                "18ef4570-bfbf-413c-9d6e-eaee240d0f64",
                                "7f259f93-72c3-4819-bd7a-46c496705ebb"
                        )
                )
        ).toString());
    }
}


