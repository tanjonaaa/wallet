package org.wallet.Models;

import lombok.*;
import org.wallet.Annotations.Column;
import org.wallet.Annotations.CustomType;
import org.wallet.Annotations.Id;
import org.wallet.Annotations.Model;
import org.wallet.Models.Types.TransactionType;

import java.util.Objects;

@Builder
@Model(table = "categories")
public class Category {
    @Id
    @Column(name = "category_id")
    private String categoryId;
    @Column(name = "category_name")
    @NonNull
    private String categoryName;
    @Column(name = "transaction_type")
    @CustomType(type_class = "TransactionType")
    @NonNull
    private TransactionType transactionType;

    public Category(String categoryId, @NonNull String categoryName, @NonNull TransactionType transactionType) {
        this.categoryId = categoryId;
        this.categoryName = categoryName;
        this.transactionType = transactionType;
    }

    public Category() {
    }

    public String getCategoryId() {
        return categoryId;
    }

    public void setCategoryId(String categoryId) {
        this.categoryId = categoryId;
    }

    public String getCategoryName() {
        return categoryName;
    }

    public void setCategoryName(String categoryName) {
        this.categoryName = categoryName;
    }

    public TransactionType getTransactionType() {
        return transactionType;
    }

    public void setTransactionType(TransactionType transactionType) {
        this.transactionType = transactionType;
    }

    @Override
    public String toString() {
        return "Category{" +
                "categoryId='" + categoryId + '\'' +
                ", categoryName='" + categoryName + '\'' +
                ", transactionType=" + transactionType +
                '}';
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Category category = (Category) o;
        return Objects.equals(categoryId, category.categoryId) && Objects.equals(categoryName, category.categoryName) && transactionType == category.transactionType;
    }

    @Override
    public int hashCode() {
        return Objects.hash(categoryId, categoryName, transactionType);
    }
}

