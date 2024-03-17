package com.example.project2;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Label;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.stage.Stage;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

public class myrecipesController {

    @FXML
    private TableView<Recipe> recipesTable;
    @FXML
    private TableColumn<Recipe, String> nameColumn;
    @FXML
    private TableColumn<Recipe, String> regionColumn;
    @FXML
    private TableColumn<Recipe, String> typeColumn;
    @FXML
    private TableColumn<Recipe, String> tasteColumn;
    @FXML
    private Label welcomeText;

    public void initialize() {
        // Set up the columns to use the appropriate property values of Recipe class
        nameColumn.setCellValueFactory(cellData -> cellData.getValue().nameProperty());
        regionColumn.setCellValueFactory(cellData -> cellData.getValue().regionProperty());
        typeColumn.setCellValueFactory(cellData -> cellData.getValue().typeProperty());
        tasteColumn.setCellValueFactory(cellData -> cellData.getValue().tasteProperty());
        loadDataFromDatabase();
    }

    public void loadDataFromDatabase() {
        // You need to replace these with your actual database connection details
        String jdbcUrl = "jdbc:mysql://localhost:3306/normapp";
        String username = "root";
        String password = "ACCOUNT_LOCK42";
        String usersname=UserContext.getInstance().getCurrentUsername();
        try (Connection connection = DriverManager.getConnection(jdbcUrl, username, password)) {
            String sql = "SELECT * FROM " + usersname + "recipes";
            try (PreparedStatement preparedStatement = connection.prepareStatement(sql)) {
                try (ResultSet resultSet = preparedStatement.executeQuery()) {
                    while (resultSet.next()) {
                        // Create Recipe objects with data from the database
                        Recipe recipe = new Recipe(
                                resultSet.getString("name"),
                                resultSet.getString("region"),
                                resultSet.getString("type"),
                                resultSet.getString("taste")
                        );
                        // Add the Recipe object to the TableView
                        recipesTable.getItems().add(recipe);
                    }
                }
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    public void onmenuButtonClick() {
        try {
            // Load the login.fxml file
            FXMLLoader loader = new FXMLLoader(getClass().getResource("menu.fxml"));
            Parent root = loader.load();

            // Create a new Scene
            Scene loginScene = new Scene(root);

            // Get the current stage (assuming your signup.fxml is in the same stage)
            Stage stage = (Stage) welcomeText.getScene().getWindow();

            // Set the new scene on the stage
            stage.setScene(loginScene);
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
}
