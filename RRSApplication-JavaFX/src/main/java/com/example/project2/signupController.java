package com.example.project2;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.TextField;
import javafx.stage.Stage;

public class signupController {
    public TextField usernameTextField;
    public TextField usermailTextField;
    public TextField passwordTextField;
    public Button signupButton;
    public Button loginButton;
    @FXML
    private Label welcomeText;

    @FXML
    protected void onsignupButtonClick() {
        try {
            // Load the login.fxml file
            String username = usernameTextField.getText();
            String usermail = usermailTextField.getText();
            String password = passwordTextField.getText();
            // Pass input to the main method for validation
            if (username.isEmpty() || usermail.isEmpty() || password.isEmpty()) {
                // Show an error message or handle the case where input is missing
                System.out.println("Please fill in all fields.");
                return;  // Exit the method without loading the login scene
            }
            RRSApplication.performsignup(username,usermail,password);

            FXMLLoader loader = new FXMLLoader(getClass().getResource("login.fxml"));
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

    public void onloginButtonClick() {
        try {
            // Load the login.fxml file
            FXMLLoader loader = new FXMLLoader(getClass().getResource("login.fxml"));
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