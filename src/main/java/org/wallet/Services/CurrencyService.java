package org.wallet.Services;

import org.springframework.stereotype.Service;
import org.wallet.CrudOperations.CurrencyCrudOperations;
import org.wallet.Models.Currency;

import java.util.List;

@Service
public class CurrencyService {
    private final CurrencyCrudOperations crudOperations;

    public CurrencyService(CurrencyCrudOperations crudOperations) {
        this.crudOperations = crudOperations;
    }

    public List<Currency> getAll(){
        return this.crudOperations.findAll();
    }
    public List<Currency> createOrUpdate(List<Currency> currencies){
        return this.crudOperations.saveAll(currencies);
    }
}
