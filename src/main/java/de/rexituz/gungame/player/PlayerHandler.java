package de.rexituz.gungame.player;

import de.rexituz.gungame.equipment.Equipments;
import de.rexituz.gungame.equipment.LevelEquipment;
import de.rexituz.gungame.main.Main;
import de.rexituz.gungame.positions.Positions;
import de.rexituz.gungame.scoreboard.ScoreboardHandler;
import eu.cloudnetservice.driver.permission.PermissionGroup;
import eu.cloudnetservice.driver.permission.PermissionManagement;
import eu.cloudnetservice.driver.permission.PermissionUser;
import org.bukkit.Bukkit;
import org.bukkit.ChatColor;
import org.bukkit.Sound;
import org.bukkit.entity.Player;
import org.bukkit.inventory.ItemStack;

import java.util.HashMap;

public class PlayerHandler {
    private static final HashMap<String, Integer> playerLevelMap = new HashMap<>();
    private static final HashMap<String, String> playerLastHitBy = new HashMap<>();
    private static final HashMap<String, Long> playerLastHitWhen = new HashMap<>();
    private static final HashMap<String, Integer> playerSessionKills = new HashMap<>();

    public static void spawnPlayer(Player player) {
        registerPlayer(player);
        player.sendTitle(ChatColor.DARK_AQUA + "GunGame", ChatColor.GRAY + "Level auf mit jedem Kill!", 10, 90, 20);
        player.teleport(Positions.SPAWN.getLocation());
        player.playSound(player.getLocation(), Sound.ENTITY_PLAYER_LEVELUP, 1, 1);
        ScoreboardHandler.setScoreBoard(player);
    }

    public static void registerPlayer(Player player) {
        String name = player.getName();

        if(! playerLevelMap.containsKey(name)) {
            playerLevelMap.put(name, 0);
        }

        if(! playerSessionKills.containsKey(name)) {
            playerSessionKills.put(player.getName(), 0);
        }

        refreshPlayer(player);
    }

    private static void refreshPlayer(Player player) {
        player.setHealth(20);
        player.setExp(0.999f);
        player.setLevel(getPlayerLevel(player));
        String suffix = ChatColor.DARK_GRAY + " [" + ChatColor.GOLD + "Lvl. " + ChatColor.GREEN + getPlayerLevel(player) + ChatColor.DARK_GRAY + "]";
        String color = getPlayerColor(player);
        player.setPlayerListName(color + player.getName() + suffix);
        player.setDisplayName(color + player.getName() + suffix);
        equipPlayer(player);
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

    public static void increasePlayer(Player player) {
        if(getPlayerLevel(player) + 1 <= maxEquipmentLevel()) {
            playerLevelMap.replace(player.getName(), getPlayerLevel(player) + 1);
        }

        player.playSound(player.getLocation(), Sound.ENTITY_PLAYER_LEVELUP, 1, 1);
        refreshPlayer(player);
    }

    public static void decreasePlayer(Player player) {
        Bukkit.getScheduler().scheduleSyncDelayedTask(Main.getInstance().getPlugin(), () -> {
            if(getPlayerLevel(player) - 1 >= 0) {
                playerLevelMap.replace(player.getName(), getPlayerLevel(player) - 1);
            }

            player.playSound(player.getLocation(), Sound.ENTITY_SKELETON_HURT, 1, 0.5f);
            refreshPlayer(player);
        },  4L);
    }

    private static String getPlayerColor(Player player) {
        PermissionManagement permissionManagement = Main.getInstance().getPermissionManagement();
        PermissionUser permissionUser = permissionManagement.user(player.getUniqueId());

        if(permissionUser == null) return ChatColor.WHITE.toString();

        PermissionGroup userPermissionGroup = permissionManagement.highestPermissionGroup(permissionUser);

        if(userPermissionGroup == null) {
            PermissionGroup defaultPermissionGroup = permissionManagement.defaultPermissionGroup();

            if(defaultPermissionGroup == null) return ChatColor.WHITE.toString();

            String unconvertedColor = defaultPermissionGroup.color();
            return ChatColor.COLOR_CHAR + unconvertedColor.substring(1);
        }

        String unconvertedColor = userPermissionGroup.color();
        return ChatColor.COLOR_CHAR + unconvertedColor.substring(1);
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

    public static void addKill(Player player) {
        playerSessionKills.put(player.getName(), playerSessionKills.get(player.getName()) + 1);
    }

    public static HashMap<String, String> getPlayerLastHitBy() {
        return playerLastHitBy;
    }

    public static HashMap<String, Long> getPlayerLastHitWhen() {
        return playerLastHitWhen;
    }
}