package pgmst.pgmst;

import org.bukkit.Bukkit;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import tc.oc.pgm.wool.PlayerWoolPlaceEvent;

public class PlayerWoolTracker implements Listener {

    @EventHandler

    public void onWoolCapture(PlayerWoolPlaceEvent event) {

        String Player = event.getPlayer().getNameLegacy();
        String Wool = event.getWool().getName();

        Bukkit.broadcastMessage(Player + " Ha capturado " + Wool);
    }
}
