package com.example.demo1;

import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;
import java.io.File;

public class DashboardController {

    @FXML private Label totalStudentsLabel;
    @FXML private Label averageGradeLabel;
    @FXML private Button addStudentBtn;
    @FXML private Button addGradesBtn;
    @FXML private Button viewStudentsBtn;
    @FXML private Button viewReportsBtn;
    @FXML private Button logoutBtn;

    @FXML
    public void initialize() {
        loadStatistics();
    }

    private void loadStatistics() {
        File file = new File("students.txt");

        // If file doesn't exist yet, default everything to zero safely
        if (!file.exists()) {
            totalStudentsLabel.setText("0");
            averageGradeLabel.setText("0.00");
            return;
        }

        int count = 0;
        double sum = 0;

        try (BufferedReader reader = new BufferedReader(new FileReader(file))) {
            String line;
            while ((line = reader.readLine()) != null) {
                line = line.trim();
                if (line.isEmpty()) continue;

                // Split format matching AddStudentController: id,name,grade
                String[] tokens = line.split(",");
                if (tokens.length >= 3) {
                    try {
                        double grade = Double.parseDouble(tokens[2].trim());
                        sum += grade;
                        count++;
                    } catch (NumberFormatException e) {
                        // Skip unparseable grade strings
                    }
                }
            }
        } catch (IOException e) {
            System.out.println("Error reading statistics from students.txt");
            e.printStackTrace();
        }

        // UI text element updates
        totalStudentsLabel.setText(String.valueOf(count));
        if (count > 0) {
            double average = sum / count;
            averageGradeLabel.setText(String.format("%.2f", average));
        } else {
            averageGradeLabel.setText("0.00");
        }
    }

    @FXML
    private void goToAddStudent() {
        Navigation.sceneSwitcher(addStudentBtn, "/com/example/demo1/add_student.fxml");
    }

    @FXML
    private void goToAddGrades() {
        Navigation.sceneSwitcher(addGradesBtn, "/com/example/demo1/add_grades.fxml");
    }

    @FXML
    private void goToViewStudents() {
        Navigation.sceneSwitcher(viewStudentsBtn, "/com/example/demo1/ViewStudents.fxml");
    }

    @FXML
    private void goToReports() {
        Navigation.sceneSwitcher(viewReportsBtn, "/com/example/demo1/view_reports.fxml");
    }

    @FXML
    private void handleLogout() {
        Navigation.sceneSwitcher(logoutBtn, "/com/example/demo1/login.fxml");
    }
}