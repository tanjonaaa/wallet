package org.wallet.Controller;

import lombok.AllArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.wallet.Components.AccountComponent;
import org.wallet.Models.Account;
import org.wallet.Services.AccountService;

import java.time.LocalDateTime;
import java.util.List;

@RestController
@RequestMapping("accounts")
@AllArgsConstructor
public class AccountController {

    private final AccountService accountService;
    @GetMapping("/{accountId}")
    public ResponseEntity<AccountComponent> getAccountById(@PathVariable String accountId) {
        AccountComponent accountComponent = accountService.getAccountById(accountId);
        return ResponseEntity.ok(accountComponent);
    }

    @GetMapping("")
    public ResponseEntity<List<Account>> getAllAccounts(){
        return ResponseEntity.ok(this.accountService.getAll());
    }

    @PutMapping("")
    public ResponseEntity<List<Account>> createOrUpdateAccounts(@RequestBody List<Account> accounts){
        return ResponseEntity.ok(this.accountService.createOrUpdate(accounts));
    }

    @GetMapping("/exchange-rate/{transactionTimestamp}/{calculationType}")
    public ResponseEntity<Double> calculateExchangeRate(@PathVariable LocalDateTime transactionTimestamp, @PathVariable String calculationType) {
        Double exchangeRate = accountService.calculateExchangeRate(transactionTimestamp, calculationType);
        return ResponseEntity.ok(exchangeRate);
    }
}
