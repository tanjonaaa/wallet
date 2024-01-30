package org.wallet.CrudOperationsTest;

import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.wallet.CrudOperations.AccountCrudOperations;
import org.wallet.Models.Account;

import java.util.ArrayList;
import java.util.List;
import static org.junit.jupiter.api.Assertions.*;
import static org.wallet.Models.Types.AccountType.BANK;
import static org.wallet.Models.Types.AccountType.CASH;

public class AccountCrudOperationsTest {
    public  AccountCrudOperations crudOperations;

    @BeforeEach
    public void setUp() {
        // Initialize the CRUD operations object
        crudOperations = new AccountCrudOperations();
    }

    @Test
    public void testSave() {
        Account account = new Account("5AS4A8S-adkndkad4-dqsdjq40","4a292832-970d-48b6-b553-afc2a8e0c8ca",BANK);

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
        Account account1 = new Account("tETS","4a292832-970d-48b6-b553-afc2a8e0c8ca",BANK);
        Account account2 = new Account("TESTD", "1ff686b7-4314-4995-8b29-75be1f5f89e0" ,CASH);

        List<Account> accountsToSave = List.of(account1, account2);

        // Test saveAll operation
        List<Account> savedAccounts = crudOperations.saveAll(accountsToSave);
        assertNotNull(savedAccounts); // Ensure savedAccounts list is not null
        assertEquals(2, savedAccounts.size()); // Ensure correct number of accounts are saved
        // Add more assertions based on the expected behavior of saveAll()
    }

    @Test
    public void testDelete() {
        Account account = new Account("compte courant","1ff686b7-4314-4995-8b29-75be1f5f89e0", CASH );
        // Save the account first
        Account savedAccount = crudOperations.save(account);
        // Test delete operation
        Account deletedAccount = crudOperations.delete(savedAccount);
        assertEquals(savedAccount, deletedAccount); // Ensure the deleted account matches the saved one
    }
}
