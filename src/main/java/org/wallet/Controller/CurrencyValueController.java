package org.wallet.Controller;

import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.wallet.Services.CurrencyValueService;
import org.wallet.Models.CurrencyValue;
import java.time.LocalDate;
import java.util.List;

@RestController
@RequestMapping("currency-values")
public class CurrencyValueController {

    private final CurrencyValueService currencyValueService;

    public CurrencyValueController(CurrencyValueService currencyValueService) {
        this.currencyValueService = currencyValueService;
    }

    @PostMapping
    public ResponseEntity<List<CurrencyValue>> saveAll(@RequestBody List<CurrencyValue> toSave) {
        List<CurrencyValue> savedCurrencyValues = currencyValueService.saveAll(toSave);
        return ResponseEntity.ok(savedCurrencyValues);
    }

    @GetMapping("/change-rate/{date}")
    public ResponseEntity<Double> getChangeRate(@PathVariable LocalDate date) {
        double changeRate = currencyValueService.getChangeRate(date);
        return ResponseEntity.ok(changeRate);
    }
}

