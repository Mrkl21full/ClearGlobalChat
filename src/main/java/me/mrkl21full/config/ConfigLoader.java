package me.mrkl21full.config;

import me.mrkl21full.ClearGlobalChat;
import org.bukkit.configuration.file.FileConfiguration;

import java.io.IOException;

/**
 * Class ConfigLoader.
 */
public class ConfigLoader {

    /**
     * Get file configuration.
     */
    public static void reloadMessages() {
        final FileConfiguration c = ClearGlobalChat.getMessages();

        // Load or add default key with value.
        c.addDefault("prefix", "&4[&bCGC&4]");
        c.addDefault("no-permission", "&cYou do not have permission to use this command.");
        c.addDefault("re-loaded", "&aSuccessfully reloaded &ball configuration files&a.");
        c.addDefault("console-use-command", "&cOnly player can use commands.");
        c.addDefault("clear-chat", "&6Chat has been cleared by &r%admin%&6.");
        c.addDefault("clear-private-chat", "&6Your chat has been cleared by &r%admin%&6.");
        c.addDefault("chat-for-players-off", "&7Chat for players: &cDisabled&7.");
        c.addDefault("chat-for-players-on", "&7Chat for players: &aEnabled&7.");
        c.addDefault("chatmute-chat-disabled", "&6Chat has been &cdisabled&6 by &r%admin%&6.");
        c.addDefault("chatmute-chat-enabled", "&6Chat has been &aenabled&6 by &r%admin%&6.");
        c.addDefault("chat-disabled", "&6Chat is currently &cdisabled&6.");
        c.addDefault("chat-commands-disabled", "&6Command &c/me&6 is disabled when chat is muted.");
        c.addDefault("cant-find-player", "&cPlayer &4%player%&c is not online.");
        c.addDefault("new-updates", "&6There is a new version &b%new_version%&6. You are using &b%current_version%&6.");
        c.addDefault("failed-updates", "&cFailed to check updates on Spigot.");
        c.options().header("#####  ClearGlobalChat v1.0 - messages.yml  ##### #\n#####      Plugin created by Mrkl21full     ##### #\n#####           File: messages.yml          ##### #\n#####   All configurable messages are here  ##### #\n");
        c.options().copyDefaults(true);

        // Notify about loading.
        System.out.println("[ClearGlobalChat] Loading messages...");

        // Load messages to plugin memory.
        try {
            ClearGlobalChat.getMessages().save("plugins/ClearGlobalChat/messages.yml");
        } catch (IOException e) {
            System.err.println("[ClearGlobalChat] Error: " + e.getMessage());
        }
    }

}

