package org.wallet.Models;

import lombok.*;

import java.util.List;

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
    private String name;
    @NonNull
    private String currencyId;
    @NonNull
    private String accountType;
}
