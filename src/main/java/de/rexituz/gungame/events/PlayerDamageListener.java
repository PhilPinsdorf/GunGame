package de.rexituz.gungame.events;

import de.rexituz.gungame.player.PlayerHandler;
import de.rexituz.gungame.positions.PositionHandler;
import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.entity.EntityDamageByEntityEvent;
import org.bukkit.event.entity.EntityDamageEvent;

public class PlayerDamageListener implements Listener {
    @EventHandler
    private void onDamage(EntityDamageByEntityEvent event) {
        if(!(event.getEntity() instanceof Player && event.getDamager() instanceof Player)) return;

        Player victim = (Player) event.getEntity();
        Player damager = (Player) event.getDamager();

        if (PositionHandler.isInNoHitZone(victim.getLocation()) || PositionHandler.isInNoHitZone(damager.getLocation())) {
            event.setCancelled(true);
            return;
        }

        PlayerHandler.getPlayerLastHitBy().put(victim.getName(), damager.getName());
        PlayerHandler.getPlayerLastHitWhen().put(victim.getName(), System.currentTimeMillis());
    }

    @EventHandler
    private void onFallDamage(EntityDamageEvent event) {
        if(!(event.getEntity() instanceof Player)) return;
        if (event.getCause() != EntityDamageEvent.DamageCause.FALL) return;
        event.setCancelled(true);
    }
}
