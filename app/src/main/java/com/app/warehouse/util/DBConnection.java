package com.app.warehouse.util;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;
import java.util.Properties;
import java.io.InputStream;

public final class DBConnection {

    private static String URL;
    private static String USER;
    private static String PASSWORD;
    private static String DRIVER;

    static {
        try {
            Properties props = new Properties();
            InputStream input = DBConnection.class
                    .getClassLoader()
                    .getResourceAsStream("db.properties");

            if (input == null) {
                throw new RuntimeException("db.properties not found in resources folder");
            }

            props.load(input);

            DRIVER = props.getProperty("db.driver");
            URL = props.getProperty("db.url");
            USER = props.getProperty("db.user");
            PASSWORD = props.getProperty("db.password");

            Class.forName(DRIVER);

        } catch (Exception e) {
            throw new ExceptionInInitializerError(
                    "Failed to initialize database connection: " + e.getMessage()
            );
        }
    }

    private DBConnection() {
    }

    /**
     * Get new database connection.
     * @return Connection
     * @throws SQLException
     */
    public static Connection getConnection() throws SQLException {
        return DriverManager.getConnection(URL, USER, PASSWORD);
    }
}
