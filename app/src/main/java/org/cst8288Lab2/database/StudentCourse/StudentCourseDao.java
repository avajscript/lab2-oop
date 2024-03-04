package org.cst8288Lab2.database.StudentCourse;

import org.cst8288Lab2.transferobjects.StudentCourse;

import java.util.List;

public interface StudentCourseDao {
    List<StudentCourse> getAllStudentCourses();

    StudentCourse getStudentCourseByStudentAndCourseId(int studentId, String courseId);

    void addStudentCourse(StudentCourse studentCourse);

    void updateStudentCourse(StudentCourse studentCourse);

    void deleteStudentCourse(StudentCourse studentCourse);

    void deleteAllStudentCourses();
}
