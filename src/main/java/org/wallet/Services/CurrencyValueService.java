package org.wallet.Services;

import org.springframework.stereotype.Service;
import org.wallet.CrudOperations.CurrencyValueCrudOperations;
import org.wallet.Models.CurrencyValue;

import java.time.LocalDate;
import java.util.List;

@Service
public class CurrencyValueService {
    private final CurrencyValueCrudOperations currencyValueCrudOperations;

    public CurrencyValueService(CurrencyValueCrudOperations currencyValueCrudOperations) {
        this.currencyValueCrudOperations = currencyValueCrudOperations;
    }

    public List<CurrencyValue> saveAll(List<CurrencyValue> toSave) {
        return currencyValueCrudOperations.saveAll(toSave);
    }

    public CurrencyValue delete(CurrencyValue toDelete) {
        return currencyValueCrudOperations.delete(toDelete);
    }

    public double getChangeRate(LocalDate date) {
        return currencyValueCrudOperations.getChangeRate(date);
    }
}
