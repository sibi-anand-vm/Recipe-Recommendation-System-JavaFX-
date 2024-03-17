package com.example.project2;

import javafx.event.ActionEvent;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.TextField;
import javafx.stage.Stage;

public class regController {

    public Label welcomeText;
    public TextField recipenameTextField;
    public TextField recipetypeTextField;
    public TextField reciperegionTextField;
    public TextField recipetasteTextField;
    public Button confirmButton;
    public Button loginButton;

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

    public void onconfirmButtonclick() {
        try {
            String name = recipenameTextField.getText();
            String type = recipetypeTextField.getText();
            String region = reciperegionTextField.getText();
            String taste = recipetasteTextField.getText();

            // Check if any of the fields are empty
            if (name.isEmpty() || type.isEmpty() || taste.isEmpty() || region.isEmpty()) {
                System.out.println("Please fill in all fields.");
                return;
            }
            RRSApplication.addrecipes(name,region,type,taste);
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
}
