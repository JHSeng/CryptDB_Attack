package com.cryptdbattack;

import java.sql.*;

public final class LoginService {
    public static Boolean login(String username, String password) {
        String loginSQL = "select * from user_data where user_name='" + username + "' and password ='" + password + "'";
        DBConnector dbConnector = DBConnector.createInstance();
        dbConnector.connectDB();
        try {
            ResultSet resultSet = dbConnector.executeQuery(loginSQL);
            if (resultSet.next()) {
                dbConnector.closeConnection();
                return true;
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        dbConnector.closeConnection();
        return false;
    }

    public static Boolean register(String username, String password) {
        String registerSQL = "insert into user_data values('" + username + "','" + password + "')";
        DBConnector dbConnector = DBConnector.createInstance();
        dbConnector.connectDB();
        int ret = dbConnector.executeUpdate(registerSQL);
        if (ret != 0) {
            dbConnector.closeConnection();
            return true;
        }
        dbConnector.closeConnection();
        return false;
    }

    public static Boolean delete(String username, String password) {
        String deleteSQL = "delete from user_data where user_name='" + username + "' and password='" + password + "'";
        DBConnector dbConnector = DBConnector.createInstance();
        dbConnector.connectDB();
        int ret = dbConnector.executeUpdate(deleteSQL);
        if (ret != 0) {
            dbConnector.closeConnection();
            return true;
        }
        dbConnector.closeConnection();
        return false;
    }

    public static Boolean findUsername(String username) {
        String findSQL = "select * from user_data where username='" + username + "'";
        DBConnector dbConnector = DBConnector.createInstance();
        dbConnector.connectDB();
        try {
            ResultSet resultSet = dbConnector.executeQuery(findSQL);
            if (resultSet.next()) {
                dbConnector.closeConnection();
                return true;
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        dbConnector.closeConnection();
        return false;
    }
}