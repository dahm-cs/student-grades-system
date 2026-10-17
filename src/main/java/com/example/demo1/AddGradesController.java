package com.example.demo1;

import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.TextField;
import javafx.scene.paint.Color;
import java.io.*;
import java.util.ArrayList;
import java.util.List;

public class AddGradesController {

    @FXML
    private TextField idField;

    @FXML
    private TextField gradeField;

    @FXML
    private Label messageLabel;

    @FXML
    private Button backButton;

    @FXML
    public void saveGrade() {
        messageLabel.setText("");
        
        String targetId = idField.getText().trim();
        String gradeStr = gradeField.getText().trim();

        // Validation Check
        if (targetId.isEmpty() || gradeStr.isEmpty()) {
            messageLabel.setTextFill(Color.RED);
            messageLabel.setText("Please fill all fields!");
            return;
        }

        double gradeValue;
        try {
            gradeValue = Double.parseDouble(gradeStr);
            if (gradeValue < 0 || gradeValue > 100) {
                messageLabel.setTextFill(Color.RED);
                messageLabel.setText("Grade must be between 0 and 100.");
                return;
            }
        } catch (NumberFormatException e) {
            messageLabel.setTextFill(Color.RED);
            messageLabel.setText("Please enter a valid numeric grade.");
            return;
        }

        File file = new File("students.txt");
        List<String> updatedLines = new ArrayList<>();
        boolean studentFound = false;

        // Read existing file line by line
        if (file.exists()) {
            try (BufferedReader reader = new BufferedReader(new FileReader(file))) {
                String line;
                while ((line = reader.readLine()) != null) {
                    String[] data = line.split(",");
                    // CSV format layout: StudentID,Name,Grade
                    if (data.length >= 3 && data[0].equals(targetId)) {
                        data[2] = String.valueOf(gradeValue); // update grade column
                        studentFound = true;
                    }
                    updatedLines.add(String.join(",", data));
                }
            } catch (IOException e) {
                showErrorMessage("Error reading database file!");
                e.printStackTrace();
                return;
            }
        }

        if (!studentFound) {
            showErrorMessage("Student ID not found!");
            return;
        }

        // Rewrite file with the updated data list
        try (BufferedWriter writer = new BufferedWriter(new FileWriter(file, false))) {
            for (String record : updatedLines) {
                writer.write(record);
                writer.newLine();
            }
            messageLabel.setTextFill(Color.GREEN);
            messageLabel.setText("Grade assigned successfully!");
            idField.clear();
            gradeField.clear();
        } catch (IOException e) {
            showErrorMessage("Error saving grade data!");
            e.printStackTrace();
        }
    }

    private void showErrorMessage(String msg) {
        messageLabel.setTextFill(Color.RED);
        messageLabel.setText(msg);
    }

     public void goBack() {
    // Look for dashboard.fxml directly in the same local package folder
    Navigation.sceneSwitcher(backButton, "dashboard.fxml");
    }
}