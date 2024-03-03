package org.wallet.Controller;

import lombok.AllArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.wallet.Models.Currency;
import org.wallet.Services.CurrencyService;

import java.util.List;

@RestController
@RequestMapping("currencies")
@AllArgsConstructor
public class CurrencyController {
    private final CurrencyService service;

    @GetMapping("")
    public ResponseEntity<List<Currency>> getAllCurrencies(){
        return ResponseEntity.ok(this.service.getAll());
    }

    @PutMapping("")
    public ResponseEntity<List<Currency>> createOrUpdateCurrencies(@RequestBody List<Currency> currencies){
        return ResponseEntity.ok(this.service.createOrUpdate(currencies));
    }
}
