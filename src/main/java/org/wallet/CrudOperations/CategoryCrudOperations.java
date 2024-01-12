package org.wallet.CrudOperations;

import org.wallet.Components.TransactionComponent;
import org.wallet.Models.Category;
import org.wallet.Models.CategoryAndAmount;
import org.wallet.Models.Types.TransactionType;
import org.wallet.ConnectionDB.ConnectionDB;

import java.sql.*;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

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

    /*private boolean checkDateIsBetween(LocalDate startDate, LocalDate endDate){
        return
    }*/

    public String getCategoryId(String name, TransactionType type){
        Connection connection = ConnectionDB.getConnection();
        String result;
        String sql = "SELECT category_id FROM categories WHERE category_name = ? AND transaction_type = CAST(? AS transaction_type)";
        try {
            PreparedStatement statement = connection.prepareStatement(sql);

            statement.setString(1, name);
            statement.setString(2, String.valueOf(type));

            ResultSet resultSet = statement.executeQuery();
            resultSet.next();
            result = resultSet.getString("category_id");

            statement.close();
            connection.close();
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
        return result;
    }

    public List<CategoryAndAmount> getSumOfTransactions(String accountId, LocalDate startDate, LocalDate endDate) {
        List<CategoryAndAmount> result = new ArrayList<>();

        List<TransactionComponent> transactions = transactionCrud.getTransactionByAccountId(accountId, null);

        if (!transactions.isEmpty()) {
            System.out.println("Number of transactions retrieved: " + transactions.size());

            Map<String, Double> categorySumMap = transactions.stream()
                    .filter(transaction ->
                            transaction.getTransactionDate().toLocalDate().isAfter(startDate) &&
                                    transaction.getTransactionDate().toLocalDate().isBefore(endDate))
                    .collect(Collectors.groupingBy(TransactionComponent::getCategoryId,
                            Collectors.summingDouble(TransactionComponent::getAmount)));

            System.out.println("Number of categories with sum: " + categorySumMap.size());

            if (!categorySumMap.isEmpty()) {
                result.add(CategoryAndAmount.builder().matching(categorySumMap).build());
            } else {
                System.out.println("No transactions found for the specified entries.");
            }
        }
        return result;
    }
}

