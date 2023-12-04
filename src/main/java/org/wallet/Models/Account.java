package org.wallet.Models;

import lombok.*;

@AllArgsConstructor
@NoArgsConstructor(force = true)
@RequiredArgsConstructor
@ToString
@EqualsAndHashCode
@Getter
@Setter
public class Account {
    private String accountId;
    @NonNull
    private Float balance;
    @NonNull
    private String currencyId;
}