/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.example.demo1;

import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Node;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.stage.Stage;
import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;
import java.net.URL;
import java.util.Objects;
import java.util.ResourceBundle;

public class ViewStudentsController implements Initializable {

    @FXML private TableView<Student> studentsTable;
    @FXML private TableColumn<Student, String> idColumn;
    @FXML private TableColumn<Student, String> nameColumn;
    @FXML private TableColumn<Student, Double> gradeColumn;
    @FXML private Label averageLabel;
    @FXML private Button backButton;

    private ObservableList<Student> studentList = FXCollections.observableArrayList();

    @Override
    public void initialize(URL location, ResourceBundle resources) {
        idColumn.setCellValueFactory(new PropertyValueFactory<>("id"));
        nameColumn.setCellValueFactory(new PropertyValueFactory<>("name"));
        gradeColumn.setCellValueFactory(new PropertyValueFactory<>("grade"));

        loadStudentsData();
    }

    private void loadStudentsData() {
        double totalGrades = 0;
        int studentCount = 0;

        try (BufferedReader br = new BufferedReader(new FileReader("students.txt"))) {
            String line;
            while ((line = br.readLine()) != null) {
                String[] data = line.split(",");
                if (data.length == 3) {
                    String id = data[0].trim();
                    String name = data[1].trim();
                    double grade = Double.parseDouble(data[2].trim());
                    
                    studentList.add(new Student(id, name, grade));
                    totalGrades += grade;
                    studentCount++;
                }
            }
            studentsTable.setItems(studentList);

            if (studentCount > 0) {
                double average = totalGrades / studentCount;
                averageLabel.setText(String.format("Average Grade: %.2f", average));
            } else {
                averageLabel.setText("Average Grade: 0.00");
            }

        } catch (IOException e) {
            System.out.println("Error reading file: " + e.getMessage());
        } catch (NumberFormatException e) {
            System.out.println("Error parsing grade data.");
        }
    }

    @FXML
    public void goBackToDashboard() {
        Navigation.sceneSwitcher(backButton, "/com/example/demo1/dashboard.fxml");
    }
}