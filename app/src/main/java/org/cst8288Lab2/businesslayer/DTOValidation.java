package org.cst8288Lab2.businesslayer;

import org.cst8288Lab2.logging.ValidationLogger;

public abstract class DTOValidation {
    protected static boolean validateString(String value, String field, int maxLength, String objectString, boolean isNullAllowed, ValidationLogger validationLogger) {
        if (value == null && isNullAllowed) {
            validationLogger.logError(field + " cannot be null", objectString);
            return false;
        } else if (value.length() == 0) {
            validationLogger.logError(field + " cannot be empty", objectString);
        } else if (value.length() > maxLength) {
            validationLogger.logError(field + " cannot exceed length of " + maxLength, objectString);
        }
        return true;
    }

    protected static boolean validateRegex(String value, String field, String regex, String objectString, ValidationLogger validationLogger) {
        boolean state = value.matches(regex);
        if (!state) {
            validationLogger.logError(field + " does not match pattern", objectString);
        }
        return state;
    }
}
