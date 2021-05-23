package database;

import java.sql.*;

public class DatabaseConnection {
    private Connection connection;

    private String CONNECTION_STRING = "jdbc:sqlserver://localhost:1433;databaseName=fifa;";
    private String CONNECTION_USER = "javauser";
    private String CONNECTION_PASSWORD = "password";

    public boolean connect() {
        try {
            Class.forName("com.microsoft.sqlserver.jdbc.SQLServerDriver");
            connection = DriverManager.getConnection(CONNECTION_STRING,CONNECTION_USER,CONNECTION_PASSWORD);
            return true;

        } catch (ClassNotFoundException e) {
            e.printStackTrace();
            return false;
        } catch (SQLException throwables) {
            throwables.printStackTrace();
            return false;
        }
    }
}
