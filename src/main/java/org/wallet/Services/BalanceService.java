package org.wallet.Services;

import org.springframework.stereotype.Service;
import org.wallet.CrudOperations.BalanceCrudOperations;
import org.wallet.Models.Balance;

import java.time.LocalDateTime;
import java.util.List;

@Service
public class BalanceService {
    private final BalanceCrudOperations balanceCrudOperations;

    public BalanceService(BalanceCrudOperations balanceCrudOperations) {
        this.balanceCrudOperations = balanceCrudOperations;
    }

    public List<Balance> saveAll(List<Balance> toSave) {
        return balanceCrudOperations.saveAll(toSave);
    }

    public Balance delete(Balance toDelete) {
        return balanceCrudOperations.delete(toDelete);
    }

    public Balance getLastBalanceOfAccount(String accountId) {
        return balanceCrudOperations.getLastBalanceOfAccount(accountId);
    }

    public double getAmountByDate(String accountId, LocalDateTime amountUpdated) {
        return balanceCrudOperations.getAmountByDate(accountId, amountUpdated);
    }

    public double getAmountWithChangeRate(String accountId, LocalDateTime amountUpdated) {
        return balanceCrudOperations.getAmountWithChangeRate(accountId, amountUpdated);
    }

    public List<Balance> getBalanceHistory(String accountId, LocalDateTime startDate, LocalDateTime endDate) {
        return balanceCrudOperations.getBalanceHistory(accountId, startDate, endDate);
    }
}
