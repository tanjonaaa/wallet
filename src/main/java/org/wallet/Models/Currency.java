package org.wallet.Models;

import lombok.*;

@AllArgsConstructor
@NoArgsConstructor
@RequiredArgsConstructor
@ToString
@EqualsAndHashCode
@Getter
@Setter
public class Currency {
    @NonNull
    private String currencyId;
    @NonNull
    private String name;
    @NonNull
    private String symbol;
}
