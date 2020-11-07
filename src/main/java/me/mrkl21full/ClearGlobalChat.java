package me.mrkl21full;

import me.mrkl21full.commands.ChatCommand;
import me.mrkl21full.commands.ChatCommandShort;
import me.mrkl21full.config.ConfigLoader;
import me.mrkl21full.config.DatabaseLoader;
import me.mrkl21full.config.MessagesHandler;
import me.mrkl21full.config.VersionHandler;
import me.mrkl21full.listeners.ChatMuteListener;
import me.mrkl21full.listeners.PlayerJoinListener;
import org.bukkit.Bukkit;
import org.bukkit.configuration.file.FileConfiguration;
import org.bukkit.plugin.java.JavaPlugin;

import java.io.File;

/**
 * Main class ClearGlobalChat.
 */
public final class ClearGlobalChat extends JavaPlugin {

    /**
     * Main plugin instance.
     */
    private static ClearGlobalChat iInstance;

    /**
     * Config file.
     */
    private static File fConfig;

    /**
     * Messages file configuration.
     */
    private static FileConfiguration fcMessages;

    /**
     * {@inheritDoc}
     */
    @Override
    public void onEnable() {
        iInstance = this;

        // Initialize config.
        fConfig = new File(this.getDataFolder(), "config.yml");

        // Copy config configuration.
        this.getConfig().options().copyDefaults(true);
        this.saveDefaultConfig();
        this.reloadConfig();

        // Initialize messages.
        fcMessages = new DatabaseLoader("plugins/ClearGlobalChat", "messages.yml").getFileConfiguration();

        // Reload / initialize new ones.
        ConfigLoader.reloadMessages();
        MessagesHandler.reloadMessages();

        // Register commands.
        this.getCommand("chat").setExecutor(new ChatCommand());

        // Register shorter commands (if enabled).
        if (this.getConfig().getBoolean("Shorter_Version_Commands", true)) {
            // Short: /chat clear
            this.getCommand("cc").setExecutor(new ChatCommandShort());
            // Short: /chat cp
            this.getCommand("cp").setExecutor(new ChatCommandShort());
            // Short: /chat mute
            this.getCommand("cm").setExecutor(new ChatCommandShort());
            // Short: /chat reload
            this.getCommand("cr").setExecutor(new ChatCommandShort());
            // Short: /chat version
            this.getCommand("cv").setExecutor(new ChatCommandShort());
        }

        // Reset variables.
        ChatCommand.bEnabled = true;

        // Register events.
        Bukkit.getPluginManager().registerEvents(new ChatMuteListener(), this);
        Bukkit.getPluginManager().registerEvents(new PlayerJoinListener(), this);

        // Post onEnable.
        System.out.println("[ClearGlobalChat] Version: v" + this.getDescription().getVersion());
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public void onDisable() {
        System.out.println("[ClearGlobalChat] Version: v" + this.getDescription().getVersion());
    }

    /**
     * Get main plugin instance.
     *
     * @return
     *   ClearGlobalChat instance.
     */
    public static ClearGlobalChat getInstance() {
        return iInstance;
    }

    /**
     * Get messages file configuration.
     *
     * @return
     *   File configuration.
     */
    public static FileConfiguration getMessages() {
        return fcMessages;
    }

}
