package at.hakimst.apr.db;
import java.sql.Connection;
import java.sql.DriverManager;

public class DBConnection {
    private final static String DB_URL = "jdbc:mysql://localhost:3306/notes";
    private final static String DB_USER = "root";
    private final static String DB_PASS = "";
    private static Connection connection;

    private DBConnection(){
    }

    public static Connection getConnection(){
        if(connection == null){
            try {
                Class.forName("com.mysql.cj.jdbc.Driver");
                connection = DriverManager.getConnection(DB_URL,DB_USER, DB_PASS);
            } catch(Exception e){
                e.printStackTrace();
            }
        }
        return connection;
    }

}