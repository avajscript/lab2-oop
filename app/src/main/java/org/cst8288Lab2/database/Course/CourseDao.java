package org.cst8288Lab2.database.Course;

import org.cst8288Lab2.transferobjects.Course;

import java.util.List;

public interface CourseDao {
    List<Course> getAllCourses();

    Course getCourseById(String courseId);

    void addCourse(Course course);

    void updateCourse(Course course);

    void removeCourse(Course course);

    void deleteAllCourses();
}
