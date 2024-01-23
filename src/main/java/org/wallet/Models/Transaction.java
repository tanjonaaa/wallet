package org.wallet.Models;

import lombok.*;
import org.wallet.Annotations.Column;
import org.wallet.Annotations.CustomType;
import org.wallet.Annotations.Id;
import org.wallet.Annotations.Model;
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
@Model(table = "transaction")
public class Transaction {
        @Id
        @Column(name = "transaction_id")
        private String transactionId;
        @Column(name = "description")
        @NonNull
        private String description;
        @Column(name = "amount")
        @NonNull
        private Double amount;
        @Column(name = "transaction_date")
        @org.wallet.Annotations.LocalDateTime
        private LocalDateTime transactionDate;
        @Column(name = "transaction_type")
        @CustomType(type_class = "TransactionType")
        @NonNull
        private TransactionType transactionType;
        @Column(name = "account_id")
        @NonNull
        private String accountId;
        @Column(name = "category_id")
        @NonNull
        private String categoryId;
}
