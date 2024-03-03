package org.wallet.Models;

import lombok.*;
import org.wallet.Annotations.Column;
import org.wallet.Annotations.Id;
import org.wallet.Annotations.Model;

import java.util.Objects;

@Model(table = "currency")
public class Currency {
    @Id
    @Column(name = "currency_id")
    private String currencyId;
    @Column(name = "name")
    @NonNull
    private String name;
    @Column(name = "currency_code")
    @NonNull
    private String currencyCode;

    public Currency(String currencyId, @NonNull String name, @NonNull String currencyCode) {
        this.currencyId = currencyId;
        this.name = name;
        this.currencyCode = currencyCode;
    }

    public Currency() {
    }

    public String getCurrencyId() {
        return currencyId;
    }

    public void setCurrencyId(String currencyId) {
        this.currencyId = currencyId;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getCurrencyCode() {
        return currencyCode;
    }

    public void setCurrencyCode(String currencyCode) {
        this.currencyCode = currencyCode;
    }

    @Override
    public String toString() {
        return "Currency{" +
                "currencyId='" + currencyId + '\'' +
                ", name='" + name + '\'' +
                ", currencyCode='" + currencyCode + '\'' +
                '}';
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Currency currency = (Currency) o;
        return Objects.equals(currencyId, currency.currencyId) && Objects.equals(name, currency.name) && Objects.equals(currencyCode, currency.currencyCode);
    }

    @Override
    public int hashCode() {
        return Objects.hash(currencyId, name, currencyCode);
    }
}


