package pgmst.pgmst.Database;

import org.bukkit.entity.Player;
import pgmst.pgmst.Main;

import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.UUID;

public class SQLGetter {

    private Main plugin;
    public SQLGetter(Main plugin){
        this.plugin = plugin;
    }

    //create PlayerStats table if not exists
    public void createTable(){
        PreparedStatement ps;

        String SQLcode = "CREATE TABLE IF NOT EXISTS `PlayerStats` (\n" +
                        "`id` int NOT NULL AUTO_INCREMENT,\n" +
                        "`PlayerName` varchar(50) NOT NULL,\n" +
                        "`PlayerUUID` varchar(50) NOT NULL,\n" +
                        "`Kills` int NOT NULL,\n" +
                        "`Deaths` int NOT NULL,\n" +
                        "`Wools` int NOT NULL,\n" +
                        "PRIMARY KEY (`id`)\n" +
                        ");";

        try {
            ps = plugin.connection.getConnection().prepareStatement(SQLcode);
            ps.executeUpdate();
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    //Create player's profile if it doesn't exist in the database.
    public void createNewPlayer(Player player){
        PreparedStatement ps;
        try{
            UUID uuid = player.getUniqueId();
            if(!exists(uuid)){
                ps = plugin.connection.getConnection().prepareStatement("INSERT INTO PlayerStats (PlayerName, PlayerUUID, Kills, Deaths, Wools) VALUES (?, ?, 0, 0, 0);");
                ps.setString(1, player.getName());
                ps.setString(2, uuid.toString());
                ps.executeUpdate();
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    //Detects if a player uuid is already existing in the database.
    public boolean exists(UUID uuid){
        PreparedStatement ps;
        try {
            ps = plugin.connection.getConnection().prepareStatement("SELECT * FROM PlayerStats WHERE PlayerUUID=?;");
            ps.setString(1, uuid.toString());

            ResultSet result = ps.executeQuery();
            if (result.next()) return true;
            else return false;
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return false;
    }

    //Adds a kill to the given player.
    public void addKill(UUID uuid){
        PreparedStatement ps;
        try {
            ps = plugin.connection.getConnection().prepareStatement("UPDATE playerstats SET Kills=Kills+1 WHERE PlayerUUID=?;");
            ps.setString(1, uuid.toString());
            ps.executeUpdate();
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    //Adds a Death to the given player.
    public void addDeath(UUID uuid){
        PreparedStatement ps;
        try {
            ps = plugin.connection.getConnection().prepareStatement("UPDATE playerstats SET Deaths=Deaths+1 WHERE PlayerUUID=?;");
            ps.setString(1, uuid.toString());
            ps.executeUpdate();
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    //Adds a Captured wool to the given player.
    public void addWools(UUID uuid){
        PreparedStatement ps;
        try {
            ps = plugin.connection.getConnection().prepareStatement("UPDATE playerstats SET Wools=Wools+1 WHERE PlayerUUID=?;");
            ps.setString(1, uuid.toString());
            ps.executeUpdate();
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

}
