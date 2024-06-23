package org.example.ex1;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

public class ConnectionPool {

    private Connection connection;
    public Connection GetConnectionString() {
        if (connection == null) {
            String connectionString = "jdbc:sqlserver://localhost:1434;database=LIBRARY;encrypt=true;trustServerCertificate=true;";
            try {
                Class.forName("com.mysql.cj.jdbc.Driver");
                connection = DriverManager.getConnection(connectionString, "User1", "1234");
            } catch (ClassNotFoundException | SQLException e) {
                throw new RuntimeException(e);
            }
        }
        return connection;
    }
}
