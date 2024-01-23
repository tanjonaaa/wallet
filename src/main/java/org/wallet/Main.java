package org.wallet;

import org.wallet.CrudOperations.*;
import org.wallet.Models.Account;
import org.wallet.Models.Transaction;
import org.wallet.Models.Types.AccountType;
import org.wallet.Models.Types.TransactionType;
import org.wallet.Utilities.Enums.PgQuery;
import org.wallet.Utilities.QueryFormatterUtility;

import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;


// Press Shift twice to open the Search Everywhere dialog and type `show whitespaces`,
// then press Enter. You can now see whitespace characters in your code.
public class Main {
    public static void main(String[] args) {
        List<CrudOperations<?>> crudOperations = List.of(
                new AccountCrudOperations(),
                new BalanceCrudOperations(),
                new CategoryCrudOperations(),
                new CurrencyCrudOperations(),
                new TransferHistoryCrudOperations(),
                new TransactionCrudOperations(),
                new CurrencyValueCrudOperations()
        );

        crudOperations.forEach(
                crudOperation -> System.out.println(crudOperation.findAll())
        );
    }
}


