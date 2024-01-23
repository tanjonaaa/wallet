package org.wallet.Models;

import lombok.*;
import org.wallet.Annotations.Column;
import org.wallet.Annotations.Id;
import org.wallet.Annotations.Model;

import java.time.LocalDateTime;

@AllArgsConstructor
@NoArgsConstructor(force = true)
@RequiredArgsConstructor
@ToString
@EqualsAndHashCode
@Getter
@Setter
@Builder
@Model(table = "transfer_history")
public class TransferHistory {
    @Id
    @Column(name = "id")
    private String transferHistoryId;
    @Column(name = "debit_transaction_id")
    @NonNull
    private String debitTransactionId;
    @Column(name = "credit_transaction_id")
    @NonNull
    private String creditTransactionId;

    @Column(name = "transfer_date")
    @org.wallet.Annotations.LocalDateTime
    private LocalDateTime transferDate;
}
