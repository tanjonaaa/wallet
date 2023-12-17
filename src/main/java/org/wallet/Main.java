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

        System.out.println(balanceCrud.getAmountWithChangeRate(
                "a103aa3d-60e2-4b65-b6e4-7254338ac5ff",
                LocalDateTime.now()
        ));

    }
}


