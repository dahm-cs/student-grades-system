package com.example.demo1;

import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.stage.Stage;
import java.io.IOException;
import java.util.Objects;

public class Navigation {
    public static void sceneSwitcher(Button currentButton, String fxmlPath) {
        try {
            Parent root = FXMLLoader.load(Objects.requireNonNull(Navigation.class.getResource(fxmlPath)));

            Stage currentStage = (Stage) currentButton.getScene().getWindow();

            currentStage.setScene(new Scene(root));
            currentStage.show();
        } catch (IOException e) {
            System.out.println("Error: The Folder UI is not found" + fxmlPath);
            e.printStackTrace();
        }
    }
}
