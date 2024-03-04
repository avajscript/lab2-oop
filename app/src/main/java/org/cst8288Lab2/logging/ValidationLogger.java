package org.cst8288Lab2.logging;

import java.io.FileWriter;
import java.io.IOException;
import java.io.PrintWriter;
import java.util.Date;
import java.util.logging.Logger;

public class ValidationLogger {
    private static final String REPORT_FILENAME = "import-report.md";
    private static final String ERROR_FILENAME = "error-report.md";

    private static ValidationLogger instance;
    private PrintWriter reportLogFile;
    private PrintWriter errorLogFile;

    // Private constructor to prevent external instantiation
    private ValidationLogger() {
        try {
            reportLogFile = new PrintWriter(new FileWriter(REPORT_FILENAME, true));
            errorLogFile = new PrintWriter(new FileWriter(ERROR_FILENAME, true));
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    public static ValidationLogger getInstance() {
        if (instance == null) {
            instance = new ValidationLogger();
        }
        return instance;
    }

    public void logReport(String message) {
        String timestamp = new Date().toString();
        reportLogFile.println("Report: " + timestamp + ": " + message);
        reportLogFile.flush();
    }

    public void logError(String message, String objectString) {
        String timestamp = new Date().toString();
        errorLogFile.println("Error: " + timestamp + ": " + objectString + ", message: " + message);
        errorLogFile.flush();

    }

    public void clearReportFile() {
        try {
            if (reportLogFile != null) {
                reportLogFile.close();
            }
            reportLogFile = new PrintWriter(new FileWriter(REPORT_FILENAME));
            reportLogFile.close();
            reportLogFile = new PrintWriter(new FileWriter(REPORT_FILENAME, true));
        } catch (IOException e) {
            e.printStackTrace();
        }

    }

    public void clearErrorFile() {
        try {
            if (errorLogFile != null) {
                errorLogFile.close();
            }
            errorLogFile = new PrintWriter(new FileWriter(REPORT_FILENAME));
            errorLogFile.close();
            errorLogFile = new PrintWriter(new FileWriter(REPORT_FILENAME, true));
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    public void close() {
        if (reportLogFile != null) {
            reportLogFile.close();
        }

        if (errorLogFile != null) {
            errorLogFile.close();
        }
    }
}
