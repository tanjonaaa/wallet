package org.wallet.Services;

import org.springframework.stereotype.Service;
import org.wallet.Components.TransactionComponent;
import org.wallet.CrudOperations.TransactionCrudOperations;
import org.wallet.Models.Transaction;

import java.time.LocalDateTime;
import java.util.List;

@Service
public class TransactionService {
    private final TransactionCrudOperations transactionCrudOperations;

    public TransactionService(TransactionCrudOperations transactionCrudOperations) {
        this.transactionCrudOperations = transactionCrudOperations;
    }

    public List<Transaction> saveAll(List<Transaction> toSave) {
        return transactionCrudOperations.saveAll(toSave);
    }

    public Transaction delete(Transaction toDelete) {
        return transactionCrudOperations.delete(toDelete);
    }

    public List<TransactionComponent> getTransactionByAccountId(String accountId, LocalDateTime date) {
        return transactionCrudOperations.getTransactionByAccountId(accountId, date);
    }

    public Transaction getById(String id) {
        return transactionCrudOperations.getById(id);
    }
}
