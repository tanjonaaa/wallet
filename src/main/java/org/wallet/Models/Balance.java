package org.wallet.Models;

import lombok.NonNull;
import org.wallet.Annotations.Column;
import org.wallet.Annotations.Id;
import org.wallet.Annotations.Model;

import java.time.LocalDateTime;
import java.util.Objects;

@Model(table = "balance")
public class Balance {
    @Id
    @Column(name = "balance_id")
    private String balanceId;
    @Column(name = "balance_timestamp")
    private LocalDateTime balanceTimestamp;
    @Column(name = "account_id")
    private String accountId;
    @Column(name = "amount")
    private Double amount;

    public Balance() {
    }

    public Balance(String balanceId, LocalDateTime balanceTimestamp, String accountId, Double amount) {
        this.balanceId = balanceId;
        this.balanceTimestamp = balanceTimestamp;
        this.accountId = accountId;
        this.amount = amount;
    }

    public Balance(LocalDateTime balanceTimestamp, String accountId, double v) {
    }

    public String getBalanceId() {
        return balanceId;
    }

    public LocalDateTime getBalanceTimestamp() {
        return balanceTimestamp;
    }

    public String getAccountId() {
        return accountId;
    }

    public Double getAmount() {
        return amount;
    }

    public void setBalanceId(String balanceId) {
        this.balanceId = balanceId;
    }

    public void setBalanceTimestamp(LocalDateTime balanceTimestamp) {
        this.balanceTimestamp = balanceTimestamp;
    }

    public void setAccountId(String accountId) {
        this.accountId = accountId;
    }

    public void setAmount(Double amount) {
        this.amount = amount;
    }

    @Override
    public String toString() {
        return "Balance{" +
                "balanceId='" + balanceId + '\'' +
                ", balanceTimestamp=" + balanceTimestamp +
                ", accountId='" + accountId + '\'' +
                ", amount=" + amount +
                '}';
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Balance balance = (Balance) o;
        return Objects.equals(balanceId, balance.balanceId) &&
                Objects.equals(balanceTimestamp, balance.balanceTimestamp) &&
                Objects.equals(accountId, balance.accountId) &&
                Objects.equals(amount, balance.amount);
    }

    @Override
    public int hashCode() {
        return Objects.hash(balanceId, balanceTimestamp, accountId, amount);
    }
}
