package de.rexituz.gungame.events;

import org.bukkit.ChatColor;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.player.AsyncPlayerChatEvent;

public class ChatListener implements Listener {
    @EventHandler
    private void onChat(AsyncPlayerChatEvent event) {
        event.setFormat("%s " + ChatColor.GRAY + " » " + ChatColor.WHITE + "%s");
    }
}
