package org.wallet.CrudOperationsTest;

import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.wallet.CrudOperations.CurrencyCrudOperations;
import org.wallet.Models.Currency;

import java.util.ArrayList;
import java.util.List;

public class CurrencyCrudOperationsTest {
    private CurrencyCrudOperations crudOperations;

    @BeforeEach
    public void setUp() {
        // You might need to set up your test database or use a test database connection here
        // Initialize the CRUD operations object
        crudOperations = new CurrencyCrudOperations();
    }

    @AfterEach
    public void tearDown() {
        // Clean up any test data after each test case if needed
    }

    @Test
    public void testSave() {
        Currency currency = new Currency();
        currency.setName("TestCurrency");
        currency.setSymbol("TST");

        // Test save operation
        Currency savedCurrency = crudOperations.save(currency);
        Assertions.assertNotNull(savedCurrency.getCurrencyId()); // Ensure that the saved currency has an ID
    }

    @Test
    public void testFindAll() {
        // Test findAll operation
        List<Currency> currencies = crudOperations.findAll();
        Assertions.assertNotNull(currencies); // Ensure currencies list is not null
        // Add more assertions based on the expected behavior of findAll()
    }

    @Test
    public void testSaveAll() {
        List<Currency> currenciesToSave = new ArrayList<>();
        Currency currency1 = new Currency();
        currency1.setName("TestCurrency1");
        currency1.setSymbol("TST1");

        Currency currency2 = new Currency();
        currency2.setName("TestCurrency2");
        currency2.setSymbol("TST2");

        currenciesToSave.add(currency1);
        currenciesToSave.add(currency2);

        // Test saveAll operation
        List<Currency> savedCurrencies = crudOperations.saveAll(currenciesToSave);
        Assertions.assertNotNull(savedCurrencies); // Ensure savedCurrencies list is not null
        Assertions.assertEquals(2, savedCurrencies.size()); // Ensure correct number of currencies are saved
        // Add more assertions based on the expected behavior of saveAll()
    }

    @Test
    public void testDelete() {
        Currency currency = new Currency();
        currency.setName("TestCurrency");
        currency.setSymbol("TST");

        // Save the currency first
        Currency savedCurrency = crudOperations.save(currency);

        // Test delete operation
        Currency deletedCurrency = crudOperations.delete(savedCurrency);
        Assertions.assertEquals(savedCurrency, deletedCurrency); // Ensure the deleted currency matches the saved one
    }
}
