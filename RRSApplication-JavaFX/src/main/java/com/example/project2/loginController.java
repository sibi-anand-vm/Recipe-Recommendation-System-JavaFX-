package com.example.project2;

import javafx.event.ActionEvent;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.TextField;
import javafx.stage.Stage;

public class loginController {
    public Button signupButton;
    public Button loginButton;
    public TextField passwordTextField;
    public TextField usermailTextField;
    public Label welcomeText;

    public void onloginButtonClick() {
        try {
            // Load the login.fxml file
            String usermail = usermailTextField.getText();
            String password = passwordTextField.getText();
            boolean isvalid = RRSApplication.performlogin(usermail, password);
            if (isvalid) {
                try {
                    // Load the login.fxml file
                    FXMLLoader loader = new FXMLLoader(getClass().getResource("menu.fxml"));
                    Parent root = loader.load();
                    Scene loginScene = new Scene(root);

                    // Get the current stage (assuming your signup.fxml is in the same stage)
                    Stage stage = (Stage) welcomeText.getScene().getWindow();

                    // Set the new scene on the stage
                    stage.setScene(loginScene);
                } catch (Exception e) {
                    e.printStackTrace();
                }
            } else {
                // If validation fails, handle accordingly (show an error message, etc.)
                System.out.println("Invalid usermail or password");
            }
        }
        catch (Exception e) {
            e.printStackTrace();
        }
    }
    public void onSignUpButtonClick() {
        try {
            // Load the login.fxml file

            FXMLLoader loader = new FXMLLoader(getClass().getResource("signup.fxml"));
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
