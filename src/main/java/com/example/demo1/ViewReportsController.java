package com.example.demo1;

import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Button;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.cell.PropertyValueFactory;
import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;
import java.io.File;
import java.net.URL;
import java.util.Collections;
import java.util.Comparator;
import java.util.ResourceBundle;

public class ViewReportsController implements Initializable {

    @FXML private TableView<Student> reportsTable;
    @FXML private TableColumn<Student, String> idColumn;
    @FXML private TableColumn<Student, String> nameColumn;
    @FXML private TableColumn<Student, Double> gradeColumn;
    @FXML private TableColumn<Student, String> letterColumn;
    @FXML private Button sortToggleButton;
    @FXML private Button backButton;

    private ObservableList<Student> reportList = FXCollections.observableArrayList();
    private boolean isHighestFirst = true; // Tracks the toggle sorting state variable flag

    @Override
    public void initialize(URL location, ResourceBundle resources) {
        idColumn.setCellValueFactory(new PropertyValueFactory<>("id"));
        nameColumn.setCellValueFactory(new PropertyValueFactory<>("name"));
        gradeColumn.setCellValueFactory(new PropertyValueFactory<>("grade"));
        letterColumn.setCellValueFactory(new PropertyValueFactory<>("letterGrade"));

        loadReportData();
    }

    private void loadReportData() {
        reportList.clear(); // Clear existing lists before re-reading
        File file = new File("students.txt");
        if (!file.exists()) return;

        try (BufferedReader br = new BufferedReader(new FileReader(file))) {
            String line;
            while ((line = br.readLine()) != null) {
                line = line.trim();
                if (line.isEmpty()) continue;

                String[] data = line.split(",");
                if (data.length >= 3) {
                    String id = data[0].trim();
                    String name = data[1].trim();
                    try {
                        double grade = Double.parseDouble(data[2].trim());
                        reportList.add(new Student(id, name, grade));
                    } catch (NumberFormatException e) {
                        // Safe skip line execution data fallback
                    }
                }
            }

            sortData();
            reportsTable.setItems(reportList);

        } catch (IOException e) {
            System.out.println("Error rendering report views: " + e.getMessage());
        }
    }

    private void sortData() {
        Collections.sort(reportList, new Comparator<Student>() {
            @Override
            public int compare(Student s1, Student s2) {
                if (isHighestFirst) {
                    return Double.compare(s2.getGrade(), s1.getGrade()); // Descending order
                } else {
                    return Double.compare(s1.getGrade(), s2.getGrade()); // Ascending order (least on top)
                }
            }
        });
    }

    @FXML
    public void toggleSortOrder() {
        // Reverse sorting choice
        isHighestFirst = !isHighestFirst;

        if (isHighestFirst) {
            sortToggleButton.setText("Sort: Highest First");
        } else {
            sortToggleButton.setText("Sort: Lowest First");
        }

        // Apply updated array sort math logic conditions and refresh table view items UI
        sortData();
        reportsTable.refresh();
    }

    @FXML
    public void goBackToDashboard() {
        Navigation.sceneSwitcher(backButton, "/com/example/demo1/dashboard.fxml");
    }
}