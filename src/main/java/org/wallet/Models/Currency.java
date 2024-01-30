package org.wallet.Models;

import lombok.*;
import org.wallet.Annotations.Column;
import org.wallet.Annotations.Id;
import org.wallet.Annotations.Model;

@AllArgsConstructor
@NoArgsConstructor(force = true)
@RequiredArgsConstructor
@ToString
@EqualsAndHashCode
@Getter
@Setter
@Model(table = "currency")
public class Currency {
    @Id
    @Column(name = "currency_id")
    private String currencyId;
    @Column(name = "name")
    @NonNull
    private String name;
    @Column(name = "currency_code")
    @NonNull
    private String currencyCode;
}
