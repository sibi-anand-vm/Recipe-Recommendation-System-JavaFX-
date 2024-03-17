package com.example.project2;

import javafx.event.ActionEvent;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.TextField;
import javafx.stage.Stage;

public class delController {
    public Label welcomeText;
    public TextField recipenameTextField;
    public Button deleteButton;

    public void onmenuButtonClick(ActionEvent actionEvent) {
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

    public void ondeleteButtonClick(ActionEvent actionEvent) {
        String name=recipenameTextField.getText();
        RRSApplication.deleterecipes(name);
    }
}
