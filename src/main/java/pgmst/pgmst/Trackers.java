package pgmst.pgmst;

import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.player.PlayerJoinEvent;
import pgmst.pgmst.Database.SQLGetter;
import tc.oc.pgm.api.player.MatchPlayerState;
import tc.oc.pgm.api.player.ParticipantState;
import tc.oc.pgm.api.player.event.MatchPlayerDeathEvent;
import tc.oc.pgm.core.CoreBlockBreakEvent;
import tc.oc.pgm.destroyable.DestroyableHealthChangeEvent;
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
        UUID playerUUID = player.getUniqueId();
        if (data.exists(playerUUID)){
            data.updateName(player);
        } else {
            data.createNewPlayer(player);
        }
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

    //      GOAL TRACKERS

    @EventHandler
    public void onWoolCapture(PlayerWoolPlaceEvent event) {
        //Gets the player UUID that captured a wool.
        ParticipantState player = event.getPlayer();
        if (player.getPlayer().isPresent()){
            data.addWools(player.getId());
        }
    }

    @EventHandler
    public void onMonumentBreak(DestroyableHealthChangeEvent event) {
        ParticipantState player = event.getChange().getPlayerCause();
        UUID playerUUID = player.getId();

        if (player.isPlayer(player.getPlayer().get()) && !event.getDestroyable().hasTouched(player)) {
            data.addMonument(playerUUID);
        }
    }

    @EventHandler
    public void onCoreBreak(CoreBlockBreakEvent event) {
        // like the old OCTC website, it is going to track only the touched cores.
        // You can only get one +1 stat per core.
        // (I tried to track just the player that actually leaked the core, but it was a pain to do)

        MatchPlayerState player = event.getPlayer();
        ParticipantState participantState = player.getPlayer().get().getParticipantState();

        if(player.getPlayer().isPresent() && !event.getCore().hasTouched(participantState)) {
            data.addCore(player.getId());
        }
    }
}
