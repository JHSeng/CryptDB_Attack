package com.cryptdbattack;

import java.sql.*;

public class DBConnector {
    static final String USER = "cryptdb";
    static final String PASSWORD = "cryptdb";
    static final String DRIVER = "org.mariadb.jdbc.Driver";
    static final String URL = "jdbc:mysql://localhost:3306/cryptdbtest?useSSL=false&serverTimezone=UTC";

    private static DBConnector instance = null;
    private Connection conn = null;
    private Statement stmt = null;

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
            conn = DriverManager.getConnection(URL, USER, PASSWORD);
            stmt = conn.createStatement();
        } catch (SQLException e) {
            e.printStackTrace();
        }
        System.out.println("SqlManager: Connect to database successfully!");
    }

    public void closeConnection() {
        System.out.println("Close connection to database...");
        try {
            stmt.close();
            conn.close();
        } catch (SQLException e) {
            e.printStackTrace();
        }
        System.out.println("Close connection successfully");
    }

    public ResultSet executeQuery(String sql) {
        ResultSet rs = null;
        try {
            rs = stmt.executeQuery(sql);
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return rs;
    }

    public int executeUpdate(String sql) {
        int ret = 0;
        try {
            ret = stmt.executeUpdate(sql);
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return ret;
    }
}
