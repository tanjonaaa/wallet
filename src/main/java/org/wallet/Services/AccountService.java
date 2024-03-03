package org.wallet.Services;

import org.springframework.stereotype.Service;
import org.wallet.CrudOperations.AccountCrudOperations;
import org.wallet.Models.Account;

import java.util.List;

@Service
public class AccountService {
    private final AccountCrudOperations accountCrudOperations;

    public AccountService(AccountCrudOperations accountCrudOperations) {
        this.accountCrudOperations = accountCrudOperations;
    }

    public List<Account> getAll(){
        return this.accountCrudOperations.findAll();
    }

    public List<Account> createOrUpdate(List<Account> accounts){
        return this.accountCrudOperations.saveAll(accounts);
    }
}
