package org.wallet.Models;

import lombok.*;

import java.time.LocalDate;

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
    private Double changeRate;
    private LocalDate currencyValueDate;
}
