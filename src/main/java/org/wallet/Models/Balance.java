package org.wallet.Models;

import lombok.*;

import java.time.LocalDateTime;

@AllArgsConstructor
@NoArgsConstructor(force = true)
@RequiredArgsConstructor
@Getter
@Setter
@ToString
@EqualsAndHashCode
public class Balance {
    private String balanceId;
    @NonNull
    private LocalDateTime balanceTimestamp;
    @NonNull
    private String accountId;
    @NonNull
    private Double amount;
}
