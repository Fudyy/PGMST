package pgmst.pgmst;

import org.bukkit.event.Listener;
import pgmst.pgmst.Database.SQLGetter;
import org.bukkit.Bukkit;
import org.bukkit.plugin.java.JavaPlugin;
import pgmst.pgmst.Database.DBConnection;

import java.sql.SQLException;

public final class Main extends JavaPlugin implements Listener {

    public DBConnection connection;
    public SQLGetter data;

    @Override
    public void onEnable() {
        System.out.println("PGMST is starting...");

        getConfig().options().copyDefaults();
        saveDefaultConfig();

        this.connection = new DBConnection(this);
        this.data = new SQLGetter(this);

        try {
            connection.connect();
        } catch (ClassNotFoundException | SQLException e){
            Bukkit.getLogger().info("PGMST can't connect to the database. :(");
        }

        if(connection.isConnected()){
            Bukkit.getLogger().info("PGMST is now connected to the database! :D");
            //create PlayerStats table if not exists in the database.
            data.createTable();

            this.getServer().getPluginManager().registerEvents(new Trackers(data), this);
        }
    }

    @Override
    public void onDisable() {
        System.out.println("PGMST is shutting down...");
        connection.disconnect();
    }
}
