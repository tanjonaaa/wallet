package org.wallet.Services;

import org.springframework.stereotype.Service;
import org.wallet.CrudOperations.CategoryCrudOperations;
import org.wallet.Models.CategoryAndAmount;
import org.wallet.Models.Types.TransactionType;

import java.time.LocalDate;
import java.util.List;

@Service
public class CategoryService {
    private final CategoryCrudOperations categoryCrudOperations;

    public CategoryService(CategoryCrudOperations categoryCrudOperations) {
        this.categoryCrudOperations = categoryCrudOperations;
    }

    public String getCategoryId(String name, TransactionType type) {
        return categoryCrudOperations.getCategoryId(name, type);
    }


    public List<CategoryAndAmount> getSumOfTransactions(String accountId, LocalDate startDate, LocalDate endDate) {
        return categoryCrudOperations.getSumOfTransactions(accountId, startDate, endDate);
    }
}
