package de.rexituz.gungame.events;

import de.rexituz.gungame.player.PlayerHandler;
import de.rexituz.gungame.positions.Positions;
import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.entity.EntityDamageByEntityEvent;

public class PlayerDamageListener implements Listener {
    @EventHandler
    private void onDamage(EntityDamageByEntityEvent event) {
        if(!(event.getEntity() instanceof Player && event.getDamager() instanceof Player)) return;

        if ((event.getDamager().getLocation().getX() > Positions.NO_HIT_POS_1.getLocation().getX() &&
            event.getDamager().getLocation().getY() > Positions.NO_HIT_POS_1.getLocation().getY() &&
            event.getDamager().getLocation().getZ() > Positions.NO_HIT_POS_1.getLocation().getZ() &&
            event.getDamager().getLocation().getX() < Positions.NO_HIT_POS_2.getLocation().getX() &&
            event.getDamager().getLocation().getY() < Positions.NO_HIT_POS_2.getLocation().getY() &&
            event.getDamager().getLocation().getZ() < Positions.NO_HIT_POS_2.getLocation().getZ()) ||
            (event.getEntity().getLocation().getX() > Positions.NO_HIT_POS_1.getLocation().getX() &&
            event.getEntity().getLocation().getY() > Positions.NO_HIT_POS_1.getLocation().getY() &&
            event.getEntity().getLocation().getZ() > Positions.NO_HIT_POS_1.getLocation().getZ() &&
            event.getEntity().getLocation().getX() < Positions.NO_HIT_POS_2.getLocation().getX() &&
            event.getEntity().getLocation().getY() < Positions.NO_HIT_POS_2.getLocation().getY() &&
            event.getEntity().getLocation().getZ() < Positions.NO_HIT_POS_2.getLocation().getZ())
        ) {
            event.setCancelled(true);
            return;
        }

        PlayerHandler.getPlayerLastHitBy().put(event.getEntity().getName(), event.getDamager().getName());
        PlayerHandler.getPlayerLastHitWhen().put(event.getEntity().getName(), System.currentTimeMillis());
    }
}
