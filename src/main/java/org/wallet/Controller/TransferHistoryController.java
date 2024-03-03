package org.wallet.Controller;

import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.wallet.Components.TransferHistoryComponent;
import org.wallet.Services.TransferHistoryService;
import org.wallet.Models.TransferHistory;
import java.time.LocalDateTime;
import java.util.List;

@RestController
@RequestMapping("transfer-history")
public class TransferHistoryController {

    private final TransferHistoryService transferHistoryService;

    public TransferHistoryController(TransferHistoryService transferHistoryService) {
        this.transferHistoryService = transferHistoryService;
    }

    @PostMapping
    public ResponseEntity<List<TransferHistory>> saveAll(@RequestBody List<TransferHistory> toSave) {
        List<TransferHistory> savedTransferHistories = transferHistoryService.saveAll(toSave);
        return ResponseEntity.ok(savedTransferHistories);
    }

    @PostMapping("/single")
    public ResponseEntity<TransferHistory> save(@RequestBody TransferHistory toSave) {
        TransferHistory savedTransferHistory = transferHistoryService.save(toSave);
        return ResponseEntity.ok(savedTransferHistory);
    }

    @GetMapping("/credit/{transactionId}")
    public ResponseEntity<TransferHistory> getByCreditTransaction(@PathVariable String transactionId, @RequestParam LocalDateTime limit) {
        TransferHistory transferHistory = transferHistoryService.getByCreditTransaction(transactionId, limit);
        return ResponseEntity.ok(transferHistory);
    }

    @GetMapping
    public ResponseEntity<List<TransferHistoryComponent>> getHistory(@RequestParam LocalDateTime startDate, @RequestParam LocalDateTime endDate) {
        List<TransferHistoryComponent> transferHistory = transferHistoryService.getHistory(startDate, endDate);
        return ResponseEntity.ok(transferHistory);
    }
}

