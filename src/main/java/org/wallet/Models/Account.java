package org.wallet.Models;

import lombok.*;

@AllArgsConstructor
@NoArgsConstructor
@RequiredArgsConstructor
@ToString
@EqualsAndHashCode
@Getter
@Setter
public class Account {
    @NonNull
    private String accountId;
    @NonNull
    private String balance;
    @NonNull
    private String currencyId;
}
