package org.wallet.Components;

import lombok.*;
import org.wallet.Models.Transaction;

import java.time.LocalDateTime;
import java.util.List;

@AllArgsConstructor
@Getter
@Setter
@EqualsAndHashCode
@ToString
public class AccountComponent {
    private String accountId;
    private String name;
    private BalanceComponent balance;
    private CurrencyComponent currency;
    private List<Transaction> transactionList;
    private String accountType;
}
