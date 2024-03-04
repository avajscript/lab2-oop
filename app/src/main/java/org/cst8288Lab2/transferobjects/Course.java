package org.cst8288Lab2.transferobjects;

public class Course implements DTO {
    private String courseId;
    private String courseName;

    public Course() {
    }

    public Course(String courseId, String courseName) {
        this.courseId = courseId;
        this.courseName = courseName;
    }

    public String getCourseId() {
        return courseId;
    }

    public void setCourseId(String courseId) {
        this.courseId = courseId;
    }

    public String getCourseName() {
        return courseName;
    }

    public void setCourseName(String courseName) {
        this.courseName = courseName;
    }


    public String toString() {
        return "Course: [ Course id: " + courseId + ", name: " + courseName + " ]";
    }

}
