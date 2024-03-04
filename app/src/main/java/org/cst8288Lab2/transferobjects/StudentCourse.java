package org.cst8288Lab2.transferobjects;

public class StudentCourse implements DTO {
    private String term;
    private int year;
    private Student student;
    private Course course;

    public StudentCourse() {
    }

    public StudentCourse(String term, int year, Student student, Course course) {
        this.term = term;
        this.year = year;
        this.student = student;
        this.course = course;
    }

    public String getTerm() {
        return term;
    }

    public void setTerm(String term) {
        this.term = term;
    }

    public int getYear() {
        return year;
    }

    public void setYear(int year) {
        this.year = year;
    }

    public Student getStudent() {
        return student;
    }

    public void setStudent(Student student) {
        this.student = student;
    }

    public Course getCourse() {
        return course;
    }

    public void setCourse(Course course) {
        this.course = course;
    }

    public String toString() {
        return "Student Course: [ Term: " + term + ", year: " + year + ", " + student.toString() + course.toString() + " ]";
    }
}
