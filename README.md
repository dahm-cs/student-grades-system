# Student Grades System

A desktop application built with JavaFX for managing student records and grades. The interface uses the Umm Al-Qura University logo and colors.

## Features

- **Login screen** with username and password validation (password must be at least 8 characters)
- **Dashboard** showing the total number of students and the average grade
- **Add student**: register a new student by ID and name, with a check for duplicate IDs
- **Add grades**: assign a score (0 to 100) to an existing student
- **View students**: table of all students with their grades and the class average
- **Reports**: students ranked by score with letter grades (A+ to F), with a button to switch between highest first and lowest first

## Technologies

- Java
- JavaFX (FXML for the screens)
- Maven

## Project structure

```
src/main/java/com/example/demo1/
├── LoginScreen.java            # application entry point
├── Navigation.java             # switches between screens
├── Student.java                # student model + letter grade logic
├── LoginController.java
├── DashboardController.java
├── AddStudentController.java
├── AddGradesController.java
├── ViewStudentsController.java
├── ViewReportsController.java
└── module-info.java

src/main/resources/com/example/demo1/
├── login.fxml
├── dashboard.fxml
├── add_student.fxml
├── add_grades.fxml
├── ViewStudents.fxml
├── view_reports.fxml
└── Umm_Al-Qura_University_logo.png
```

## How to run

Requirements: **JDK 23** or newer and **Maven**.

```bash
mvn clean javafx:run
```

## Demo login

| Username | Password |
|---|---|
| `Admin` | `Admin1234` |

> These credentials are for demo purposes only.

## Data storage

Student records are saved in a plain text file called `students.txt`, one student per line in the format `id,name,grade`. The file is created automatically in the project folder when the first student is added, and it is excluded from the repository through `.gitignore`.
