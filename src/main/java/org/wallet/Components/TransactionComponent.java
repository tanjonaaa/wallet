package org.wallet.Components;

import lombok.*;

import java.time.LocalDateTime;


@AllArgsConstructor
@NoArgsConstructor
@Getter
@Setter
@ToString
@EqualsAndHashCode
public class TransactionComponent {
    private String transaction_id;
    private String description;
    private Double amount;
    private LocalDateTime transaction_date;
    private String transaction_type;
}
