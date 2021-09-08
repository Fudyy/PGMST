package pgmst.pgmst;

import org.bukkit.Bukkit;
import org.bukkit.plugin.java.JavaPlugin;

public final class PGMST extends JavaPlugin {

    @Override
    public void onEnable() {
        System.out.println("PGMST is starting...");
        Bukkit.getPluginManager().registerEvents(new PlayerWoolTracker(), this);
        Bukkit.getPluginManager().registerEvents(new PlayerKillTracker(), this);
    }

    @Override
    public void onDisable() {
        System.out.println("PGMST is shutting down...");

    }
}
