package org.cst8288Lab2.database;

import java.io.FileInputStream;
import java.io.InputStream;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;
import java.util.Properties;

/**
 * Singleton class to connect to the database
 */
public class DBConnection {
    private static Connection connection = null;
    private static Properties properties = new Properties();
    private static String db = null;
    private static String name = null;
    private static String user = null;
    private static String pass = null;
    private static String host = null;
    private static String port = null;


    private DBConnection() {
    }

    public static void loadProperties() {
        try (InputStream input = DBConnection.class.getClassLoader().getResourceAsStream("database.properties")) {
            if (input == null) {
                System.out.println("Sorry, unable to find database.properties");
                return;
            }

            // Load the properties file
            properties.load(input);
            // set the class attributes from the properties
            db = properties.getProperty("db");
            name = properties.getProperty("name");
            user = properties.getProperty("user");
            pass = properties.getProperty("pass");
            host = properties.getProperty("host");
            port = properties.getProperty("port");

            System.out.println("DB: " + db);
        } catch (Exception e) {
            e.printStackTrace();
            throw new RuntimeException("Failed to load database properties");
        }
    }

    public static Connection getConnection() {
        try {
            if (connection == null || connection.isClosed()) {
                if (db == null) {
                    loadProperties();
                }
                if (connection != null) {
                    connection.close();
                }
                connection = DriverManager.getConnection(("jdbc:" + db + "://" + host + ":" + port + "/" + name),
                        user, pass);
            } else {
                System.out.println("Cannot create new connection");
            }

        } catch (SQLException sqlException) {
            sqlException.printStackTrace();
        }
        return connection;
    }


}
