package org.wallet.Components;

import lombok.*;

import java.time.LocalDateTime;

@AllArgsConstructor
@ToString
@EqualsAndHashCode
@Getter
@Setter
public class BalanceComponent {
    private Double amount;
    private LocalDateTime lastUpdate;
}
