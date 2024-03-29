package pgmst.pgmst.Database;

import pgmst.pgmst.Main;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

public class DBConnection {
    private Connection connection;

    private Main plugin;
    public DBConnection(Main plugin){
        this.plugin = plugin;
    }

    public boolean isConnected(){
        return (connection != null);
    }

    public void connect() throws ClassNotFoundException, SQLException {
        if(!isConnected()){
            String ip = plugin.getConfig().getString("database.ip");
            String port = plugin.getConfig().getString("database.port");
            String DBName = plugin.getConfig().getString("database.dbName");
            String user = plugin.getConfig().getString("database.user");
            String password = plugin.getConfig().getString("database.password");

            connection = DriverManager.getConnection("jdbc:mysql://"+ip+":"+port+"/"+DBName+"?useSSL=false", user, password);
        }
    }

    public void disconnect() {
        if (isConnected()) {
            try {
                connection.close();
            } catch (SQLException e) {
                e.printStackTrace();
            }
        }
    }

    public Connection getConnection() {
        return connection;
    }
}


