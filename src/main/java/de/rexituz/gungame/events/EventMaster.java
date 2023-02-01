package de.rexituz.gungame.events;

import de.rexituz.gungame.GunGame;
import org.bukkit.Bukkit;

public class EventMaster {
    public static void registerEvents() {
        Bukkit.getServer().getPluginManager().registerEvents(new PlayerDeathListener(), GunGame.getInstance().getPlugin());
        Bukkit.getServer().getPluginManager().registerEvents(new PlayerJoinLeaveListener(), GunGame.getInstance().getPlugin());
        Bukkit.getServer().getPluginManager().registerEvents(new PlayerMoveListener(), GunGame.getInstance().getPlugin());
        Bukkit.getServer().getPluginManager().registerEvents(new DisabledEventsListener(), GunGame.getInstance().getPlugin());
        Bukkit.getServer().getPluginManager().registerEvents(new PlayerDamageListener(), GunGame.getInstance().getPlugin());
        Bukkit.getServer().getPluginManager().registerEvents(new ChatListener(), GunGame.getInstance().getPlugin());
    }
}
