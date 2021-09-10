package pgmst.pgmst.Database;

import java.sql.Connection;
import java.sql.DriverManager;


public class DBConnection {
    public static void main(String[] args) {
        try{
            Connection connection = DriverManager.getConnection("jdbc:mysql://localhost:3306/pgmst", "root", "");

        } catch (Exception e){
            e.printStackTrace();
        }
    }
}

