package de.rexituz.gungame.events;

import de.rexituz.gungame.GunGame;
import de.rexituz.gungame.player.PlayerHandler;
import de.rexituz.gungame.positions.PositionHandler;
import org.bukkit.Bukkit;
import org.bukkit.ChatColor;
import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.entity.PlayerDeathEvent;
import org.bukkit.event.player.PlayerRespawnEvent;

public class PlayerDeathListener implements Listener {
    @EventHandler
    public void onDeath(PlayerDeathEvent event) {
        Player player = event.getEntity();
        Bukkit.getScheduler().scheduleSyncDelayedTask(GunGame.getInstance().getPlugin(), () -> player.spigot().respawn(),  4L);

        event.setDroppedExp(0);
        event.getDrops().clear();
        event.setDeathMessage(GunGame.getPrefix() + ChatColor.GRAY + player.getDisplayName() + ChatColor.GRAY + " starb durch Wasser!");

        if(!(PlayerHandler.getPlayerLastHitWhen().containsKey(player.getName()) && PlayerHandler.getPlayerLastHitWhen().containsKey(player.getName()))) return;

        if(System.currentTimeMillis() - PlayerHandler.getPlayerLastHitWhen().get(player.getName()) <= 2000) {
            String killerName = PlayerHandler.getPlayerLastHitBy().get(player.getName());
            Player killer = Bukkit.getPlayer(killerName);
            if(killer != null) {
                PlayerHandler.increasePlayer(killer);
                PlayerHandler.addKill(killer);
                event.setDeathMessage(GunGame.getPrefix() + ChatColor.GRAY + player.getDisplayName() + ChatColor.GRAY + " starb durch " + killer.getDisplayName() + ChatColor.GRAY + "!");
            }
        }

        PlayerHandler.getPlayerLastHitWhen().remove(player.getName());
        PlayerHandler.getPlayerLastHitBy().remove(player.getName());
    }

    @EventHandler
    private void onRespawn(PlayerRespawnEvent event) {
        event.setRespawnLocation(PositionHandler.getPosition("spawn"));
        PlayerHandler.decreasePlayer(event.getPlayer());
    }
}
