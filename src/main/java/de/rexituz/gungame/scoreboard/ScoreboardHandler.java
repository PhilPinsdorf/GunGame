package de.rexituz.gungame.scoreboard;

import de.rexituz.gungame.main.Main;
import de.rexituz.gungame.player.PlayerHandler;
import org.bukkit.Bukkit;
import org.bukkit.ChatColor;
import org.bukkit.entity.Player;
import org.bukkit.scoreboard.Criteria;
import org.bukkit.scoreboard.DisplaySlot;
import org.bukkit.scoreboard.Objective;
import org.bukkit.scoreboard.Scoreboard;
import org.bukkit.scoreboard.Team;

public class ScoreboardHandler {
    public static void setScoreBoard(Player player) {
        Scoreboard board = Bukkit.getScoreboardManager().getNewScoreboard();
        Objective obj = board.registerNewObjective("sidebar", Criteria.DUMMY, ChatColor.DARK_AQUA + "" + ChatColor.BOLD + "GunGame");
        obj.setDisplaySlot(DisplaySlot.SIDEBAR);

        obj.getScore(" ").setScore(15);

        obj.getScore(ChatColor.GREEN + "Map:").setScore(14);
        obj.getScore(ChatColor.GOLD + "Mystischer See").setScore(13);

        obj.getScore("  ").setScore(12);

        obj.getScore(ChatColor.GREEN + "Online:").setScore(11);

        Team onlineCounter = board.registerNewTeam("onlineCounter");
        onlineCounter.addEntry(ChatColor.BLACK + "" + ChatColor.WHITE);
        onlineCounter.setPrefix(ChatColor.GOLD + "" + Bukkit.getOnlinePlayers().size() + ChatColor.DARK_GRAY + "/" + ChatColor.GOLD + Bukkit.getMaxPlayers());
        obj.getScore(ChatColor.BLACK + "" + ChatColor.WHITE).setScore(10);

        obj.getScore("   ").setScore(9);

        obj.getScore(ChatColor.GREEN + "Level:").setScore(8);

        Team levelCounter = board.registerNewTeam("levelCounter");
        levelCounter.addEntry(ChatColor.RED + "" + ChatColor.WHITE);
        levelCounter.setPrefix(ChatColor.GOLD + "" + PlayerHandler.getPlayerLevel(player));
        obj.getScore(ChatColor.RED + "" + ChatColor.WHITE).setScore(7);

        obj.getScore("    ").setScore(6);

        obj.getScore(ChatColor.GREEN + "Kills:").setScore(5);

        Team killCounter = board.registerNewTeam("killCounter");
        killCounter.addEntry(ChatColor.GREEN + "" + ChatColor.WHITE);
        killCounter.setPrefix(ChatColor.GOLD + "" + PlayerHandler.getPlayerKills(player));
        obj.getScore(ChatColor.GREEN + "" + ChatColor.WHITE).setScore(4);

        obj.getScore("     ").setScore(3);

        player.setScoreboard(board);
    }

    public static void updateScoreBoard(Player player) {
        Scoreboard board = player.getScoreboard();
        board.getTeam("onlineCounter").setPrefix(ChatColor.GOLD + "" + Bukkit.getOnlinePlayers().size() + ChatColor.DARK_GRAY + "/" + ChatColor.GOLD + Bukkit.getMaxPlayers());
        board.getTeam("levelCounter").setPrefix(ChatColor.GOLD + "" + PlayerHandler.getPlayerLevel(player));
        board.getTeam("killCounter").setPrefix(ChatColor.GOLD + "" + PlayerHandler.getPlayerKills(player));
    }

    public static void runUpdates() {
        Bukkit.getScheduler().scheduleSyncRepeatingTask(Main.getPlugin(), new Runnable() {
            public void run() {
                for (Player player : Bukkit.getOnlinePlayers()) {
                    updateScoreBoard(player);
                }
            }
        },  0, 10);
    }
}
