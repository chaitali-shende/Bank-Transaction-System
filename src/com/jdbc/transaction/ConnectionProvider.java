package com.jdbc.transaction;

import java.io.FileInputStream;
import java.io.IOException;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;


import java.util.Properties;

public class ConnectionProvider 
{	
	private static Properties props = new Properties();

    // Load DB connection properties once
    static {
        try (FileInputStream fis = new FileInputStream("connection.properties")) {
            props.load(fis);

            // Load Driver
            Class.forName(props.getProperty("db_driver"));

        } catch (IOException | ClassNotFoundException e) {
            e.printStackTrace();
        }
    }

    // Always return a new connection
    public static Connection getConnection() {
        try {
            String db_url = props.getProperty("db_url");
            String db_usn = props.getProperty("db_usn");
            String db_pwd = props.getProperty("db_pwd");

            return DriverManager.getConnection(db_url, db_usn, db_pwd);
        } catch (SQLException e) {
            e.printStackTrace();
            throw new RuntimeException("Failed to establish DB connection!", e);
        }
    }
	
}
