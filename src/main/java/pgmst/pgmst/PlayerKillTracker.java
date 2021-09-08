package pgmst.pgmst;

import org.bukkit.Bukkit;
import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.entity.PlayerDeathEvent;

public class PlayerKillTracker implements Listener {
    @EventHandler
    public void onPlayerKill(PlayerDeathEvent event) {

        Player Killer = event.getEntity().getKiller();

        if (Killer != null) {
            String KilledName = event.getEntity().getName();
            String KillerName = Killer.getName();

            Bukkit.broadcastMessage(KillerName + " Ha matado a " + KilledName + " LOLOLOLOLO");
        }else{
            Bukkit.broadcastMessage("Environment death");
        }
    }
}
