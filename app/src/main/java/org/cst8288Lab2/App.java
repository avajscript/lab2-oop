/*
 * Main
 */
package org.cst8288Lab2;

import org.checkerframework.checker.units.qual.C;
import org.cst8288Lab2.businesslayer.StudentCourse.StudentCourseValidation;
import org.cst8288Lab2.businesslayer.course.CourseValidation;
import org.cst8288Lab2.businesslayer.student.StudentValidation;
import org.cst8288Lab2.database.Course.CourseDaoImpl;
import org.cst8288Lab2.database.DatabaseTracker;
import org.cst8288Lab2.database.StudentCourse.StudentCourseDao;
import org.cst8288Lab2.database.StudentCourse.StudentCourseDaoImpl;
import org.cst8288Lab2.database.student.StudentDaoImpl;
import org.cst8288Lab2.logging.ValidationLogger;
import org.cst8288Lab2.transferobjects.Course;
import org.cst8288Lab2.transferobjects.Student;
import org.cst8288Lab2.transferobjects.StudentCourse;

import java.io.BufferedReader;
import java.io.FileInputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.util.Properties;

/**
 * App
 */
public class App {
    /**
     * Parses the file: bulk-import.csv
     * Validates each item in each row and updates the database accordingly.
     *
     * @param args -
     */
    public static void main(String[] args) {
        //Ensure that you use the Properties class to load values from the database.properties file
        Properties dbConnection = new Properties();
        ValidationLogger validationLogger = ValidationLogger.getInstance();
        StudentDaoImpl studentDao = new StudentDaoImpl();
        CourseDaoImpl courseDao = new CourseDaoImpl();
        StudentCourseDao studentCourseDao = new StudentCourseDaoImpl();
        DatabaseTracker databaseTracker = new DatabaseTracker();

        // clear the files using ValidationLogger
        validationLogger.clearReportFile();
        validationLogger.clearErrorFile();

        //Preserve this input path
        try (InputStream in = new FileInputStream("./app/data/database.properties")) {
            try (BufferedReader br = new BufferedReader(new InputStreamReader(in))) {
                String out;
                while ((out = br.readLine()) != null) {
                    System.out.println(out.toString());
                }
            }
        } catch (IOException e) {
            e.printStackTrace();
        }


        // delete all data in database each run to avoid duplicate keys
        studentCourseDao.deleteAllStudentCourses();
        studentDao.deleteAllStudents();
        courseDao.deleteAllCourses();

        //Preserve this input path
        try (InputStream in = new FileInputStream("./app/data/bulk-import.csv")) {
            try (BufferedReader br = new BufferedReader(new InputStreamReader(in))) {
                // skip first line
                br.readLine();
                // create uninitialized dto objects
                Student student;
                Course course;
                StudentCourse studentCourse;

                String out;
                // variables for csv columns
                int studentId;
                String firstName,
                        lastName,
                        courseId;
                String courseName, term;
                int year;
                // read csv line
                while ((out = br.readLine()) != null) {

                    // initialize dto objects
                    student = new Student();
                    course = new Course();
                    studentCourse = new StudentCourse();

                    // initialize variables from csv
                    String[] columns = out.split(",");
                    System.out.println("---");
                    try {
                        studentId = Integer.parseInt(columns[0]);
                    } catch (NumberFormatException e) {
                        studentId = 0;
                    }

                    firstName = columns[1];
                    lastName = columns[2];
                    courseId = columns[3];
                    courseName = columns[4];
                    term = columns[5];
                    try {
                        year = Integer.parseInt(columns[6]);
                    } catch (NumberFormatException e) {
                        year = 0;
                    }

                    // create student
                    student.setStudentId(studentId);
                    student.setFirstName(firstName);
                    student.setLastName(lastName);

                    // if student is valid, add to database and update database tracker, else it will log errors
                    if (StudentValidation.validateStudent(student, validationLogger)) {

                        studentDao.addStudent(student);
                        databaseTracker.addStudent();
                    }

                    // create course
                    course.setCourseId(courseId);
                    course.setCourseName(courseName);

                    // if course is valid, add to database and update database tracker, else it will log errors
                    if (CourseValidation.validateCourse(course, validationLogger)) {
                        courseDao.addCourse(course);
                        databaseTracker.addCourse();
                    }

                    studentCourse.setTerm(term);
                    studentCourse.setYear(year);
                    studentCourse.setStudent(student);
                    studentCourse.setCourse(course);

                    // if StudentCourse is valid, add to database and update database tracker, else it will log errors
                    if (StudentCourseValidation.validateStudentCourse(studentCourse, validationLogger)) {
                        studentCourseDao.addStudentCourse(studentCourse);
                        databaseTracker.addStudentCourse();
                    }

                }
            }
        } catch (IOException e) {
            e.printStackTrace();
        } finally {
            validationLogger.close();
        }
    }
}
