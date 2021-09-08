package pgmst.pgmst;

import org.bukkit.Bukkit;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.entity.PlayerDeathEvent;

public class PlayerKillTracker implements Listener {
    @EventHandler
    public void onPlayerKill(PlayerDeathEvent event) {

        String KilledName = event.getEntity().getName();
        String KillerName = event.getEntity().getKiller().getName();


        Bukkit.broadcastMessage(KillerName + " Ha matado a " + KilledName + " LOLOLOLOLO");
    }
}
