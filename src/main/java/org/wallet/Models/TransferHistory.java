package org.wallet.Models;

import lombok.*;
import org.wallet.Annotations.Column;
import org.wallet.Annotations.Id;
import org.wallet.Annotations.Model;

import java.time.LocalDateTime;
import java.util.Objects;

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

    public TransferHistory(String transferHistoryId, @NonNull String debitTransactionId,
                           @NonNull String creditTransactionId, LocalDateTime transferDate) {
        this.transferHistoryId = transferHistoryId;
        this.debitTransactionId = debitTransactionId;
        this.creditTransactionId = creditTransactionId;
        this.transferDate = transferDate;
    }

    public TransferHistory() {
    }

    public String getTransferHistoryId() {
        return transferHistoryId;
    }

    public void setTransferHistoryId(String transferHistoryId) {
        this.transferHistoryId = transferHistoryId;
    }

    public String getDebitTransactionId() {
        return debitTransactionId;
    }

    public void setDebitTransactionId(String debitTransactionId) {
        this.debitTransactionId = debitTransactionId;
    }

    public String getCreditTransactionId() {
        return creditTransactionId;
    }

    public void setCreditTransactionId(String creditTransactionId) {
        this.creditTransactionId = creditTransactionId;
    }

    public LocalDateTime getTransferDate() {
        return transferDate;
    }

    public void setTransferDate(LocalDateTime transferDate) {
        this.transferDate = transferDate;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        TransferHistory that = (TransferHistory) o;
        return Objects.equals(transferHistoryId, that.transferHistoryId) && Objects.equals(debitTransactionId, that.debitTransactionId) && Objects.equals(creditTransactionId, that.creditTransactionId) && Objects.equals(transferDate, that.transferDate);
    }

    @Override
    public int hashCode() {
        return Objects.hash(transferHistoryId, debitTransactionId, creditTransactionId, transferDate);
    }

    @Override
    public String toString() {
        return "TransferHistory{" +
                "transferHistoryId='" + transferHistoryId + '\'' +
                ", debitTransactionId='" + debitTransactionId + '\'' +
                ", creditTransactionId='" + creditTransactionId + '\'' +
                ", transferDate=" + transferDate +
                '}';
    }
}
