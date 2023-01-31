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
        if(Bukkit.getScoreboardManager() == null) return;

        Scoreboard board = Bukkit.getScoreboardManager().getNewScoreboard();
        Objective obj = board.registerNewObjective("sidebar", Criteria.DUMMY, ChatColor.DARK_AQUA + "" + ChatColor.BOLD + "GunGame");
        obj.setDisplaySlot(DisplaySlot.SIDEBAR);

        obj.getScore(ChatColor.GREEN + "Map:").setScore(15);
        obj.getScore(ChatColor.GOLD + "Mystischer See").setScore(14);

        obj.getScore("  ").setScore(13);

        obj.getScore(ChatColor.GREEN + "Online:").setScore(12);

        Team onlineCounter = board.registerNewTeam("onlineCounter");
        onlineCounter.addEntry(ChatColor.BLACK + "" + ChatColor.WHITE);
        onlineCounter.setPrefix(ChatColor.GOLD + "" + Bukkit.getOnlinePlayers().size() + ChatColor.DARK_GRAY + "/" + ChatColor.GOLD + Bukkit.getMaxPlayers());
        obj.getScore(ChatColor.BLACK + "" + ChatColor.WHITE).setScore(11);

        obj.getScore("   ").setScore(10);

        obj.getScore(ChatColor.GREEN + "Level:").setScore(9);

        Team levelCounter = board.registerNewTeam("levelCounter");
        levelCounter.addEntry(ChatColor.RED + "" + ChatColor.WHITE);
        levelCounter.setPrefix(ChatColor.GOLD + "" + PlayerHandler.getPlayerLevel(player));
        obj.getScore(ChatColor.RED + "" + ChatColor.WHITE).setScore(8);

        obj.getScore("    ").setScore(7);

        obj.getScore(ChatColor.GREEN + "Kills:").setScore(6);

        Team killCounter = board.registerNewTeam("killCounter");
        killCounter.addEntry(ChatColor.GREEN + "" + ChatColor.WHITE);
        killCounter.setPrefix(ChatColor.GOLD + "" + PlayerHandler.getPlayerKills(player));
        obj.getScore(ChatColor.GREEN + "" + ChatColor.WHITE).setScore(5);

        player.setScoreboard(board);
    }

    public static void updateScoreBoard(Player player) {
        Scoreboard board = player.getScoreboard();

        Team onlineCounter = board.getTeam("onlineCounter");
        Team levelCounter = board.getTeam("levelCounter");
        Team killCounter = board.getTeam("killCounter");

        if(onlineCounter == null) return;
        onlineCounter.setPrefix(ChatColor.GOLD + "" + Bukkit.getOnlinePlayers().size() + ChatColor.DARK_GRAY +
                                "/" + ChatColor.GOLD + Bukkit.getMaxPlayers());
        if(levelCounter == null) return;
        levelCounter.setPrefix(ChatColor.GOLD + "" + PlayerHandler.getPlayerLevel(player));
        if(killCounter == null) return;
        killCounter.setPrefix(ChatColor.GOLD + "" + PlayerHandler.getPlayerKills(player));
    }

    public static void runUpdates() {
        Bukkit.getScheduler().scheduleSyncRepeatingTask(Main.getInstance().getPlugin(), () -> {
            for (Player player : Bukkit.getOnlinePlayers()) {
                updateScoreBoard(player);
            }
        },  0, 10);
    }
}
