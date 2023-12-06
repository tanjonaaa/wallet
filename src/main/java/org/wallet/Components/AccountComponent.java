package org.wallet.Components;

import lombok.*;

@AllArgsConstructor
@Getter
@Setter
@EqualsAndHashCode
@ToString
public class AccountComponent {
    private String accountId;
    private Double balance;
    private CurrencyComponent currency;
}
