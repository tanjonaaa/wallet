package org.wallet.Models;

import lombok.*;

import java.time.LocalDateTime;

@AllArgsConstructor
@NoArgsConstructor
@Getter
@Setter
@Builder
@ToString
@EqualsAndHashCode
public class CurrencyValue {
    private String currencyValueId;
    private String idCurrencySource;
    private String idCurrencyDestination;
    private Double EuroEnAriary; // exemple input : 4500Ar
    private LocalDateTime currencyValueDate;
}
