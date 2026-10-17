package com.example.demo1;

public class Student {
    private String id;
    private String name;
    private double grade;

    public Student(String id, String name, double grade) {
        this.id = id;
        this.name = name;
        this.grade = grade;
    }

    public String getId() { return id; }
    public void setId(String id) { this.id = id; }

    public String getName() { return name; }
    public void setName(String name) { this.name = name; }

    public double getGrade() { return grade; }
    public void setGrade(double grade) { this.grade = grade; }

    // Dynamic conversion utility supporting plus tiers
    public String getLetterGrade() {
        if (grade >= 95.0) return "A+";
        if (grade >= 90.0) return "A";
        if (grade >= 85.0) return "B+";
        if (grade >= 80.0) return "B";
        if (grade >= 75.0) return "C+";
        if (grade >= 70.0) return "C";
        if (grade >= 65.0) return "D+";
        if (grade >= 60.0) return "D";
        return "F";
    }
}