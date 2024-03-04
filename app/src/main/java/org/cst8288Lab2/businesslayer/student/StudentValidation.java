package org.cst8288Lab2.businesslayer.student;

import org.cst8288Lab2.businesslayer.DTOValidation;
import org.cst8288Lab2.businesslayer.ValidationException;
import org.cst8288Lab2.logging.ValidationLogger;
import org.cst8288Lab2.transferobjects.Student;

public class StudentValidation extends DTOValidation {
    private static String STUDENT_REGEX = "^[0-9]{9}$";
    private static final int STUDENT_ID_LENGTH = 9;
    private static final int FIRST_NAME_MAX_LENGTH = 100;
    private static final int LAST_NAME_MAX_LENGTH = 100;

    public static boolean validateStudent(Student student, ValidationLogger validationLogger) {
        boolean state = true;
        boolean validFirstName = DTOValidation.validateString(student.getFirstName(), "First name", FIRST_NAME_MAX_LENGTH, student.toString(), false, validationLogger);
        boolean validLastName = DTOValidation.validateString(student.getLastName(), "Last name", LAST_NAME_MAX_LENGTH, student.toString(), false, validationLogger);
        boolean validStudentId = DTOValidation.validateRegex("" + student.getStudentId(), "Student Id", STUDENT_REGEX, student.toString(), validationLogger);
        for (boolean funcState : new boolean[]{validFirstName, validLastName, validStudentId}) {
            if (!funcState) {
                state = false;
                break;
            }
        }
        return state;
    }

}
