package com.cryptdbattack;

import java.sql.*;

public class DBConnector {
    static final String USER = "cryptdb";
    static final String PASSWORD = "cryptdb";
    // static final String DRIVER = "com.mysql.jdbc.Driver";
    static final String DRIVER = "org.mariadb.jdbc.Driver";
    static final String URL = "jdbc:mysql://localhost:3306/?useSSL=false&serverTimezone=UTC";
    private static DBConnector instance = null;
    private Connection connection = null;
    private Statement statement = null;

    // default build function
    private DBConnector() {
    }

    // create instance since single instance pattern
    public static DBConnector create_instance() {
        if (instance == null) {
            instance = new DBConnector();
            instance.testDriver();
        }
        return instance;
    }

    // test JDBC driver
    public void testDriver() {
        try {
            Class.forName("com.mysql.jdbc.Driver");
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    // build connection to database
    public void connectDB() {
        System.out.println("Connecting to database...");
        try {
            connection = DriverManager.getConnection(URL, USER, PASSWORD);
            statement = connection.createStatement();
        } catch (SQLException e) {
            e.printStackTrace();
        }
        System.out.println("Connect to database successfully!");
    }

    // close connection to database
    public void closeConnection() {
        System.out.println("Close connection to database...");
        try {
            statement.close();
            connection.close();
        } catch (SQLException e) {
            e.printStackTrace();
        }
        System.out.println("Close connection successfully!");
    }

    // execute select language
    public ResultSet execute_query(String sql) {
        ResultSet rs = null;
        try {
            rs = statement.executeQuery(sql);
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return rs;
    }

    // execute insert, update, delete or other language
    public int execute_update(String sql) {
        int ret = 0;
        try {
            ret = statement.executeUpdate(sql);
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return ret;
    }
}