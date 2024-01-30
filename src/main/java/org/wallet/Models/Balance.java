package org.wallet.Models;

import lombok.*;
import org.wallet.Annotations.Column;
import org.wallet.Annotations.Id;
import org.wallet.Annotations.Model;

import java.time.LocalDateTime;

@AllArgsConstructor
@NoArgsConstructor(force = true)
@RequiredArgsConstructor
@Getter
@Setter
@ToString
@EqualsAndHashCode
@Model(table = "balance")
public class Balance {
    @Id
    @Column(name = "balance_id")
    private String balanceId;
    @Column(name = "balance_timestamp")
    @org.wallet.Annotations.LocalDateTime
    @NonNull
    private LocalDateTime balanceTimestamp;
    @Column(name = "account_id")
    @NonNull
    private String accountId;
    @Column(name = "amount")
    @NonNull
    private Double amount;
}
