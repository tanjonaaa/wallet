package org.wallet.Models;

import lombok.*;

import java.time.LocalDateTime;

@AllArgsConstructor
@NoArgsConstructor(force = true)
@RequiredArgsConstructor
@ToString
@EqualsAndHashCode
@Getter
@Setter
@Builder
public class TranferHistory {
    private String transferHistoryId;
    @NonNull
    private String debitTransactionId;
    @NonNull
    private String creditTransactionId;

    private LocalDateTime tranferDate;
}
