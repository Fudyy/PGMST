package pgmst.pgmst;

import pgmst.pgmst.Trackers.PlayerKillTracker;
import pgmst.pgmst.Trackers.PlayerWoolTracker;
import org.bukkit.Bukkit;
import org.bukkit.plugin.java.JavaPlugin;
import pgmst.pgmst.Database.DBConnection;


public final class PGMST extends JavaPlugin {

    @Override
    public void onEnable() {
        System.out.println("PGMST is starting...");

        DBConnection conn = new DBConnection();
        if (conn != null) {
            System.out.println("Successful database connection!");
        } else {
            System.out.printf("Cannot establish connection to the database :( ");
        }

        Bukkit.getPluginManager().registerEvents(new PlayerWoolTracker(), this);
        Bukkit.getPluginManager().registerEvents(new PlayerKillTracker(), this);
    }

    @Override
    public void onDisable() {
        System.out.println("PGMST is shutting down...");

    }
}
