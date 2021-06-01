package database;

import subject.Subject;

import java.sql.*;
import java.util.*;

public class DatabaseConnection {
    private Connection connection;

    private final String CONNECTION_STRING = "jdbc:sqlserver://localhost:1433;databaseName=fifa;";
    private final String CONNECTION_USER = "javauser";
    private final String CONNECTION_PASSWORD = "password";
    private final String TABLE_NAME = "dataset2";
    private final String SUBJECTS_NAME = "PIŁKARZE";
    private final Map<String,String> ATTRIBURES_NAMES = new HashMap<>() {{
        put("Age","WIEK");
        put("Value","WYCENA");
        put("Wage", "WYNAGRODZENIE");
        put("Special", "PODSUMOWANIE UMIEJĘTNOŚCI");
        put("Height", "WZROST"); // I forgot to convert feet to cm while importing the data, yikes.
        put("Weight","WAGA");
        put("SprintSpeed","SZYBKOŚĆ SPRINTU");
        put("Agility","ZWINNOŚĆ");
        put("Stamina","WYTRZYMAŁOŚĆ");
        put("Strength","SIŁA");
    }};

    private final String BONUS_ATTRIBUTE_NAME = "Nationality";

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

    public List<Subject> getSubjects() {
        List<Subject> subjects = new ArrayList<>();

        try (Statement statement = connection.createStatement()) {
            String query = "SELECT " +String.join(",", ATTRIBURES_NAMES.keySet()) + "," + BONUS_ATTRIBUTE_NAME + " FROM " + TABLE_NAME;

            ResultSet resultSet = statement.executeQuery(query);
            while(resultSet.next()) {
                Map<String,Double> attributes = new HashMap<>();
                for (String key : ATTRIBURES_NAMES.keySet()) {
                    attributes.put(ATTRIBURES_NAMES.get(key),resultSet.getDouble(key));
                }

                String bonusAttribute = resultSet.getString(BONUS_ATTRIBUTE_NAME);
                subjects.add(new Subject(attributes,bonusAttribute.toUpperCase(Locale.ROOT)));
            }

        } catch (SQLException throwables) {
            throwables.printStackTrace();
        }

        return subjects;
    }
}
