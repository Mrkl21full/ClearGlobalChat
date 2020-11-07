package me.mrkl21full.commands;

import me.mrkl21full.ClearGlobalChat;
import me.mrkl21full.config.ConfigLoader;
import me.mrkl21full.config.MessagesHandler;
import org.bukkit.Bukkit;
import org.bukkit.command.Command;
import org.bukkit.command.CommandExecutor;
import org.bukkit.command.CommandSender;
import org.bukkit.entity.Player;

/**
 * Class ChatCommand.
 */
public class ChatCommand implements CommandExecutor {

    /**
     * Determinate if chat is enabled or disabled.
     */
    public static boolean bEnabled;

    /**
     * {@inheritDoc}
     */
    @Override
    public boolean onCommand(CommandSender sender, Command command, String label, String[] args) {
        if(args.length == 0) {
            ChatCommand.chatHelp(sender);
            return false;
        }

        switch (args[0]) {
            case "clear": ChatCommand.chatClear(sender); break;
            case "private": ChatCommand.chatPrivate(sender, args.length == 1 ? null : args[1]); break;
            case "mute": ChatCommand.chatMute(sender); break;
            case "reload": ChatCommand.chatReload(sender); break;
            case "version":ChatCommand.chatVersion(sender); break;
            default: ChatCommand.chatHelp(sender); break;
        }

        return false;
    }

    /**
     * Clears all chats.
     *
     * @param sender
     *   CommandSender.
     */
    static void chatHelp(CommandSender sender) {
        // Header
        sender.sendMessage(MessagesHandler.replaceColors("&a&l========== &4[&bClearGlobalChat&4] &a&l=========="));

        // Commands access
        if (sender.hasPermission("chatclear.clear") || sender.isOp()) sender.sendMessage(MessagesHandler.replaceColors("&7- &b/chat clear &8- &6Clears all chats"));
        if (sender.hasPermission("chatclear.private") || sender.isOp()) sender.sendMessage(MessagesHandler.replaceColors("&7- &b/chat private &8- &6Clears private chats"));
        if (sender.hasPermission("chatclear.mute") || sender.isOp()) sender.sendMessage(MessagesHandler.replaceColors("&7- &b/chat mute &8- &6Toggles chats"));
        if (sender.hasPermission("chatclear.reload") || sender.isOp()) sender.sendMessage(MessagesHandler.replaceColors("&7- &b/chat reload &8- &6Reload plugin configs"));

        // Global plugin command
        sender.sendMessage(MessagesHandler.replaceColors("&7- &b/chat help &8- &6Shows this command"));
        sender.sendMessage(MessagesHandler.replaceColors("&7- &b/chat version &8- &6Shows plugin info"));
    }

    /**
     * Clears all chats.
     *
     * @param sender
     *   CommandSender.
     */
    static void chatClear(CommandSender sender) {
        if (sender.hasPermission("clearchat.clearchat") || sender.isOp()) {
            for (int x = 0; x <= 120; ++x) {
                Bukkit.broadcastMessage("");

                if (x == 120) {
                    Bukkit.broadcastMessage(MessagesHandler.getMessage("clear-chat").replace("%admin%", getPlayerName(sender)));
                }
            }
        } else {
            sender.sendMessage(MessagesHandler.getMessage("no-permission"));
        }
    }

    /**
     * Clears private chat.
     *
     * @param sender
     *   CommandSender.
     * @param target
     *   Client target.
     */
    static void chatPrivate(CommandSender sender, String target) {
        if (sender.hasPermission("clearchat.clearprivatechat") || sender.isOp()) {
            if (!(sender instanceof Player)) {
                return;
            }

            Player player = Bukkit.getPlayer(target == null ? sender.getName() : target);

            if (player == null) {
                sender.sendMessage(MessagesHandler.getMessage("cant-find-player").replace("%player%", target == null ? "Unknown" : target));
                return;
            }

            for (int x = 0; x <= 120; ++x) {
                player.sendMessage("");

                if (x == 120) {
                    player.sendMessage(MessagesHandler.getMessage("clear-private-chat").replace("%admin%", getPlayerName(sender)));
                }
            }
        }  else {
            sender.sendMessage(MessagesHandler.getMessage("no-permission"));
        }
    }

    /**
     * Mute all chats.
     *
     * @param sender
     *   CommandSender.
     */
    static void chatMute(CommandSender sender) {
        if (sender.hasPermission("clearchat.chatmute") || sender.isOp()) {
            if (!ChatCommand.bEnabled) {
                ChatCommand.bEnabled = true;
                sender.sendMessage(MessagesHandler.getMessage("chat-for-players-on"));
                Bukkit.broadcastMessage(MessagesHandler.getMessage("chatmute-chat-enabled").replace("%admin%", getPlayerName(sender)));
            } else {
                ChatCommand.bEnabled = false;
                sender.sendMessage(MessagesHandler.getMessage("chat-for-players-off"));
                Bukkit.broadcastMessage(MessagesHandler.getMessage("chatmute-chat-disabled").replace("%admin%", getPlayerName(sender)));
            }
        } else {
            sender.sendMessage(MessagesHandler.getMessage("no-permission"));
        }
    }

    /**
     * Reloads plugin configuration.
     *
     * @param sender
     *   CommandSender.
     */
    static void chatReload(CommandSender sender) {
        if (sender.hasPermission("clearchat.reload") || sender.isOp()) {
            // Reload config.
            ClearGlobalChat.getInstance().reloadConfig();

            // Reload messages.
            ConfigLoader.reloadMessages();
            MessagesHandler.reloadMessages();

            // Send message.
            sender.sendMessage(MessagesHandler.getMessage("re-loaded"));
        } else {
            sender.sendMessage(MessagesHandler.getMessage("no-permission"));
        }
    }

    /**
     * Shows plugin info.
     *
     * @param sender
     *   CommandSender.
     */
    static void chatVersion(CommandSender sender) {
        sender.sendMessage(MessagesHandler.replaceColors("&4[&bCGC] &7Author: &b") + ClearGlobalChat.getInstance().getDescription().getAuthors());
        sender.sendMessage(MessagesHandler.replaceColors("&4[&bCGC] &7Current version: &b" + ClearGlobalChat.getInstance().getDescription().getVersion()));
        sender.sendMessage(MessagesHandler.replaceColors("&4[&bCGC] &7Plugin spigot page: &bhttps://www.spigotmc.org/resources/clearglobalchat.85538/"));
    }

    /**
     * Get player display name.
     *
     * @param sender
     *   CommandSender.
     *
     * @return String
     *   Player display name.
     */
    private static String getPlayerName(CommandSender sender) {
        return (!(sender instanceof Player)) ? sender.getName() : ((Player) sender).getDisplayName();
    }

}
