package org.cst8288Lab2.database;

import org.junit.jupiter.api.Test;

import java.sql.Connection;

import static org.junit.jupiter.api.Assertions.*;

class DBConnectionTest {
    @Test
    public void testGetConnection() {
        Connection connection = DBConnection.getConnection();
        assertNotNull(connection, "Database properly connected");
    }

    @Test
    public void testDriver() {
        try {
            Class.forName("com.mysql.cj.jdbc.Driver");
        } catch (ClassNotFoundException e) {
            throw new RuntimeException("MySQL JDBC Driver not found", e);
        }

        // Rest of the code for loading properties
    }

}