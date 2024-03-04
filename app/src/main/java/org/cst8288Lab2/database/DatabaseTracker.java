package org.cst8288Lab2.database;

import org.cst8288Lab2.logging.ValidationLogger;

public class DatabaseTracker {
    private int studentsAdded = 0;
    private int coursesAdded = 0;
    private int studentCoursesAdded = 0;

    public void addStudent() {
        this.studentsAdded++;
    }

    public void addCourse() {
        this.coursesAdded++;
    }

    public void addStudentCourse() {
        this.studentCoursesAdded++;
    }

    public void resetCourses() {
        this.studentsAdded = 0;
        this.coursesAdded = 0;
        this.studentCoursesAdded = 0;
    }

    public void logToFile(ValidationLogger validationLogger) {
        validationLogger.logReport(studentsAdded + " student records added to database");
        validationLogger.logReport(coursesAdded + " course records added to database");
        validationLogger.logReport(studentCoursesAdded + " student courses  added to database");
    }
}
