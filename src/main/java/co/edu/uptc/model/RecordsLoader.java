package co.edu.uptc.model;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;

public class RecordsLoader {

    private static final String DB_URL = "jdbc:sqlite:trivia_game.db";

    public static void main(String[] args) {
        List<Record> records = loadRecordsFromDatabase();
        records.forEach(System.out::println);
    }

    public static List<Record> loadRecordsFromDatabase() {
        List<Record> records = new ArrayList<>();
        try (Connection connection = DriverManager.getConnection(DB_URL);
                Statement statement = connection.createStatement()) {
            String sql = "SELECT name, correct_answers FROM records";
            ResultSet resultSet = statement.executeQuery(sql);
            while (resultSet.next()) {
                String name = resultSet.getString("name");
                int correctanswers = resultSet.getInt("correct_answers");
                Record record = new Record(name, correctanswers);
                records.add(record);
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return records;
    }
}