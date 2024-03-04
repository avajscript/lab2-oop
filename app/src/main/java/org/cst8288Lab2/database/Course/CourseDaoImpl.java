package org.cst8288Lab2.database.Course;

import org.cst8288Lab2.database.DBConnection;
import org.cst8288Lab2.transferobjects.Course;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

public class CourseDaoImpl implements CourseDao {

    @Override
    public List<Course> getAllCourses() {
        Connection conn = null;
        PreparedStatement pstmt = null;
        ResultSet rs = null;
        ArrayList<Course> courses = null;

        try {
            conn = DBConnection.getConnection();
            String query = "SELECT * FROM Course";
            pstmt = conn.prepareStatement(query);
            rs = pstmt.executeQuery();

            courses = new ArrayList<>();
            while (rs.next()) {
                Course course = new Course();

                String courseId = rs.getString("courseId");
                course.setCourseId(courseId);

                String courseName = rs.getString("courseName");
                course.setCourseName(courseName);

                courses.add(course);
            }
        } catch (SQLException e) {
            e.printStackTrace();
        } finally {
            closeResources(conn, pstmt, rs);
        }
        return courses;
    }

    @Override
    public Course getCourseById(String courseId) {
        Connection conn = null;
        PreparedStatement pstmt = null;
        ResultSet rs = null;
        Course course = null;

        try {
            conn = DBConnection.getConnection();
            pstmt = conn.prepareStatement("SELECT * FROM Course WHERE courseId = ?");
            pstmt.setString(1, courseId);
            rs = pstmt.executeQuery();

            while (rs.next()) {
                course = new Course();
                course.setCourseId(rs.getString("courseId"));
                course.setCourseName(rs.getString("courseName"));
            }
        } catch (SQLException e) {
            e.printStackTrace();
        } finally {
            closeResources(conn, pstmt, rs);
        }
        return course;
    }

    @Override
    public void addCourse(Course course) {
        Connection conn = null;
        PreparedStatement pstmt = null;

        try {
            conn = DBConnection.getConnection();
            pstmt = conn.prepareStatement("INSERT INTO Course (courseId, courseName) VALUES (?, ?)");
            pstmt.setString(1, course.getCourseId());
            pstmt.setString(2, course.getCourseName());
            pstmt.executeUpdate();
        } catch (SQLException e) {
            e.printStackTrace();
        } finally {
            closeResources(conn, pstmt, null);
        }
    }

    // Implementing updateCourse and removeCourse methods similar to StudentDao is left as an exercise.
    // Remember to close resources appropriately.

    // Utility method to close resources
    private void closeResources(Connection conn, PreparedStatement pstmt, ResultSet rs) {
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

    @Override
    public void updateCourse(Course course) {
        Connection conn = null;
        PreparedStatement pstmt = null;

        try {
            conn = DBConnection.getConnection();
            pstmt = conn.prepareStatement("UPDATE Course SET courseName = ? WHERE courseId = ?");
            pstmt.setString(1, course.getCourseName());
            pstmt.setString(2, course.getCourseId());
            pstmt.executeUpdate();
        } catch (SQLException e) {
            e.printStackTrace();
        } finally {
            closeResources(conn, pstmt, null);
        }
    }

    @Override
    public void removeCourse(Course course) {
        Connection conn = null;
        PreparedStatement pstmt = null;

        try {
            conn = DBConnection.getConnection();
            pstmt = conn.prepareStatement("DELETE FROM Course WHERE courseId = ?");
            pstmt.setString(1, course.getCourseId());
            pstmt.executeUpdate();
        } catch (SQLException e) {
            e.printStackTrace();
        } finally {
            closeResources(conn, pstmt, null);
        }
    }

    @Override
    public void deleteAllCourses() {
        Connection conn = null;
        PreparedStatement pstmt = null;

        try {
            conn = DBConnection.getConnection();
            pstmt = conn.prepareStatement("DELETE FROM course WHERE TRUE");
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
