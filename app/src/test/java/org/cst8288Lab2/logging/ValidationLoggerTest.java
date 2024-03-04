package org.cst8288Lab2.logging;

import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

class ValidationLoggerTest {

    @Test
    public void testInstance() {
        ValidationLogger validationLogger1 = ValidationLogger.getInstance();
        ValidationLogger validationLogger2 = ValidationLogger.getInstance();

        assertSame(validationLogger1, validationLogger2, "Validation logger instances are not the same");
    }

}