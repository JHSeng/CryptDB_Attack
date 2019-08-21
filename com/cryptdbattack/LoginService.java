package com.cryptdbattack;

import java.sql.*;

public final class LoginService {
    private static DBConnector dbConnector = null;

    public static void databaseInit() {
        if (dbConnector == null)
            dbConnector = DBConnector.createInstance();
        dbConnector.connectDB();
        dbConnector.executeUpdate("create database if not exists cryptdbtest");
        dbConnector.executeUpdate("use cryptdbtest");
        dbConnector.executeUpdate("drop table if exists user_data");
        dbConnector.executeUpdate(
                "create table user_data(id varchar(20) primary key not null, user_name varchar(20) not null, password varchar(20) not null, user_info varchar(30))");

    }

    public static Boolean login(String username, String password) {
        String loginSQL = "select * from user_data where user_name='" + username + "' and password ='" + password + "'";
        // if (dbConnector == null) {
        // dbConnector = DBConnector.createInstance();
        // dbConnector.connectDB();
        // dbConnector.executeUpdate("use cryptdbtest");
        // }
        try {
            ResultSet resultSet = dbConnector.executeQuery(loginSQL);
            if (resultSet.next()) {
                return true;
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return false;
    }

    public static Boolean register(int id, String username, String password, String info) {
        String id_str = Integer.toString(id);
        String registerSQL = "insert into user_data values('" + id_str + "','" + username + "','" + password + "','"
                + info + "')";
        int ret = dbConnector.executeUpdate(registerSQL);
        if (ret != 0) {
            return true;
        }
        return false;
    }

    public static Boolean delete(String username, String password) {
        String deleteSQL = "delete from user_data where user_name='" + username + "' and password='" + password + "'";
        int ret = dbConnector.executeUpdate(deleteSQL);
        if (ret != 0) {
            return true;
        }
        return false;
    }

    public static Boolean findUsername(String username) {
        String findSQL = "select * from user_data where user_name='" + username + "'";
        try {
            ResultSet resultSet = dbConnector.executeQuery(findSQL);
            if (resultSet.next()) {
                return true;
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return false;
    }
}