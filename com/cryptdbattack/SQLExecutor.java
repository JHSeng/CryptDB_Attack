package com.cryptdbattack;

import java.sql.*;

public final class SQLExecutor {
    // execute SQL
    public Boolean executeSQL(String sql){
        DBConnector dbConnector=DBConnector.createInstance();
        dbConnector.connectDB();
        try{
            ResultSet resultSet=null;
            
        }
    }
}