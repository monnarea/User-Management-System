package util;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.Statement;

public class DataConnectionConfigure {
    private static String username = "postgres";
    private static String password = "1234";
    private static String url = "jdbc:postgresql://localhost:5432/postgres";
    public static  Connection getConnection(){
        try{
           return DriverManager.getConnection(
                   url,
                   username,
                   password
           );
        } catch (Exception e) {
            System.out.println("connection fail");
        }
        return null;
    }

}
