package uwu.smsgamer.uwunotify;

import org.bukkit.plugin.java.JavaPlugin;
import uwu.smsgamer.uwunotify.Commands.Commands;

import java.io.File;

public final class UwUNotify extends JavaPlugin {

    public static UwUNotify instance;

    @Override
    public void onEnable() {
        instance = this;
        Commands cmds = new Commands();
        getCommand("notify").setExecutor(cmds);
        loadConfig();
    }

    @Override
    public void onDisable() {
        // Plugin shutdown logic
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
