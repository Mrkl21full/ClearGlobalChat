package me.mrkl21full.config;

import me.mrkl21full.ClearGlobalChat;

import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.net.URL;
import java.net.URLConnection;

/**
 * Class VersionHandler.
 */
public class VersionHandler {

    /**
     * Check if there is a new version of plugin.
     *
     * @return String
     *   New version number, or null if there is no new version.
     */
    public static String getNewVersion() {
        final int iProject = 85538;

        try {
            final URLConnection conn = new URL("https://api.spigotmc.org/legacy/update.php?resource=" + iProject).openConnection();
            final String sNewVersion = new BufferedReader(new InputStreamReader(conn.getInputStream())).readLine();
            final String sCurrentVersion = ClearGlobalChat.getInstance().getDescription().getVersion();

            return getVersionNumber(sNewVersion) > getVersionNumber(sCurrentVersion) ? sNewVersion : null;
        } catch (Exception e) {
            System.err.println("[ClearGlobalChat] Error: " + e.getMessage());
        }

        return null;
    }

    /**
     * Escape version name to int value.
     *
     * @param version
     *   Version in string format.
     *
     * @return
     *   Version format in int format.
     */
    private static int getVersionNumber(final String version) {
        return Integer.parseInt(version.replace(".", ""));
    }

}
