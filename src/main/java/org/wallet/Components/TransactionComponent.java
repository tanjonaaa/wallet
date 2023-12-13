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
    private String transactionId;
    private String description;
    private Double amount;
    private LocalDateTime transactionDate;
    private String transactionType;
    private String categoryId;
}
