package org.wallet.Models;

import lombok.*;
import org.wallet.Models.Types.TransactionType;

import java.sql.Timestamp;
import java.time.LocalDateTime;


@AllArgsConstructor
@NoArgsConstructor(force = true)
@RequiredArgsConstructor
@Builder
@Getter
@Setter
@ToString
@EqualsAndHashCode
public class Transaction {
        private String transactionId;
        @NonNull
        private String description;
        @NonNull
        private Double amount;
        private LocalDateTime transactionDate;
        @NonNull
        private TransactionType transactionType;
        @NonNull
        private String accountId;
        @NonNull
        private String categoryId;
}
