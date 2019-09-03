package uwu.smsgamer.uwunotify;

import org.bukkit.plugin.java.JavaPlugin;
import uwu.smsgamer.uwunotify.Commands.Commands;

import java.io.File;
/**
 * Main class for this plugin.
 */
public final class UwUNotify extends JavaPlugin {

    public static UwUNotify instance;

    /**
     * Used to do stuff when the plugin gets enabled (after reload, on server start)
     */
    @Override
    public void onEnable() {
        instance = this;
        Commands cmds = new Commands();
        getCommand("notify").setExecutor(cmds);
        loadConfig();
    }

    /**
     * Used to load configuration files: config.yml
     */
    public void loadConfig() {
        File configFile = new File(this.getDataFolder(), "config.yml");
        if (!configFile.exists()) {
            saveDefaultConfig();
        }
    }
}
