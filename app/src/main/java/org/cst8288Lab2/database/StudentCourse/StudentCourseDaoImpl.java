package org.cst8288Lab2.database.StudentCourse;

import org.cst8288Lab2.database.DBConnection;
import org.cst8288Lab2.database.student.StudentDao;
import org.cst8288Lab2.transferobjects.Course;
import org.cst8288Lab2.transferobjects.Student;
import org.cst8288Lab2.transferobjects.StudentCourse;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;


public class StudentCourseDaoImpl implements StudentCourseDao {


    @Override
    public List<StudentCourse> getAllStudentCourses() {
        Connection conn = null;
        PreparedStatement pstmt = null;
        ResultSet rs = null;
        ArrayList<StudentCourse> studentCourses = null;
        try {
            conn = DBConnection.getConnection();
            String query = "SELECT sc.studentId, s.firstName AS studentFirstName, s.lastName AS studentLastName, " +
                    "c.courseId, c.courseName, sc.term, sc.year " +
                    "FROM StudentCourse sc " +
                    "JOIN Student s ON sc.studentId = s.studentId " +
                    "JOIN Course c ON sc.courseId = c.courseId";
            pstmt = conn.prepareStatement(query);
            rs = pstmt.executeQuery();
            studentCourses = new ArrayList<StudentCourse>();
            while (rs.next()) {
                int studentId = rs.getInt("studentId");
                String studentFirstName = rs.getString("studentFirstName");
                String studentLastName = rs.getString("studentLastName");
                String courseId = rs.getString("courseId");
                String courseName = rs.getString("courseName");
                String term = rs.getString("term");
                int year = rs.getInt("year");

                // Create and populate dto objects
                Student student = new Student(studentId, studentFirstName, studentLastName);
                Course course = new Course(courseId, courseName);
                StudentCourse studentCourse = new StudentCourse(term, year, student, course);

                studentCourses.add(studentCourse);
            }
        } catch (SQLException e) {
            e.printStackTrace();
        } finally {
            try {
                if (rs != null) {
                    rs.close();
                }

                if (pstmt != null) {
                    pstmt.close();
                }

                if (conn != null) {
                    conn.close();
                }
            } catch (SQLException ex) {
                System.out.println(ex.getMessage());
            }
        }
        return studentCourses;
    }

    @Override
    public StudentCourse getStudentCourseByStudentAndCourseId(int studentId, String courseId) {
        Connection con = null;
        PreparedStatement pstmt = null;
        ResultSet rs = null;
        StudentCourse studentCourse = null;

        try {
            String query = "SELECT sc.studentId, s.firstName AS studentFirstName, s.lastName AS studentLastName, " +
                    "c.courseId, c.courseName, sc.term, sc.year " +
                    "FROM StudentCourse sc " +
                    "JOIN Student s ON sc.studentId = s.studentId " +
                    "JOIN Course c ON sc.courseId = c.courseId" +
                    "WHERE sc.studentId = ? AND sc.courseId = ?";
            con = DBConnection.getConnection();
            pstmt = con.prepareStatement(query);
            pstmt.setInt(1, studentId);
            pstmt.setString(2, courseId);
            rs = pstmt.executeQuery();
            while (rs.next()) {
                int studentIdFromDB = rs.getInt("studentId");
                String studentFirstName = rs.getString("studentFirstName");
                String studentLastName = rs.getString("studentLastName");
                String courseIdFromDB = rs.getString("courseId");
                String courseName = rs.getString("courseName");
                String term = rs.getString("term");
                int year = rs.getInt("year");

                // Create and populate dto objects
                Student student = new Student(studentIdFromDB, studentFirstName, studentLastName);
                Course course = new Course(courseIdFromDB, courseName);
                studentCourse = new StudentCourse(term, year, student, course);
            }
        } catch (SQLException e) {
            e.printStackTrace();
        } finally {
            try {
                if (rs != null) {
                    rs.close();
                }

                if (pstmt != null) {
                    pstmt.close();
                }

                if (con != null) {
                    con.close();
                }
            } catch (SQLException ex) {
                System.out.println(ex.getMessage());
            }
        }
        return studentCourse;
    }

    @Override
    public void addStudentCourse(StudentCourse studentCourse) {
        Connection con = null;
        PreparedStatement pstmt = null;

        try {
            con = DBConnection.getConnection();

            // do not insert AuthorID, it is generated by Database
            pstmt = con.prepareStatement(
                    "INSERT INTO studentcourse (studentId, courseId, term, year) "
                            + "VALUES(?, ?, ?, ?)");
            pstmt.setInt(1, studentCourse.getStudent().getStudentId());
            pstmt.setString(2, studentCourse.getCourse().getCourseId());
            pstmt.setString(3, studentCourse.getTerm());
            pstmt.setInt(4, studentCourse.getYear());
            pstmt.executeUpdate();
        } catch (SQLException e) {
            e.printStackTrace();
        } finally {
            try {
                if (pstmt != null) {
                    pstmt.close();
                }

                if (con != null) {
                    con.close();
                }
            } catch (SQLException ex) {
                System.out.println(ex.getMessage());
            }
        }
    }

    @Override
    public void updateStudentCourse(StudentCourse studentCourse) {
        Connection con = null;
        PreparedStatement pstmt = null;

        try {
            con = DBConnection.getConnection();
            pstmt = con.prepareStatement(
                    "UPDATE studentCourse SET term = ?, "
                            + "year = ? WHERE studentId = ? AND courseId = ?");
            pstmt.setString(1, studentCourse.getTerm());
            pstmt.setInt(2, studentCourse.getYear());
            pstmt.setInt(3, studentCourse.getStudent().getStudentId());
            pstmt.setString(4, studentCourse.getCourse().getCourseId());
            pstmt.executeUpdate();
        } catch (SQLException e) {
            e.printStackTrace();
        } finally {
            try {
                if (pstmt != null) {
                    pstmt.close();
                }

                if (con != null) {
                    con.close();
                }
            } catch (SQLException ex) {
                System.out.println(ex.getMessage());
            }
        }
    }

    @Override
    public void deleteStudentCourse(StudentCourse studentCourse) {
        Connection con = null;
        PreparedStatement pstmt = null;

        try {
            con = DBConnection.getConnection();
            pstmt = con.prepareStatement(
                    "DELETE FROM studentCourse WHERE studentId = ? AND courseId = ?");
            pstmt.setInt(1, studentCourse.getStudent().getStudentId());
            pstmt.setString(2, studentCourse.getCourse().getCourseId());
            pstmt.executeUpdate();
        } catch (SQLException e) {
            e.printStackTrace();
        } finally {
            try {
                if (pstmt != null) {
                    pstmt.close();
                }

                if (con != null) {
                    con.close();
                }
            } catch (SQLException ex) {
                System.out.println(ex.getMessage());
            }
        }
    }

    @Override
    public void deleteAllStudentCourses() {
        Connection conn = null;
        PreparedStatement pstmt = null;

        try {
            conn = DBConnection.getConnection();
            pstmt = conn.prepareStatement("DELETE FROM studentCourse WHERE TRUE");
            pstmt.executeUpdate();

        } catch (SQLException e) {
            e.printStackTrace();
        } finally {
            try {
                if (pstmt != null) {
                    pstmt.close();
                }

                if (conn != null) {
                    conn.close();
                }
            } catch (SQLException ex) {
                System.out.println(ex.getMessage());
            }
        }
    }

}
