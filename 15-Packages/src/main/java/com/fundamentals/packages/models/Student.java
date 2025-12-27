package com.fundamentals.packages.models;

/**
 * Student model class
 * 
 * Person sınıfını extend eder.
 * Aynı package'de olduğu için import gerekmez.
 */
public class Student extends Person {
    private String studentId;

    public Student(String name, int age, String studentId) {
        super(name, age);
        this.studentId = studentId;
    }

    public String getStudentId() {
        return studentId;
    }

    public void setStudentId(String studentId) {
        this.studentId = studentId;
    }

    @Override
    public void displayInfo() {
        System.out.println("Student: " + getName() + ", Age: " + getAge() + 
                         ", ID: " + studentId);
    }
}
