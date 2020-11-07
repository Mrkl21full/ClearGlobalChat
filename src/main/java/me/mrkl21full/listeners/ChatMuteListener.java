package me.mrkl21full.listeners;

import me.mrkl21full.commands.ChatCommand;
import me.mrkl21full.config.MessagesHandler;
import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.EventPriority;
import org.bukkit.event.Listener;
import org.bukkit.event.player.AsyncPlayerChatEvent;

/**
 * Class ChatMuteListener.
 */
public class ChatMuteListener implements Listener {

    /**
     * Async player chat event.
     *
     * @param event
     *   AsyncPlayerChatEvent.
     */
    @EventHandler(priority = EventPriority.HIGHEST)
    public void onAsyncPlayerChat(final AsyncPlayerChatEvent event) {
        Player p = event.getPlayer();

        if (!ChatCommand.bEnabled && !p.hasPermission("clearchat.mute.ignore")) {
            p.sendMessage(MessagesHandler.getMessage("chat-disabled"));
            event.setCancelled(true);
        }
    }

}
