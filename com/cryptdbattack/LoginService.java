package com.cryptdbattack;

import java.sql.*;

public final class LoginService {
    public static Boolean login(String username, String password) {
        String loginSQL = "select * from user_data where user_name='" + username + "' and password ='" + password + "'";
        DBConnector dbConnector = DBConnector.createInstance();
        dbConnector.connectDB();
        try {
            ResultSet rs = dbConnector.executeQuery(loginSQL);
            if (rs.next()) {
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
}