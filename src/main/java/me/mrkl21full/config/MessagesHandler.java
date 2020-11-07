package me.mrkl21full.config;

import me.mrkl21full.ClearGlobalChat;
import org.bukkit.ChatColor;

import java.util.HashMap;

/**
 * Class MessageHandler.
 */
public class MessagesHandler {

    /**
     * HashMap for storing all messages
     */
    private static HashMap<String, String> hsMessages;

    static {
        MessagesHandler.hsMessages = new HashMap<>();
    }

    /**
     * Reload messages from messages config file.
     */
    public static void reloadMessages() {
        MessagesHandler.hsMessages.clear();

        for (final String key : ClearGlobalChat.getMessages().getConfigurationSection("").getKeys(false)) {
            MessagesHandler.hsMessages.put(key, replaceColors(ClearGlobalChat.getMessages().getString(key)));
        }

        System.out.println("[ClearGlobalChat] " + MessagesHandler.hsMessages.size() + " messages loaded!");
    }

    /**
     * Get message based on key.
     *
     * @param message
     *   Message key name.
     *
     * @return
     *   Message.
     */
    public static String getMessage(String message) {
        if (message.equalsIgnoreCase("prefix")) {
            return MessagesHandler.hsMessages.get(message);
        }

        String sMessage = MessagesHandler.hsMessages.get(message);

        return MessagesHandler.replaceColors(sMessage == null ?  "&4Error: &cMessage key not found!" : MessagesHandler.hsMessages.get("prefix") + "&r " + sMessage);
    }

    /**
     * Get message without prefix based on key.
     *
     * @param message
     *   Message key name.
     *
     * @return
     *   Message without prefix.
     */
    public static String getMessageWithNoPrefix(String message) {
        return MessagesHandler.hsMessages.getOrDefault(message, MessagesHandler.replaceColors("&4[&bClearChat&4] &4Error: &cMessage key not found!"));
    }

    /**
     * Function to replace color codes based on ChatColors.
     *
     * @param message
     *   Message with color codes (&).
     *
     * @return
     *   Message with correct color codes.
     */
    public static String replaceColors(String message) {
        return ChatColor.translateAlternateColorCodes('&', message);
    }

}

