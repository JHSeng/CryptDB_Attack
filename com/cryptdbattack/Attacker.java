package com.cryptdbattack;

import java.sql.*;
import java.util.HashSet;
import java.util.Set;
import java.util.Vector;
import java.util.ArrayList;
import java.util.Collections;

public final class Attacker {
    private static DBConnector dbConnector = null;
    private static Set<String> set = null;
    private static Vector<String> columnName = null;

    public static void launch() throws SQLException {
        init();

        // expected distribution from public data
        double[] distributionWeight = { 0.15, 0.25, 0.25, 0.35 };

        // find specific column
        String findAll = "select * from user_data";
        ResultSet resultSet = dbConnector.execute_query(findAll);
        ResultSetMetaData rsmd = resultSet.getMetaData();
        final int rsmdColumnCount = rsmd.getColumnCount();

        // found column
        ArrayList<Integer> candidateColumn = new ArrayList<Integer>();
        for (int i = 1; i <= rsmdColumnCount; i++) {
            // get the name of each column
            String name = rsmd.getColumnName(i);
            columnName.addElement(name);
        }
        for (int i = 0; i < columnName.size(); i++) {
            Boolean findColumn = true;
            set.clear();
            ResultSet tmp = dbConnector.execute_query(findAll);
            while (tmp.next()) {
                String currElement = tmp.getString(columnName.elementAt(i));
                set.add(currElement);
                if (set.size() > 4) {
                    findColumn = false;
                    break;
                }
            }
            if (findColumn == true) {
                System.out.println("The specific column is: " + columnName.elementAt(i));
                candidateColumn.add(i);
            }
        }
        if (candidateColumn.size() == 1) {
            System.out.println("The specific column is: " + columnName.elementAt(candidateColumn.get(0)));
        } else {
            int numOfRow = 0;
            ResultSet tmpp = dbConnector.execute_query(findAll);
            while (tmpp.next()) {
                numOfRow++;
            }
            ArrayList<Double> deviationList = new ArrayList<Double>();
            for (int i = 0; i < candidateColumn.size(); i++) {
                int columnIndex = candidateColumn.get(i);
                ArrayList<Integer> count = new ArrayList<Integer>();
                ArrayList<String> columnAttributeSet = new ArrayList<String>();
                ResultSet tmp = dbConnector.execute_query(findAll);
                while (tmp.next()) {
                    String currElement = tmp.getString(columnName.elementAt(columnIndex));
                    Boolean isNew = true;
                    for (int j = 0; j < columnAttributeSet.size(); j++) {
                        if (columnAttributeSet.get(j).equals(currElement)) {
                            count.set(j, count.get(j) + 1);
                            isNew = false;
                            break;
                        }
                    }
                    if (isNew) {
                        columnAttributeSet.add(currElement);
                        count.add(1);
                    }
                }
                double deviation = 0;
                Collections.sort(count);
                for (int j = 0; j < count.size(); j++) {
                    double a = (double) count.get(j) / numOfRow;
                    double b = (a - distributionWeight[j]) * (a - distributionWeight[j]);
                    deviation += b;
                }
                deviationList.add(deviation);
                System.out.println("the deviation is: " + String.valueOf(deviation));
            }
            int minIndex = findMinIndex(deviationList);
            System.out.println("The specific column is: " + columnName.elementAt(candidateColumn.get(minIndex)));
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

    private static int findMinIndex(ArrayList<Double> list) {
        int minIndex = 0;
        Double min = list.get(0);
        for (int i = 0; i < list.size(); i++) {
            if (list.get(i).compareTo(min) < 0) {
                min = list.get(i);
                minIndex = i;
            }
        }
        return minIndex;
    }
}