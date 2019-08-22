package com.cryptdbattack;

import java.sql.*;

public final class AccountService {
    private static DBConnector dbConnector = null;

    public static void database_init() {
        System.out.println("Start database init process...");
        if (dbConnector == null)
            dbConnector = DBConnector.create_instance();
        dbConnector.connectDB();
        dbConnector.execute_update("create database if not exists cryptdbtest");
        dbConnector.execute_update("use cryptdbtest");
        dbConnector.execute_update("drop table if exists user_data, regist_record");
        dbConnector.execute_update(
                "create table user_data(id varchar(20) primary key not null, user_name varchar(20) not null, password varchar(20) not null, user_info varchar(30), type varchar(1) not null)");
        dbConnector.execute_update(
                "create table regist_record(id varchar(20) primary key not null, user_id varchar(20) not null, type varchar(1) not null)");
        System.out.println("Database init process is completed!");
    }

    public static Boolean login(String username, String password) {
        String loginSQL = "select * from user_data where user_name='" + username + "' and password ='" + password + "'";
        try {
            ResultSet resultSet = dbConnector.execute_query(loginSQL);
            if (resultSet.next()) {
                return true;
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return false;
    }

    public static Boolean register(int id, String username, String password, String info, int type) {
        String id_str = Integer.toString(id);
        String type_str = Integer.toString(type);
        String registerSQL = "insert into user_data values('" + id_str + "','" + username + "','" + password + "','"
                + info + "','" + type_str + "')";
        String flushRecordSQL = "insert into regist_record values('" + id_str + "','" + username + "','" + type_str
                + "')";
        int ret = dbConnector.execute_update(registerSQL);
        if (ret == 0)
            return false;
        ret = dbConnector.execute_update(flushRecordSQL);
        if (ret == 0)
            return false;
        return true;
    }

    public static Boolean delete(String username, String password) {
        String deleteSQL = "delete from user_data where user_name='" + username + "' and password='" + password + "'";
        String deleteRecordSQL = "delete from regist_record where user_name='" + username + "'";
        int ret = dbConnector.execute_update(deleteSQL);
        if (ret == 0)
            return false;
        ret = dbConnector.execute_update(deleteRecordSQL);
        if (ret == 0)
            return false;
        return true;
    }

    public static Boolean find_username(String username) {
        String findSQL = "select * from user_data where user_name='" + username + "'";
        try {
            ResultSet resultSet = dbConnector.execute_query(findSQL);
            if (resultSet.next()) {
                return true;
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return false;
    }
}