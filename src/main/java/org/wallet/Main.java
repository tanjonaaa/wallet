package org.wallet;

import org.wallet.connectionDB.ConnectionDB;

import java.util.List;

// Press Shift twice to open the Search Everywhere dialog and type `show whitespaces`,
// then press Enter. You can now see whitespace characters in your code.
public class Main {
    public static void main(String[] args) {
        ConnectionDB.getConnection();
        ConnectionDB.getConnection();
        ConnectionDB.getConnection();
        ConnectionDB.getConnection();
        ConnectionDB.closeConnection();
        ConnectionDB.getConnection();
    }
}
