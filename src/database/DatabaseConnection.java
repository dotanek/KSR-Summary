package database;

import subject.Attribute;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;

public class DatabaseConnection {
    private Connection connection;

    private String CONNECTION_STRING = "jdbc:sqlserver://localhost:1433;databaseName=fifa;";
    private String CONNECTION_USER = "javauser";
    private String CONNECTION_PASSWORD = "password";
    private String TABLE_NAME = "prepared_dataset";
    private String[] ATTRIBURES_NAMES = {
            "Age",
            "Value",
            "Wage",
            "Special",
            "Height",
            "Weight",
            "SprintSpeed",
            "Agility",
            "Stamina",
            "Strength"
    };

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

    public List<Attribute> getAttributes() {
        List<Attribute> attributes = new ArrayList<>();

        try (Statement statement = connection.createStatement()) {
            StringBuilder query = new StringBuilder();

            query.append("SELECT ");
            for (int i = 0; i < ATTRIBURES_NAMES.length; i++) {
                String name = ATTRIBURES_NAMES[i];
                attributes.add(new Attribute(name));
               query.append(((i == 0) ? "" : ", ")+name);
            }
            query.append(" FROM "+TABLE_NAME);

            ResultSet resultSet = statement.executeQuery(query.toString());
            while(resultSet.next()) {
                for (int i = 0; i < ATTRIBURES_NAMES.length; i++) {
                    int e = 0;
                    attributes.get(i).getValues().add(resultSet.getDouble(ATTRIBURES_NAMES[i]));
                }
            }

        } catch (SQLException throwables) {
            throwables.printStackTrace();
        }

        return attributes;
    }
}
