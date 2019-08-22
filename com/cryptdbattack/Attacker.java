package com.cryptdbattack;

import java.util.HashSet;
import java.util.Set;
import java.util.Vector;
import java.sql.*;

public final class Attacker {
    private static DBConnector dbConnector = null;
    private static Set<String> set = null;
    private static Vector<String> columnName = null;

    public static void launch() throws SQLException {
        init();

        // find specific column
        String findAll = "select * from user_data";
        final ResultSet resultSet = dbConnector.execute_query(findAll);
        ResultSetMetaData rsmd = resultSet.getMetaData();
        final int rsmdColumnCount = rsmd.getColumnCount();
        for (int i = 1; i <= rsmdColumnCount; i++) {
            // get the name of each column
            String name = rsmd.getColumnName(i);
            columnName.addElement(name);
        }
        for (int i = 0; i < columnName.size(); i++) {
            Boolean findColumn = true;
            set.clear();
            ResultSet tmp = resultSet;
            while (tmp.next()) {
                String currElement = tmp.getString(columnName.elementAt(i));
                set.add(currElement);
                if (set.size() > 4) {
                    findColumn = false;
                    break;
                }
            }
            if (findColumn == true) {
                System.out.println(columnName.elementAt(i));
            }
        }
    }

    // attacker init
    private static void init() {
        System.out.println("Start database attacker init process...");

        // result set init
        set = new HashSet<String>();
        set.clear();

        // column name Vector init
        columnName = new Vector<String>();

        // databease connector init
        if (dbConnector == null)
            dbConnector = DBConnector.create_instance();

        System.out.println("Database attacker init finished!");
    }
}