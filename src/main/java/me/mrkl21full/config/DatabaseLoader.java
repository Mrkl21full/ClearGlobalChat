package me.mrkl21full.config;

import org.bukkit.configuration.InvalidConfigurationException;
import org.bukkit.configuration.file.FileConfiguration;
import org.bukkit.configuration.file.YamlConfiguration;

import java.io.File;
import java.io.IOException;

/**
 * Class DatabaseLoader.
 */
public class DatabaseLoader {

    /**
     * File variable.
     */
    private File fFile;

    /**
     * Path location variable.
     */
    private String sPath;

    /**
     * File name variable.
     */
    private String sFileName;

    /**
     * Constructor.
     *
     * @param sPath
     *   File path.
     * @param sFileName
     *   File name.
     */
    public DatabaseLoader(final String sPath, final String sFileName) {
        this.sPath = sPath;
        this.sFileName = sFileName;
    }

    /**
     * Get file configuration or create it if not exits.
     *
     * @return
     *   File configuration.
     */
    public FileConfiguration getFileConfiguration() {
        this.fFile = new File(this.sPath, this.sFileName);

        if (!this.fFile.exists()) {
            if (!new File(this.sPath).mkdirs()) {
                System.err.println("[ClearGlobalChat] Error: Can't create plugin directory (or it already exits)!");
            }

            try {
                if (!this.fFile.createNewFile()) {
                    System.err.println("[ClearGlobalChat] Error: Can't initialize config file!");
                    return null;
                }
            } catch (IOException e) {
                System.err.println("[ClearGlobalChat] Error: " + e.getMessage());
                return null;
            }
        }

        final FileConfiguration fileConfiguration = new YamlConfiguration();

        try {
            fileConfiguration.load(this.fFile);
        } catch (IOException | InvalidConfigurationException e) {
            System.err.println("[ClearGlobalChat] Error: " + e.getMessage());
            return null;
        }

        return fileConfiguration;
    }

}
