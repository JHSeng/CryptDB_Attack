import java.sql.*;
import java.util.*;

public class DBManager {
    static final String USER = "root";
    static final String PASSWORD = "a86622835";
    static final String DRIVER = "com.mysql.cj.jdbc.Driver";
    static final String URL = "jdbc:mysql://localhost:3006/test?useSSL=false&serverTimezone=UTC";

    private static DBManager per = null;
    private Connection conn = null;
    private Statement stmt = null;

    private DBManager() {
    }

    public static DBManager createInstance() {
        if (per == null) {
            per = new DBManager();
            per.initDB();
        }
        return per;
    }

    public void initDB() {
        try {
            Class.forName("com.mysql.cj.jdbc.Driver");
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    public void connectDB(){
        System.out.print
    }
}
