package org.wallet.Services;

import org.springframework.stereotype.Service;
import org.wallet.Components.AccountComponent;
import org.wallet.CrudOperations.AccountCrudOperations;
import org.wallet.Models.Account;

import java.time.LocalDateTime;
import java.util.List;

@Service
public class AccountService {
    private final AccountCrudOperations accountCrudOperations;

    public AccountService(AccountCrudOperations accountCrudOperations) {
        this.accountCrudOperations = accountCrudOperations;
    }

    public Account delete(Account toDelete) {
        return accountCrudOperations.delete(toDelete);
    }

    public AccountComponent getAccountById(String id) {
        return accountCrudOperations.getAccountById(id);
    }

    public List<Account> getAll(){
        return this.accountCrudOperations.findAll();
    }

    public List<Account> createOrUpdate(List<Account> accounts){
        return this.accountCrudOperations.saveAll(accounts);
    }

    public Double calculateExchangeRate(LocalDateTime transactionTimestamp, String calculationType) {
        return accountCrudOperations.getExchangeRate(transactionTimestamp, calculationType);
    }
}
