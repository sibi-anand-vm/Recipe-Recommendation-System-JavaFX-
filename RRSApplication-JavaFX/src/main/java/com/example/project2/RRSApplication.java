package com.example.project2;
import java.sql.*;

import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.scene.Scene;
import javafx.stage.Stage;

import java.io.IOException;

public class RRSApplication extends Application {
    public static void performsignup(String username, String usermail, String password) {
        try {
            Connection con = DriverManager.getConnection("jdbc:mysql://localhost:3306/normapp", "root", "ACCOUNT_LOCK42");

            String insertQuery = "INSERT INTO userdetails (username, usermail, password) VALUES (?, ?, ?)";
            try (PreparedStatement preparedStatement = con.prepareStatement(insertQuery)) {
                preparedStatement.setString(1, username);
                preparedStatement.setString(2, usermail);
                preparedStatement.setString(3, password);
String a="nn";
String b="nn";
                int rowsAffected = preparedStatement.executeUpdate();
                if (rowsAffected > 0) {
                    System.out.println("Sign-Up successful. Credentials stored in the database.");
                    createRecipeTable(username);
                } else {
                    System.out.println("Sign-Up failed. Unable to store credentials.");
                }
            }
        } catch (Exception e) {
            e.printStackTrace();
        }

    }

    private static void createRecipeTable(String username) {
        try {
            Connection con = DriverManager.getConnection("jdbc:mysql://localhost:3306/normapp", "root", "ACCOUNT_LOCK42");

            String createTableQuery = "CREATE TABLE " + username + "recipes ("
                    + "id INT PRIMARY KEY AUTO_INCREMENT,"
                    + "name VARCHAR(255),"
                    + "region VARCHAR(255),"
                    + "type VARCHAR(255),"
                    + "taste VARCHAR(255)"
                    + ")";

            try (PreparedStatement createTableStatement = con.prepareStatement(createTableQuery)) {
                createTableStatement.executeUpdate();
                System.out.println("Recipe table created for user: " + username);
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
    public static boolean performlogin(String usermail, String password) {
        try {
            String jdbcUrl = "jdbc:mysql://localhost:3306/normapp";
            String jdbcUser = "root";
            String jdbcPassword = "ACCOUNT_LOCK42";

            try (Connection con = DriverManager.getConnection(jdbcUrl, jdbcUser, jdbcPassword)) {
                String selectQuery = "SELECT username FROM userdetails WHERE BINARY usermail = ? AND BINARY password = ?";
                try (PreparedStatement preparedStatement = con.prepareStatement(selectQuery)) {
                    preparedStatement.setString(1, usermail);
                    preparedStatement.setString(2, password);

                    try (ResultSet resultSet = preparedStatement.executeQuery()) {
                        if (resultSet.next()) {
                            String username = resultSet.getString("username");
                            // Set the username in the UserContext
                            UserContext.getInstance().setCurrentUsername(username);
                            return true; // Login successful
                        }
                    }
                }
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return false; // Login failed
    }

    public static void addrecipes(String name,String type,String region,String taste) {
        try {
            String jdbcUrl = "jdbc:mysql://localhost:3306/normapp";
            String jdbcUser = "root";
            String jdbcPassword = "ACCOUNT_LOCK42";
String username=UserContext.getInstance().getCurrentUsername();
            try (Connection con = DriverManager.getConnection(jdbcUrl, jdbcUser, jdbcPassword)) {
                String insertQuery = "INSERT INTO " + username + "recipes (name, region, type, taste) VALUES (?, ?, ?, ?)";
                try (PreparedStatement preparedStatement = con.prepareStatement(insertQuery)) {
                    preparedStatement.setString(1, name);
                    preparedStatement.setString(2, region);
                    preparedStatement.setString(3, type);
                    preparedStatement.setString(4, taste);

                    int rowsAffected = preparedStatement.executeUpdate();
                    if (rowsAffected > 0) {
                        System.out.println("Recipe added successfully. Details stored in the database.");
                    } else {
                        System.out.println("Recipe addition failed. Unable to store details.");
                    }
                }
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    public static void deleterecipes(String name) {
        try {
            String jdbcUrl = "jdbc:mysql://localhost:3306/normapp";
            String jdbcUser = "root";
            String jdbcPassword = "ACCOUNT_LOCK42";
            String username=UserContext.getInstance().getCurrentUsername();
            try (Connection con = DriverManager.getConnection(jdbcUrl, jdbcUser, jdbcPassword)) {
                String deleteQuery = "DELETE FROM "+username+"recipes WHERE name = ?";
                try (PreparedStatement preparedStatement = con.prepareStatement(deleteQuery)) {
                    preparedStatement.setString(1, name);

                    int rowsAffected = preparedStatement.executeUpdate();
                    if (rowsAffected > 0) {
                        System.out.println("Recipe deleted successfully.");
                    } else {
                        System.out.println("Recipe deletion failed. No matching record found.");
                    }
                }
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    @Override
    public void start(Stage stage) throws IOException {
        FXMLLoader fxmlLoader = new FXMLLoader(RRSApplication.class.getResource("signup.fxml"));
        Scene scene = new Scene(fxmlLoader.load(), 360, 580);
        stage.setTitle("Recipe Recommendor");
        stage.setScene(scene);
        stage.show();
    }

    public static void main(String[] args) {
       launch();
    }
}