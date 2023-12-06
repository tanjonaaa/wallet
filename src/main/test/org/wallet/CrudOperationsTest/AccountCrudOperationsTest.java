package org.wallet.CrudOperationsTest;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.wallet.CrudOperations.AccountCrudOperations;
import org.wallet.Models.Account;

import java.util.List;
import static org.junit.jupiter.api.Assertions.*;

public class AccountCrudOperationsTest {
    private AccountCrudOperations crudOperations;

    @BeforeEach
    public void setUp() {
        // Initialize the CRUD operations object
        crudOperations = new AccountCrudOperations();
    }

    @Test
    public void testSave() {
        Account account = new Account(100.0, "ae98f277-2c9f-49af-a1cc-3fddef7f8caa");

        // Test save operation
        Account savedAccount = crudOperations.save(account);
        assertNotNull(savedAccount.getAccountId()); // Ensure that the saved account has an ID
    }

    @Test
    public void testFindAll() {
        // Test findAll operation
        List<Account> accounts = crudOperations.findAll();
        assertNotNull(accounts); // Ensure accounts list is not null
        // Add more assertions based on the expected behavior of findAll()
    }

    @Test
    public void testSaveAll() {
        Account account1 = new Account(200.0, "ae98f277-2c9f-49af-a1cc-3fddef7f8caa");
        Account account2 = new Account(300.0, "ae98f277-2c9f-49af-a1cc-3fddef7f8caa");

        List<Account> accountsToSave = List.of(account1, account2);

        // Test saveAll operation
        List<Account> savedAccounts = crudOperations.saveAll(accountsToSave);
        assertNotNull(savedAccounts); // Ensure savedAccounts list is not null
        assertEquals(2, savedAccounts.size()); // Ensure correct number of accounts are saved
        // Add more assertions based on the expected behavior of saveAll()
    }

    @Test
    public void testDelete() {
        Account account = new Account(100.0, "ae98f277-2c9f-49af-a1cc-3fddef7f8caa");

        // Save the account first
        Account savedAccount = crudOperations.save(account);

        // Test delete operation
        Account deletedAccount = crudOperations.delete(savedAccount);
        assertEquals(savedAccount, deletedAccount); // Ensure the deleted account matches the saved one
    }
}
