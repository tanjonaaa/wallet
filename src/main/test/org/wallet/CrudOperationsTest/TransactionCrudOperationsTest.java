package org.wallet.CrudOperationsTest;

import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.wallet.CrudOperations.TransactionCrudOperations;
import org.wallet.Models.Transaction;

import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;
import static org.junit.jupiter.api.Assertions.*;

public class TransactionCrudOperationsTest {
    private TransactionCrudOperations crudOperations;

    @BeforeEach
    public void setUp() {
        // Initialize the CRUD operations object
        crudOperations = new TransactionCrudOperations();
    }

    @AfterEach
    public void tearDown() {
        // Clean up any test data after each test case if needed
    }

    @Test
    public void testSave() {
        Transaction transaction = new Transaction(
                "123456",
                "Test Transaction",
                100.0,
                LocalDateTime.now(),
                "income",
                "1624c5be-911f-11ee-b9d1-0242ac120002"
        );

        // Test save operation
        Transaction savedTransaction = crudOperations.save(transaction);
        assertNotNull(savedTransaction.getTransaction_id()); // Ensure that the saved transaction has an ID
    }

    @Test
    public void testFindAll() {
        // Test findAll operation
        List<Transaction> transactions = crudOperations.findAll();
        assertNotNull(transactions); // Ensure transactions list is not null
        // Add more assertions based on the expected behavior of findAll()
    }

    @Test
    public void testSaveAll() {
        List<Transaction> transactionsToSave = new ArrayList<>();
        Transaction transaction1 = new Transaction(
                "123456",
                "Test Transaction 1",
                100.0,
                LocalDateTime.now(),
                "income",
                "1624c5be-911f-11ee-b9d1-0242ac120002"
        );

        Transaction transaction2 = new Transaction(
                "789012",
                "Test Transaction 2",
                200.0,
                LocalDateTime.now(),
                "expense",
                "1624c5be-911f-11ee-b9d1-0242ac120002"
        );

        transactionsToSave.add(transaction1);
        transactionsToSave.add(transaction2);

        // Test saveAll operation
        List<Transaction> savedTransactions = crudOperations.saveAll(transactionsToSave);
        assertNotNull(savedTransactions); // Ensure savedTransactions list is not null
        assertEquals(2, savedTransactions.size()); // Ensure correct number of transactions are saved
        // Add more assertions based on the expected behavior of saveAll()
    }

    @Test
    public void testDelete() {
        Transaction transaction = new Transaction(
                "123456",
                "Test Transaction",
                100.0,
                LocalDateTime.now(),
                "income",
                "1624c5be-911f-11ee-b9d1-0242ac120002"
        );

        // Save the transaction first
        Transaction savedTransaction = crudOperations.save(transaction);

        // Test delete operation
        Transaction deletedTransaction = crudOperations.delete(savedTransaction);
        assertEquals(savedTransaction, deletedTransaction); // Ensure the deleted transaction matches the saved one
    }
}
