package org.wallet.Models;

import lombok.*;
import org.wallet.Annotations.Column;
import org.wallet.Annotations.CustomType;
import org.wallet.Annotations.Id;
import org.wallet.Annotations.Model;
import org.wallet.Models.Types.AccountType;

import java.util.List;

@AllArgsConstructor
@NoArgsConstructor(force = true)
@RequiredArgsConstructor
@ToString
@EqualsAndHashCode
@Getter
@Setter
@Model(table = "account")
@Builder
public class Account {
    @Column(name = "account_id")
    @Id
    private String accountId;
    @Column(name = "name")
    @NonNull
    private String name;
    @Column(name = "currency_id")
    @NonNull
    private String currencyId;
    @Column(name = "account_type")
    @CustomType(type_class = "AccountType")
    @NonNull
    private AccountType accountType;
}
