package me.mrkl21full.listeners;

import me.mrkl21full.ClearGlobalChat;
import me.mrkl21full.config.MessagesHandler;
import me.mrkl21full.config.VersionHandler;
import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.EventPriority;
import org.bukkit.event.Listener;
import org.bukkit.event.player.PlayerJoinEvent;

/**
 * Class PLayerJoinListener.
 */
public class PlayerJoinListener implements Listener {

    /**
     * Check ClearGlobalChat version (if enabled).
     *
     * @param event
     *   PlayerJoinEvent.
     */
    @EventHandler(priority = EventPriority.NORMAL)
    public void onPlayerJoin(final PlayerJoinEvent event) {
        Player p = event.getPlayer();

        if (!ClearGlobalChat.getInstance().getConfig().getBoolean("Update.Check_Plugin_Updates", true)) {
            return;
        }

        if (ClearGlobalChat.getInstance().getConfig().getBoolean("Update.Notify_Only_Admins", true) && (p.hasPermission("clearchat.update_check") || p.isOp())) {
            return;
        }

        String sNewVersion = VersionHandler.getNewVersion();

        if (sNewVersion != null) {
            p.sendMessage(MessagesHandler.getMessage("new-updates").replaceAll("%current_version%", ClearGlobalChat.getInstance().getDescription().getVersion()).replaceAll("%new_version%", sNewVersion));
        } else {
            p.sendMessage(MessagesHandler.getMessage("failed-updates"));
        }
    }

}
