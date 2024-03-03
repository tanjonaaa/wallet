package org.wallet.Controller;

import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.wallet.Components.TransactionComponent;
import org.wallet.Services.TransactionService;
import org.wallet.Models.Transaction;
import java.time.LocalDateTime;
import java.util.List;

@RestController
@RequestMapping("transactions")
public class TransactionController {

    private final TransactionService transactionService;

    public TransactionController(TransactionService transactionService) {
        this.transactionService = transactionService;
    }

    @PostMapping
    public ResponseEntity<List<Transaction>> saveAll(@RequestBody List<Transaction> toSave) {
        List<Transaction> savedTransactions = transactionService.saveAll(toSave);
        return ResponseEntity.ok(savedTransactions);
    }

    @GetMapping("/account/{accountId}")
    public ResponseEntity<List<TransactionComponent>> getTransactionByAccountId(@PathVariable String accountId, @RequestParam(required = false) LocalDateTime date) {
        List<TransactionComponent> transactions = transactionService.getTransactionByAccountId(accountId, date);
        return ResponseEntity.ok(transactions);
    }

    @GetMapping("/{transactionId}")
    public ResponseEntity<Transaction> getById(@PathVariable String transactionId) {
        Transaction transaction = transactionService.getById(transactionId);
        return ResponseEntity.ok(transaction);
    }
}
