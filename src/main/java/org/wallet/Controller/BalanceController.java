package org.wallet.Controller;

import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.wallet.Services.BalanceService;
import org.wallet.Models.Balance;
import java.time.LocalDateTime;
import java.util.List;

@RestController
@RequestMapping("balances")
public class BalanceController {

    private final BalanceService balanceService;

    public BalanceController(BalanceService balanceService) {
        this.balanceService = balanceService;
    }

    @PostMapping
    public ResponseEntity<List<Balance>> saveAll(@RequestBody List<Balance> toSave) {
        List<Balance> savedBalances = balanceService.saveAll(toSave);
        return ResponseEntity.ok(savedBalances);
    }

    @GetMapping("/last/{accountId}")
    public ResponseEntity<Balance> getLastBalanceOfAccount(@PathVariable String accountId) {
        Balance lastBalance = balanceService.getLastBalanceOfAccount(accountId);
        return ResponseEntity.ok(lastBalance);
    }

    @GetMapping("/amount/{accountId}")
    public ResponseEntity<Double> getAmountByDate(@PathVariable String accountId, @RequestParam LocalDateTime amountUpdated) {
        double amount = balanceService.getAmountByDate(accountId, amountUpdated);
        return ResponseEntity.ok(amount);
    }

    @GetMapping("/amount-with-change-rate/{accountId}")
    public ResponseEntity<Double> getAmountWithChangeRate(@PathVariable String accountId, @RequestParam LocalDateTime amountUpdated) {
        double amount = balanceService.getAmountWithChangeRate(accountId, amountUpdated);
        return ResponseEntity.ok(amount);
    }

    @GetMapping("/history/{accountId}")
    public ResponseEntity<List<Balance>> getBalanceHistory(@PathVariable String accountId, @RequestParam LocalDateTime startDate, @RequestParam LocalDateTime endDate) {
        List<Balance> balanceHistory = balanceService.getBalanceHistory(accountId, startDate, endDate);
        return ResponseEntity.ok(balanceHistory);
    }
}

