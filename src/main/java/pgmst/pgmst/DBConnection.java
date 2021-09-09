package pgmst.pgmst;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.Statement;


public class DBConnection {
    public static void main(String[] args) {
        try{
            Connection connection = DriverManager.getConnection("jdbc:mysql://localhost:3306/pgmst", "root", "");

            Statement statement = connection.createStatement();
        } catch (Exception e){
            e.printStackTrace();
        }
    }
}

