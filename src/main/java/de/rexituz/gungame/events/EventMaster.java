package de.rexituz.gungame.events;

import de.rexituz.gungame.main.Main;
import org.bukkit.Bukkit;

public class EventMaster {
    public static void registerEvents() {
        Bukkit.getServer().getPluginManager().registerEvents(new PlayerDeathListener(), Main.getInstance().getPlugin());
        Bukkit.getServer().getPluginManager().registerEvents(new PlayerJoinLeaveListener(), Main.getInstance().getPlugin());
        Bukkit.getServer().getPluginManager().registerEvents(new PlayerMoveListener(), Main.getInstance().getPlugin());
        Bukkit.getServer().getPluginManager().registerEvents(new DisabledEventsListener(), Main.getInstance().getPlugin());
        Bukkit.getServer().getPluginManager().registerEvents(new PlayerDamageListener(), Main.getInstance().getPlugin());
    }
}
