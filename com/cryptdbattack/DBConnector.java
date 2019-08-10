package com.cryptdbattack;

import java.sql.*;

public class DBConnector {
    static final String USER = "cryptdb";
    static final String PASSWORD = "cryptdb";
    static final String DRIVER = "org.mariadb.jdbc.Driver";
    static final String URL = "jdbc:mysql://localhost:3306/cryptdbtest?useSSL=false&serverTimezone=UTC";

    private static DBConnector instance = null;
    private Connection connection = null;
    private Statement statement = null;

    private DBConnector() {
    }

    public static DBConnector createInstance() {
        if (instance == null) {
            instance = new DBConnector();
            instance.init();
        }
        return instance;
    }

    public void init() {
        try {
            Class.forName("org.mariadb.jdbc.Driver");
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

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

    public void closeConnection() {
        System.out.println("Close connection to database...");
        try {
            statement.close();
            connection.close();
        } catch (SQLException e) {
            e.printStackTrace();
        }
        System.out.println("Close connection successfully");
    }

    public ResultSet executeQuery(String sql) {
        ResultSet rs = null;
        try {
            rs = statement.executeQuery(sql);
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return rs;
    }

    public int executeUpdate(String sql) {
        int ret = 0;
        try {
            ret = statement.executeUpdate(sql);
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return ret;
    }
}
