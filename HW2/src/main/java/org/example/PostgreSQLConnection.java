package org.example;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

public class PostgreSQLConnection implements DataBaseConnection {
    private static final String URL = "jdbc:postgresql://localhost/treeDB";
    private static final String USER = "userTrees";
    private static final String PASSWORD = "pass";

    @Override
    public Connection connect() {
        try {
            Class.forName("org.postgresql.Driver");
            return DriverManager.getConnection(URL, USER, PASSWORD);
        } catch (ClassNotFoundException | SQLException e) {
            e.printStackTrace();
            throw new RuntimeException("Error connecting to PostgreSQL database");
        }
    }
}
