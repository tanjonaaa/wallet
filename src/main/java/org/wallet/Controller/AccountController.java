package org.wallet.Controller;

import lombok.AllArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.wallet.Models.Account;
import org.wallet.Services.AccountService;

import java.util.List;

@RestController
@RequestMapping("accounts")
@AllArgsConstructor
public class AccountController {

    private final AccountService accountService;

    @GetMapping("")
    public ResponseEntity<List<Account>> getAllAccounts(){
        return ResponseEntity.ok(this.accountService.getAll());
    }

    @PutMapping("")
    public ResponseEntity<List<Account>> createOrUpdateAccounts(@RequestBody List<Account> accounts){
        return ResponseEntity.ok(this.accountService.createOrUpdate(accounts));
    }
}
