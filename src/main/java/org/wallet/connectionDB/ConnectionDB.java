package org.wallet.connectionDB;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

public class ConnectionDB {
    private static Connection connection;

    public static Connection getConnection(){
        try {
            if(connection == null || connection.isClosed()){
                String url = System.getenv("DB_URL");
                String username = System.getenv("DB_USER");
                String password = System.getenv("DB_PASS");
                try{
                    connection = DriverManager.getConnection(
                            url,username,password
                    );
                }catch(SQLException e){
                    e.printStackTrace();
                }
            }
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
        return connection;
    }

    public static void closeConnection(){
        try {
            if(connection != null && !connection.isClosed()){
                connection.close();
            }
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
    }
}
