package org.wallet.Models;

import lombok.*;
import org.wallet.Annotations.Column;
import org.wallet.Annotations.CustomType;
import org.wallet.Annotations.Id;
import org.wallet.Annotations.Model;
import org.wallet.Models.Types.TransactionType;

import java.time.LocalDateTime;
import java.util.Objects;

@Builder
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

        public Transaction(String transactionId, @NonNull String description, @NonNull Double amount,
                           LocalDateTime transactionDate, @NonNull TransactionType transactionType,
                           @NonNull String accountId, @NonNull String categoryId) {
                this.transactionId = transactionId;
                this.description = description;
                this.amount = amount;
                this.transactionDate = transactionDate;
                this.transactionType = transactionType;
                this.accountId = accountId;
                this.categoryId = categoryId;
        }

        public Transaction() {
        }

        public String getTransactionId() {
                return transactionId;
        }

        public void setTransactionId(String transactionId) {
                this.transactionId = transactionId;
        }

        public String getDescription() {
                return description;
        }

        public void setDescription(String description) {
                this.description = description;
        }

        public Double getAmount() {
                return amount;
        }

        public void setAmount(Double amount) {
                this.amount = amount;
        }

        public LocalDateTime getTransactionDate() {
                return transactionDate;
        }

        public void setTransactionDate(LocalDateTime transactionDate) {
                this.transactionDate = transactionDate;
        }

        public TransactionType getTransactionType() {
                return transactionType;
        }

        public void setTransactionType(TransactionType transactionType) {
                this.transactionType = transactionType;
        }

        public String getAccountId() {
                return accountId;
        }

        public void setAccountId(String accountId) {
                this.accountId = accountId;
        }

        public String getCategoryId() {
                return categoryId;
        }

        public void setCategoryId(String categoryId) {
                this.categoryId = categoryId;
        }

        @Override
        public boolean equals(Object o) {
                if (this == o) return true;
                if (o == null || getClass() != o.getClass()) return false;
                Transaction that = (Transaction) o;
                return Objects.equals(transactionId, that.transactionId) && Objects.equals(description, that.description) && Objects.equals(amount, that.amount) && Objects.equals(transactionDate, that.transactionDate) && transactionType == that.transactionType && Objects.equals(accountId, that.accountId) && Objects.equals(categoryId, that.categoryId);
        }

        @Override
        public int hashCode() {
                return Objects.hash(transactionId, description, amount, transactionDate, transactionType, accountId, categoryId);
        }

        @Override
        public String toString() {
                return "Transaction{" +
                        "transactionId='" + transactionId + '\'' +
                        ", description='" + description + '\'' +
                        ", amount=" + amount +
                        ", transactionDate=" + transactionDate +
                        ", transactionType=" + transactionType +
                        ", accountId='" + accountId + '\'' +
                        ", categoryId='" + categoryId + '\'' +
                        '}';
        }
}
