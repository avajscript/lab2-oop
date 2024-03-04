package org.cst8288Lab2.database.student;

import org.cst8288Lab2.transferobjects.Student;

import java.util.List;

public interface StudentDao {
    List<Student> getAllStudents();

    Student getStudentById(int studentId);

    void addStudent(Student student);

    void updateStudent(Student student);

    void deleteStudent(Student student);

    void deleteAllStudents();
}
