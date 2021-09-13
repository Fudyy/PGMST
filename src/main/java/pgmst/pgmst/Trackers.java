package pgmst.pgmst;

import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.player.PlayerJoinEvent;
import pgmst.pgmst.Database.SQLGetter;
import tc.oc.pgm.api.player.ParticipantState;
import tc.oc.pgm.api.player.event.MatchPlayerDeathEvent;
import tc.oc.pgm.wool.PlayerWoolPlaceEvent;

import java.util.UUID;

public class Trackers implements Listener {

    private SQLGetter data;
    public Trackers(SQLGetter data) {
        this.data = data;
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
        if (killer != null && !event.isSuicide()) {
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
}
