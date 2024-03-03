package org.wallet.Models;

import lombok.*;
import org.wallet.Annotations.Column;
import org.wallet.Annotations.Id;
import org.wallet.Annotations.Model;

import java.time.LocalDate;
import java.time.LocalDateTime;
import java.util.Objects;

@Builder
@Model(table = "currency_value")
public class CurrencyValue {
    @Id
    @Column(name = "currency_value_id")
    private String currencyValueId;
    @Column(name = "id_currency_source")
    @NonNull
    private String idCurrencySource;
    @Column(name = "id_currency_destination")
    @NonNull
    private String idCurrencyDestination;
    @Column(name = "change_rate")
    @NonNull
    private Double changeRate;
    @Column(name = "currency_value_date")
    @org.wallet.Annotations.LocalDateTime
    @NonNull
    private LocalDateTime currencyValueDate;

    public CurrencyValue(String currencyValueId, @NonNull String idCurrencySource, @NonNull String idCurrencyDestination,
                         @NonNull Double changeRate, @NonNull LocalDateTime currencyValueDate) {
        this.currencyValueId = currencyValueId;
        this.idCurrencySource = idCurrencySource;
        this.idCurrencyDestination = idCurrencyDestination;
        this.changeRate = changeRate;
        this.currencyValueDate = currencyValueDate;
    }

    public CurrencyValue() {
    }

    public String getCurrencyValueId() {
        return currencyValueId;
    }

    public void setCurrencyValueId(String currencyValueId) {
        this.currencyValueId = currencyValueId;
    }

    public String getIdCurrencySource() {
        return idCurrencySource;
    }

    public void setIdCurrencySource(String idCurrencySource) {
        this.idCurrencySource = idCurrencySource;
    }

    public String getIdCurrencyDestination() {
        return idCurrencyDestination;
    }

    public void setIdCurrencyDestination(String idCurrencyDestination) {
        this.idCurrencyDestination = idCurrencyDestination;
    }

    public Double getChangeRate() {
        return changeRate;
    }

    public void setChangeRate(Double changeRate) {
        this.changeRate = changeRate;
    }

    public LocalDateTime getCurrencyValueDate() {
        return currencyValueDate;
    }

    public void setCurrencyValueDate(LocalDateTime currencyValueDate) {
        this.currencyValueDate = currencyValueDate;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        CurrencyValue that = (CurrencyValue) o;
        return Objects.equals(currencyValueId, that.currencyValueId)
                && Objects.equals(idCurrencySource, that.idCurrencySource)
                && Objects.equals(idCurrencyDestination, that.idCurrencyDestination)
                && Objects.equals(changeRate, that.changeRate)
                && Objects.equals(currencyValueDate, that.currencyValueDate);
    }

    @Override
    public int hashCode() {
        return Objects.hash(currencyValueId, idCurrencySource, idCurrencyDestination, changeRate, currencyValueDate);
    }

    @Override
    public String toString() {
        return "CurrencyValue{" +
                "currencyValueId='" + currencyValueId + '\'' +
                ", idCurrencySource='" + idCurrencySource + '\'' +
                ", idCurrencyDestination='" + idCurrencyDestination + '\'' +
                ", changeRate=" + changeRate +
                ", currencyValueDate=" + currencyValueDate +
                '}';
    }
}
