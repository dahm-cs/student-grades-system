package com.example.demo1;

import javafx.fxml.FXML;
import javafx.scene.control.Label;
import javafx.scene.control.TextField;
import javafx.scene.control.PasswordField;
import javafx.scene.control.Button;
import javafx.scene.paint.Color;

public class LoginController {
    @FXML
    private TextField usernameField;

    @FXML
    private PasswordField passwordField;

    @FXML
    private Label errorLabel;

    @FXML
    private Button loginButton;

    @FXML
    public void login() {
        errorLabel.setText("");

        String username = usernameField.getText();
        String password = passwordField.getText();
        //Check if the Password is 8 Characters
        if (password.length() < 8) {
            errorLabel.setTextFill(Color.RED);
            errorLabel.setText("Password must be at least 8 characters long!");
            return;
        }
        //Check to see if the username and password are correct to move to dashboard
        if (username.equals("Admin") && password.equals("Admin1234")) {
            errorLabel.setTextFill(Color.GREEN);
            errorLabel.setText("Login Success! Redirecting...");

            //Switch The Screen to dashboard
            Navigation.sceneSwitcher(loginButton, "/com/example/demo1/dashboard.fxml");

        } else {
            //this message will pop up if the username and password aren't correct
            errorLabel.setTextFill(Color.RED);
            errorLabel.setText("Wrong Username or Password!");
        }
    }
}
