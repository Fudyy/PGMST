package pgmst.pgmst;

import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.player.PlayerJoinEvent;
import pgmst.pgmst.Database.SQLGetter;
import org.bukkit.Bukkit;
import org.bukkit.plugin.java.JavaPlugin;
import pgmst.pgmst.Database.DBConnection;
import tc.oc.pgm.api.player.ParticipantState;
import tc.oc.pgm.api.player.event.MatchPlayerDeathEvent;
import tc.oc.pgm.wool.PlayerWoolPlaceEvent;

import java.sql.SQLException;
import java.util.UUID;


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

            this.getServer().getPluginManager().registerEvents(this, this);
        }
    }

    @Override
    public void onDisable() {
        System.out.println("PGMST is shutting down...");
        connection.disconnect();
    }

    @EventHandler
    public void onPlayerJoin(PlayerJoinEvent event){
        Player player = event.getPlayer();
        data.createNewPlayer(player);
    }

    @EventHandler
    public void onDeath(MatchPlayerDeathEvent event) {
        //Get the victim uuid
        ParticipantState victim = event.getVictim().getParticipantState();
        UUID victimUUID = victim.getId();

        //Get the killer UUID if exists, or it's not a suicide
        ParticipantState killer = event.getKiller();
        if (killer != null && killer.getId() != victimUUID) {
            UUID killerUUID = killer.getId();
            data.addKill(killerUUID);
            data.addDeath(victimUUID);
        } else {
            data.addDeath(victimUUID);
        }
    }

    @EventHandler
    public void onWoolCapture(PlayerWoolPlaceEvent event) {
        //Gets the player UUID that captured a wool.
        UUID player = event.getPlayer().getId();
        data.addWools(player);
    }

    public void configPath(){
        String ip = getConfig().getString("IP");
    }
}
