package org.example;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

public class H2Connection implements DataBaseConnection {
    private static final String URL = "jdbc:h2:~/treeDB";
    private static final String USER = "userTree";
    private static final String PASSWORD = "pass";

    @Override
    public Connection connect() {
        try {

            return DriverManager.getConnection(URL, USER, PASSWORD);
        } catch (SQLException e) {
            e.printStackTrace();
            throw new RuntimeException("Error connecting to H2 database");
        }
    }
}
