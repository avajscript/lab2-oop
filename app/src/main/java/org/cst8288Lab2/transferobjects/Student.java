package org.cst8288Lab2.transferobjects;

import org.cst8288Lab2.database.StudentCourse.StudentCourseDao;

public class Student implements DTO {
    private int studentId;
    private String firstName;
    private String lastName;

    public Student() {
    }

    public Student(int studentId, String firstName, String lastName) {
        this.studentId = studentId;
        this.firstName = firstName;
        this.lastName = lastName;
    }

    public String getFirstName() {
        return firstName;
    }

    public int getStudentId() {
        return studentId;
    }

    public void setStudentId(int studentId) {
        this.studentId = studentId;
    }

    public void setFirstName(String firstName) {
        this.firstName = firstName;
    }

    public String getLastName() {
        return lastName;
    }

    public void setLastName(String lastName) {
        this.lastName = lastName;
    }

    public String toString() {
        return "Student: [ Student id: " + studentId + ", Name: " + firstName + " " + lastName + " ]";
    }
}
