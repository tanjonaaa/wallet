package org.wallet;

import org.wallet.CrudOperations.TransactionCrudOperations;
import org.wallet.Models.Transaction;

import java.util.List;

// Press Shift twice to open the Search Everywhere dialog and type `show whitespaces`,
// then press Enter. You can now see whitespace characters in your code.
public class Main {
    public static void main(String[] args) {
    // test for transaction
        TransactionCrudOperations transactionCrudOperations = new TransactionCrudOperations();

        // Create a new Transaction to save

        Transaction transactionToSave = new Transaction();
        transactionToSave.setDescription("Test Transaction");
        transactionToSave.setAmount(100.0);
        transactionToSave.setAccount_id("d701dc54-911f-11ee-b9d1-0242ac120002");
        transactionToSave.setTransaction_type("transfer");

        // Call the save method to insert the new transaction
        //Transaction savedTransaction = transactionCrudOperations.save(transactionToSave);
        // Call the findAll method to retrieve all transactions
        List<Transaction> transactions = transactionCrudOperations.findAll();

        System.out.println("List of Transactions:");
        for (Transaction transaction : transactions) {
            System.out.println("Transaction ID: " + transaction.getTransaction_id());
            System.out.println("Transaction Date: " + transaction.getTransaction_date());
            System.out.println("Amount: " + transaction.getAmount());
            System.out.println("Description: " + transaction.getDescription());
            System.out.println("Transaction Type: " + transaction.getTransaction_type());
            System.out.println("Account ID: " + transaction.getAccount_id());
            System.out.println("------------------------------");
        }

        System.out.println("You can delete it when you are sure it works well , XD");

    }
}


