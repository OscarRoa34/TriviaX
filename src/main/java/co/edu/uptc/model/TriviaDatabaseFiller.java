package co.edu.uptc.model;

import java.sql.*;
import java.net.HttpURLConnection;
import java.net.URL;
import java.io.BufferedReader;
import java.io.InputStreamReader;
import com.google.gson.*;

public class TriviaDatabaseFiller {
    private static final String DB_URL = "jdbc:sqlite:trivia_game.db";
    private static final String API_URL = "https://opentdb.com/api.php?amount=80&type=multiple";

    public static void main(String[] args) {
        try {
            String jsonResponse = fetchQuestionsFromAPI();
            System.out.println("Datos obtenidos de la API: " + jsonResponse);
            JsonObject jsonObject = JsonParser.parseString(jsonResponse).getAsJsonObject();
            JsonArray results = jsonObject.getAsJsonArray("results");
            Connection connection = DriverManager.getConnection(DB_URL);
            createTableIfNotExists(connection);
            insertQuestionsIntoDatabase(connection, results);
            connection.close();
            System.out.println("Preguntas insertadas correctamente.");
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    private static String fetchQuestionsFromAPI() throws Exception {
        URL url = new URL(API_URL);
        HttpURLConnection connection = (HttpURLConnection) url.openConnection();
        connection.setRequestMethod("GET");

        BufferedReader reader = new BufferedReader(new InputStreamReader(connection.getInputStream()));
        StringBuilder response = new StringBuilder();
        String line;

        while ((line = reader.readLine()) != null) {
            response.append(line);
        }
        reader.close();

        return response.toString();
    }

    private static void createTableIfNotExists(Connection connection) throws SQLException {
        String sql = "CREATE TABLE IF NOT EXISTS questions (" +
                "id INTEGER PRIMARY KEY AUTOINCREMENT, " +
                "question TEXT NOT NULL, " +
                "correct_answer TEXT NOT NULL, " +
                "incorrect_answer_1 TEXT, " +
                "incorrect_answer_2 TEXT, " +
                "incorrect_answer_3 TEXT)";
        Statement statement = connection.createStatement();
        statement.execute(sql);
        statement.close();
    }

    private static void insertQuestionsIntoDatabase(Connection connection, JsonArray results) throws SQLException {
        String sql = "INSERT INTO questions (question, correct_answer, incorrect_answer_1, incorrect_answer_2, incorrect_answer_3) VALUES (?, ?, ?, ?, ?)";
        PreparedStatement statement = connection.prepareStatement(sql);

        for (JsonElement element : results) {
            JsonObject questionObject = element.getAsJsonObject();

            String question = questionObject.get("question").getAsString();
            String correctAnswer = questionObject.get("correct_answer").getAsString();
            JsonArray incorrectAnswers = questionObject.getAsJsonArray("incorrect_answers");

            statement.setString(1, question);
            statement.setString(2, correctAnswer);
            statement.setString(3, incorrectAnswers.get(0).getAsString());
            statement.setString(4, incorrectAnswers.get(1).getAsString());
            statement.setString(5, incorrectAnswers.get(2).getAsString());

            statement.executeUpdate();
        }

        statement.close();
    }
}