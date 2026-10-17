package com.example.demo1;

import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.TextField;
import javafx.scene.paint.Color;
import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.File;
import java.io.IOException;

public class AddStudentController {

    @FXML
    private TextField idField;

    @FXML
    private TextField nameField;

    @FXML
    private Label messageLabel;

    @FXML
    private Button backButton;

    @FXML
    public void saveStudent() {
        messageLabel.setText("");

        String studentId = idField.getText().trim();
        String studentName = nameField.getText().trim();

        //Validation Check for empty inputs
        if (studentId.isEmpty() || studentName.isEmpty()) {
            messageLabel.setTextFill(Color.RED);
            messageLabel.setText("Please fill all fields!");
            return;
        }

        File file = new File("students.txt");

        //Check if the Student ID already exists in the file
        if (file.exists()) {
            try (BufferedReader reader = new BufferedReader(new FileReader(file))) {
                String line;
                while ((line = reader.readLine()) != null) {
                    line = line.trim();
                    if (line.isEmpty()) continue;

                    String[] tokens = line.split(",");
                    if (tokens.length >= 1) {
                        String existingId = tokens[0].trim();
                        if (existingId.equals(studentId)) {
                            messageLabel.setTextFill(Color.RED);
                            messageLabel.setText("Student ID already exists!");
                            return; // Stop execution here so it doesn't save!
                        }
                    }
                }
            } catch (IOException e) {
                messageLabel.setTextFill(Color.RED);
                messageLabel.setText("Error reading database verification!");
                e.printStackTrace();
                return;
            }
        }

        //If ID is unique, go ahead and append the new student record
        try (BufferedWriter writer = new BufferedWriter(new FileWriter(file, true))) {
            String record = studentId + "," + studentName + ",0";
            writer.write(record);
            writer.newLine();

            messageLabel.setTextFill(Color.GREEN);
            messageLabel.setText("Student added successfully!");
            idField.clear();
            nameField.clear();

        } catch (IOException e) {
            messageLabel.setTextFill(Color.RED);
            messageLabel.setText("Error saving student data!");
            e.printStackTrace();
        }
    }

    @FXML
    public void goBack() {
        Navigation.sceneSwitcher(backButton, "/com/example/demo1/dashboard.fxml");
    }
}