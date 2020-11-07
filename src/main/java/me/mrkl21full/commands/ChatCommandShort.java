package me.mrkl21full.commands;

import org.bukkit.command.Command;
import org.bukkit.command.CommandExecutor;
import org.bukkit.command.CommandSender;

/**
 * Class ChatCommandShort.
 */
public class ChatCommandShort implements CommandExecutor {

    /**
     * {@inheritDoc}
     */
    @Override
    public boolean onCommand(CommandSender sender, Command command, String label, String[] args) {
        // Check for multiple command instance.
        switch (command.getName()) {
            case "cc": ChatCommand.chatClear(sender); break;
            case "cp": ChatCommand.chatPrivate(sender, args.length == 0 ? null : args[0]); break;
            case "cm": ChatCommand.chatMute(sender); break;
            case "cr": ChatCommand.chatReload(sender); break;
            case "cv": ChatCommand.chatVersion(sender); break;
        }

        // Reetuuurnnnn.
        return false;
    }
}
