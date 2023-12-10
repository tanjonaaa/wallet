package org.wallet.Components;

import lombok.*;

import java.time.LocalDateTime;

@AllArgsConstructor
@NoArgsConstructor
@Getter
@Setter
@ToString
@EqualsAndHashCode
@Builder
public class TransferHistoryComponent {
    private String transferHistoryId;
    private AccountComponent debitAccount;
    private AccountComponent creditAccount;
    private Double amount;
    private LocalDateTime transferDate;
}
