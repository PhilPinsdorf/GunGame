package de.rexituz.gungame;

import de.rexituz.gungame.config.ConfigHandler;
import de.rexituz.gungame.events.EventMaster;
import de.rexituz.gungame.player.PlayerHandler;
import de.rexituz.gungame.scoreboard.ScoreboardHandler;
import dev.derklaro.aerogel.Inject;
import dev.derklaro.aerogel.Singleton;
import eu.cloudnetservice.driver.permission.PermissionManagement;
import eu.cloudnetservice.ext.platforminject.api.PlatformEntrypoint;
import eu.cloudnetservice.ext.platforminject.api.stereotype.Dependency;
import eu.cloudnetservice.ext.platforminject.api.stereotype.PlatformPlugin;
import org.bukkit.*;
import org.bukkit.entity.Player;
import org.bukkit.plugin.Plugin;
import org.checkerframework.checker.nullness.qual.NonNull;

@Singleton
@PlatformPlugin(
        platform = "bukkit",
        name = "GunGame",
        version = "0.1",
        authors = "rexituz",
        pluginFileNames = "plugin.yml",
        api = "1.19",
        description = "A simple GunGame plugin for the Game Night.",
        dependencies = @Dependency(name = "CloudNet-CloudPerms")
)

public final class GunGame implements PlatformEntrypoint {
    private static GunGame instance;
    private final Plugin plugin;
    PermissionManagement permissionManagement;
    private final static String prefix = ChatColor.DARK_GRAY + "[" + ChatColor.DARK_AQUA + "GunGame" + ChatColor.DARK_GRAY + "] ";

    @Inject
    private GunGame(
            @NonNull Plugin plugin,
            @NonNull PermissionManagement permissionManagement)
    {
        instance = this;
        this.plugin = plugin;
        this.permissionManagement = permissionManagement;
    }

    @Override
    public void onLoad() {
        // Plugin startup logic
        EventMaster.registerEvents();
        ConfigHandler.loadConfig();

        World world = Bukkit.getWorld("world");

        if(world != null) {
            world.setGameRule(GameRule.DO_DAYLIGHT_CYCLE, false);
            world.setGameRule(GameRule.DO_MOB_SPAWNING, false);
            world.setGameRule(GameRule.NATURAL_REGENERATION, false);
            world.setGameRule(GameRule.ANNOUNCE_ADVANCEMENTS, false);

            world.setTime(1000);
            world.setDifficulty(Difficulty.HARD);
        }

        for (Player player : Bukkit.getOnlinePlayers()) {
            PlayerHandler.spawnPlayer(player);
        }

        ScoreboardHandler.runUpdates();
    }

    @Override
    public void onDisable() {
        // Plugin shutdown logic
    }

    public static GunGame getInstance() {
        return instance;
    }

    public Plugin getPlugin() {
        return plugin;
    }

    public static String getPrefix() {
        return prefix;
    }

    public PermissionManagement getPermissionManagement() {
        return permissionManagement;
    }
}
