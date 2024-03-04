package org.cst8288Lab2.businesslayer.StudentCourse;

import org.cst8288Lab2.businesslayer.DTOValidation;
import org.cst8288Lab2.logging.ValidationLogger;
import org.cst8288Lab2.transferobjects.Course;
import org.cst8288Lab2.transferobjects.StudentCourse;

import java.time.Year;
import java.util.Calendar;

public class StudentCourseValidation extends DTOValidation {
    private static final int ALGONQUIN_FOUNDING_YEAR = 1967;
    private static final int YEAR_DIGITS = 4;

    private static enum VALID_TERM {
        WINTER,
        SUMMER,
        FALL
    }

    public static boolean validateStudentCourse(StudentCourse studentCourse, ValidationLogger validationLogger) {
        boolean state = true;
        boolean validYear = validateYear(studentCourse, validationLogger);
        boolean validTerm = validateTerm(studentCourse, validationLogger);

        for (boolean funcState : new boolean[]{validYear, validTerm}) {
            if (!funcState) {
                state = false;
                break;
            }
        }
        return state;
    }

    private static boolean validateYear(StudentCourse studentCourse, ValidationLogger validationLogger) {
        boolean state = true;
        int year = studentCourse.getYear();
        int currentYear = Year.now().getValue();
        if (String.valueOf(year).length() != YEAR_DIGITS) {
            validationLogger.logError("Year is not 4 digits", studentCourse.toString());
            state = false;
        } else if (year < ALGONQUIN_FOUNDING_YEAR || year > currentYear) {
            validationLogger.logError("Year is not within " + ALGONQUIN_FOUNDING_YEAR + " and " + year, studentCourse.toString());
            state = false;
        }
        return state;
    }

    private static boolean validateTerm(StudentCourse studentCourse, ValidationLogger validationLogger) {
        boolean state = false;
        String term = studentCourse.getTerm();
        for (VALID_TERM c : VALID_TERM.values()) {
            if (term.equals("" + c)) {
                state = true;
                break;
            }
        }

        if (!state) {
            validationLogger.logError("Course is not within valid course list", studentCourse.toString());
        }
        return state;

    }
}
