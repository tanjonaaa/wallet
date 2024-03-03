package org.wallet.Services;

import org.springframework.stereotype.Service;
import org.wallet.Components.TransferHistoryComponent;
import org.wallet.CrudOperations.TransferHistoryCrudOperations;
import org.wallet.Models.TransferHistory;

import java.time.LocalDateTime;
import java.util.List;

@Service
public class TransferHistoryService {
    private final TransferHistoryCrudOperations transferHistoryCrudOperations;

    public TransferHistoryService(TransferHistoryCrudOperations transferHistoryCrudOperations) {
        this.transferHistoryCrudOperations = transferHistoryCrudOperations;
    }

    public List<TransferHistory> saveAll(List<TransferHistory> toSave) {
        return transferHistoryCrudOperations.saveAll(toSave);
    }


    public TransferHistory save(TransferHistory toSave) {
        return transferHistoryCrudOperations.save(toSave);
    }

    public TransferHistory delete(TransferHistory toDelete) {
        return transferHistoryCrudOperations.delete(toDelete);
    }

    public TransferHistory getByCreditTransaction(String id, LocalDateTime limit) {
        return transferHistoryCrudOperations.getByCreditTransaction(id, limit);
    }

    public List<TransferHistoryComponent> getHistory(LocalDateTime startDate, LocalDateTime endDate) {
        return transferHistoryCrudOperations.getHistory(startDate, endDate);
    }
}
