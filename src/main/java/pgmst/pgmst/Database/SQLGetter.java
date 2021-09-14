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
                        "`Monuments` float NOT NULL,\n" +
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

            ps = plugin.connection.getConnection().prepareStatement("INSERT INTO PlayerStats (PlayerName, PlayerUUID, Kills, Deaths, Wools, Monuments) VALUES (?, ?, 0, 0, 0, 0);");
            ps.setString(1, player.getName());
            ps.setString(2, uuid.toString());
            ps.executeUpdate();

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
            return result.next();
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return false;
    }

    //Updates the name of the player in the database.
    public void updateName(Player player){
        PreparedStatement ps1, ps2;

        String playerUUID = player.getUniqueId().toString();
        String actualName = player.getName();

        try {

            ps1=plugin.connection.getConnection().prepareStatement("SELECT PlayerName FROM PlayerStats WHERE PlayerUUID=?;");
            ps1.setString(1,playerUUID);
            ResultSet result = ps1.executeQuery();
            result.next();
            String dbPlayerName = result.getString("PlayerName");

            if (dbPlayerName != actualName){
                ps2=plugin.connection.getConnection().prepareStatement("UPDATE PlayerStats SET PlayerName=? WHERE PlayerUUID=?;");
                ps2.setString(1,actualName);
                ps2.setString(2,playerUUID);
                ps2.executeUpdate();
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
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

    //Adds the percentage equivalent to the life that the player took from the monument
    public void addMonumentPercentage(UUID uuid, float percentage){
        PreparedStatement ps;
        try {
            ps = plugin.connection.getConnection().prepareStatement("UPDATE PlayerStats SET Monuments=Monuments+? WHERE PlayerUUID=?;");
            ps.setFloat(1,percentage);
            ps.setString(2,uuid.toString());
            ps.executeUpdate();
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

}
