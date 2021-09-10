package pgmst.pgmst.Trackers;

import org.bukkit.Bukkit;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import tc.oc.pgm.api.player.MatchPlayer;
import tc.oc.pgm.api.player.ParticipantState;
import tc.oc.pgm.api.player.event.MatchPlayerDeathEvent;

import java.util.UUID;

public class PlayerKillTracker implements Listener {

    @EventHandler
    public void onDeath(final MatchPlayerDeathEvent event) {
        //Get the victim uuid
        MatchPlayer victim = event.getVictim();
        UUID victimUUID = victim.getId();

        //Get the killer UUID if exists, or it's not a suicide
        ParticipantState killer = event.getKiller();
        if (killer != null ||  killer.getId() != victimUUID) {
            UUID killerUUID = killer.getId();
            Bukkit.broadcastMessage(killerUUID + "Ha matado a " + victimUUID); //kill
        } else {
            Bukkit.broadcastMessage(victimUUID + "Ha muerto"); //Environmental death
        }


    }
}
