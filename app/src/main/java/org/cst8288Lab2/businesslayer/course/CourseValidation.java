package org.cst8288Lab2.businesslayer.course;

import org.cst8288Lab2.businesslayer.DTOValidation;
import org.cst8288Lab2.businesslayer.ValidationException;
import org.cst8288Lab2.logging.ValidationLogger;
import org.cst8288Lab2.transferobjects.Course;

public class CourseValidation extends DTOValidation {
    private static final int COURSE_ID_LENGTH = 9;
    private static final int COURSE_NAME_MAX_LENGTH = 140;
    private static final String COURSE_REGEX = "^[a-zA-Z]{3}[0-9]{4}$";


    public static boolean validateCourse(Course course, ValidationLogger validationLogger) {
        boolean state = true;
        boolean validCourseId = validateCourseID(course, validationLogger);
        boolean validCourseName = DTOValidation.validateString(course.getCourseName(), "Course name", COURSE_NAME_MAX_LENGTH, course.toString(), false, validationLogger);
        boolean validRegex = DTOValidation.validateRegex(course.getCourseName(), "Course name", COURSE_REGEX, course.toString(), validationLogger);
        for (boolean funcState : new boolean[]{validCourseId, validCourseName, validRegex}) {
            if (!funcState) {
                state = false;
                break;
            }
        }
        return state;
    }

    private static boolean validateCourseID(Course course, ValidationLogger validationLogger) {
        boolean state = true;
        String courseId = course.getCourseId();
        if (courseId.length() != COURSE_ID_LENGTH) {
            state = false;
            validationLogger.logError("Course id is not of proper length " + COURSE_ID_LENGTH, course.toString());
        }
        return state;
    }


}
