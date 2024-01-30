package org.wallet.Models;

import lombok.*;
import org.wallet.Annotations.Column;
import org.wallet.Annotations.Id;
import org.wallet.Annotations.Model;

import java.time.LocalDate;
import java.time.LocalDateTime;

@AllArgsConstructor
@NoArgsConstructor
@Getter
@Setter
@Builder
@ToString
@EqualsAndHashCode
@Model(table = "currency_value")
public class CurrencyValue {
    @Id
    @Column(name = "currency_value_id")
    private String currencyValueId;
    @Column(name = "id_currency_source")
    @NonNull
    private String idCurrencySource;
    @Column(name = "id_currency_destination")
    @NonNull
    private String idCurrencyDestination;
    @Column(name = "change_rate")
    @NonNull
    private Double changeRate;
    @Column(name = "currency_value_date")
    @org.wallet.Annotations.LocalDateTime
    @NonNull
    private LocalDateTime currencyValueDate;
}
