package org.wallet.CrudOperations;

import org.wallet.Components.TransactionComponent;
import org.wallet.Models.Category;
import org.wallet.Models.CategoryAndAmount;
import org.wallet.connectionDB.ConnectionDB;

import java.sql.*;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class CategoryCrudOperations implements CrudOperations<Category>{
    TransactionCrudOperations transactionCrud = new TransactionCrudOperations();
    @Override
    public List<Category> findAll() {
        return null;
    }

    @Override
    public List<Category> saveAll(List<Category> toSave) {
        return null;
    }

    @Override
    public Category save(Category toSave) {
        return null;
    }

    @Override
    public Category delete(Category toDelete) {
        return null;
    }

    private boolean checkDateIsBetween(LocalDate startDate, LocalDate endDate){
        return
    }

    public List<CategoryAndAmount> getSumOfTransactions(String accountId, LocalDate startDate, LocalDate endDate){
        Connection connection = ConnectionDB.getConnection();
        List<CategoryAndAmount> result = new ArrayList<>();

        List<TransactionComponent> transactions = transactionCrud.getTransactionByAccountId(accountId);

        if(transactions.size() != 0){
            transactions = transactions.stream().filter(
                    transaction ->
            );
        }

        return result;
    }
}
