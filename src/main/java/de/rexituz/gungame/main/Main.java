package de.rexituz.gungame.main;

import de.rexituz.gungame.events.EventMaster;
import de.rexituz.gungame.player.PlayerHandler;
import de.rexituz.gungame.scoreboard.ScoreboardHandler;
import org.bukkit.*;
import org.bukkit.entity.Player;
import org.bukkit.plugin.java.JavaPlugin;

public final class Main extends JavaPlugin {
    private static Main plugin;
    private final static String prefix = ChatColor.DARK_GRAY + "[" + ChatColor.DARK_AQUA + "GunGame" + ChatColor.DARK_GRAY + "] ";

    @Override
    public void onEnable() {
        // Plugin startup logic
        plugin = this;
        EventMaster.registerEvents();

        for (Player player : Bukkit.getOnlinePlayers()) {
            PlayerHandler.spawnPlayer(player);
        }

        World world = Bukkit.getWorld("world");
        world.setGameRule(GameRule.DO_DAYLIGHT_CYCLE, false);
        world.setGameRule(GameRule.DO_MOB_SPAWNING, false);
        world.setGameRule(GameRule.NATURAL_REGENERATION, false);

        world.setTime(1000);
        world.setDifficulty(Difficulty.HARD);

        ScoreboardHandler.runUpdates();
    }

    @Override
    public void onDisable() {
        // Plugin shutdown logic
    }

    public static Main getPlugin() {
        return plugin;
    }

    public static String getPrefix() {
        return prefix;
    }
}
