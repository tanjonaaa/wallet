package org.wallet.Models;

import lombok.*;

import java.sql.Timestamp;
import java.time.LocalDateTime;


@AllArgsConstructor
@RequiredArgsConstructor
@Getter
@Setter
@ToString
@EqualsAndHashCode
public class Transaction {
        private String transaction_id;
        @NonNull
        private String description;
        @NonNull
        private Double amount;
        @NonNull
        private LocalDateTime transaction_date;
        @NonNull
        private String transaction_type;
        @NonNull
        private String account_id;
}
