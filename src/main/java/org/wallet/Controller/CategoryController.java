package org.wallet.Controller;

import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.wallet.Services.CategoryService;
import org.wallet.Models.CategoryAndAmount;
import org.wallet.Models.Types.TransactionType;
import java.time.LocalDate;
import java.util.List;

@RestController
@RequestMapping("categories")
public class CategoryController {

    private final CategoryService categoryService;

    public CategoryController(CategoryService categoryService) {
        this.categoryService = categoryService;
    }

    @GetMapping("/id")
    public ResponseEntity<String> getCategoryId(@RequestParam String name, @RequestParam TransactionType type) {
        String categoryId = categoryService.getCategoryId(name, type);
        return ResponseEntity.ok(categoryId);
    }

    @GetMapping("/sum")
    public ResponseEntity<List<CategoryAndAmount>> getSumOfTransactions(@RequestParam String accountId, @RequestParam LocalDate startDate, @RequestParam LocalDate endDate) {
        List<CategoryAndAmount> sumOfTransactions = categoryService.getSumOfTransactions(accountId, startDate, endDate);
        return ResponseEntity.ok(sumOfTransactions);
    }
}
