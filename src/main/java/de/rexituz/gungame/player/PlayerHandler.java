package de.rexituz.gungame.player;

import de.rexituz.gungame.equipment.Equipments;
import de.rexituz.gungame.equipment.LevelEquipment;
import de.rexituz.gungame.main.Main;
import de.rexituz.gungame.positions.Positions;
import de.rexituz.gungame.scoreboard.ScoreboardHandler;
import org.bukkit.Bukkit;
import org.bukkit.ChatColor;
import org.bukkit.Sound;
import org.bukkit.entity.Player;
import org.bukkit.inventory.ItemStack;

import java.util.HashMap;

public class PlayerHandler {
    private static HashMap<String, Integer> playerLevelMap = new HashMap<String, Integer>();
    private static HashMap<String, String> playerLastHitBy = new HashMap<String, String>();
    private static HashMap<String, Long> playerLastHitWhen = new HashMap<String, Long>();
    private static HashMap<String, Integer> playerSessionKills = new HashMap<String, Integer>();

    public static void registerPlayer(Player player) {
        String name = player.getName();

        if(! playerLevelMap.containsKey(name)) {
            playerLevelMap.put(name, 0);
        }

        if(! playerSessionKills.containsKey(name)) {
            playerSessionKills.put(player.getName(), 0);
            return;
        }

        refreshPlayer(player);
    }

    private static void refreshPlayer(Player player) {
        player.setHealth(20);
        player.setExp(0.999f);
        player.setLevel(getPlayerLevel(player));
        String suffix = ChatColor.DARK_GRAY + " [" + ChatColor.GOLD + "Lvl. " + ChatColor.GREEN + getPlayerLevel(player) + ChatColor.DARK_GRAY + "]";
        player.setPlayerListName(player.getName() + suffix);
        player.setDisplayName(player.getName() + suffix);
        equipPlayer(player);
    }

    public static void increasePlayer(Player player) {
        if(getPlayerLevel(player) + 1 <= maxEquipmentLevel()) {
            playerLevelMap.replace(player.getName(), getPlayerLevel(player) + 1);
        }

        player.playSound(player.getLocation(), Sound.ENTITY_PLAYER_LEVELUP, 1, 1);
        refreshPlayer(player);
    }

    public static void decreasePlayer(Player player) {
        Bukkit.getScheduler().scheduleSyncDelayedTask(Main.getPlugin(), new Runnable() {
            public void run() {
                if(getPlayerLevel(player) - 1 >= 0) {
                    playerLevelMap.replace(player.getName(), getPlayerLevel(player) - 1);
                }

                player.playSound(player.getLocation(), Sound.ENTITY_SKELETON_HURT, 1, 0);
                refreshPlayer(player);
            }
        },  4L);
    }

    public static void equipPlayer(Player player) {
        int level = getPlayerLevel(player);
        LevelEquipment equipment = Equipments.getEquipments().get(level);

        player.getInventory().clear();

        if(equipment.getHelmet() != null) player.getInventory().setHelmet(new ItemStack(equipment.getHelmet()));
        if(equipment.getChestplate() != null) player.getInventory().setChestplate(new ItemStack(equipment.getChestplate()));
        if(equipment.getLeggings() != null) player.getInventory().setLeggings(new ItemStack(equipment.getLeggings()));
        if(equipment.getBoots() != null) player.getInventory().setBoots(new ItemStack(equipment.getBoots()));
        if(equipment.getHand() != null) player.getInventory().setItem(0, new ItemStack(equipment.getHand()));

        player.updateInventory();
    }

    public static void spawnPlayer(Player player) {
        registerPlayer(player);
        player.sendTitle(ChatColor.DARK_AQUA + "GunGame", ChatColor.GRAY + "Level auf mit jedem Kill!", 10, 90, 20);
        player.teleport(Positions.SPAWN.getLocation());
        player.playSound(player.getLocation(), Sound.ENTITY_PLAYER_LEVELUP, 1, 1);
        ScoreboardHandler.setScoreBoard(player);
    }

    public static int getPlayerLevel(Player player) {
        String name = player.getName();
        return playerLevelMap.get(name);
    }

    public static int getPlayerKills(Player player) {
        String name = player.getName();
        return playerSessionKills.get(name);
    }

    private static int maxEquipmentLevel() {
        return Equipments.getEquipments().size() - 1;
    }

    public static HashMap<String, String> getPlayerLastHitBy() {
        return playerLastHitBy;
    }

    public static HashMap<String, Long> getPlayerLastHitWhen() {
        return playerLastHitWhen;
    }

    public static void addKill(Player player) {
        playerSessionKills.put(player.getName(), playerSessionKills.get(player.getName()) + 1);
    }
}
