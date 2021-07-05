package me.charashenko.letmein;

import org.bukkit.Bukkit;
import org.bukkit.plugin.Plugin;
import org.bukkit.plugin.java.JavaPlugin;

public final class LetMeIn extends JavaPlugin {

    private static final Database database = new Database();
    private static final Plugin plugin = Bukkit.getPluginManager().getPlugin("LetMeIn");

    @Override
    public void onEnable() {
        // Plugin startup logic
        getConfig().options().copyDefaults();
        saveDefaultConfig();

        database.createDatabase();
        database.connect();

        Bukkit.getServer().getPluginManager().registerEvents(new EventHandlers(), this);
    }

    @Override
    public void onDisable() {
        // Plugin shutdown logic
        //database.dropDatabase();
    }

    public static Database getDatabase() {
        return database;
    }

    public static Plugin getPlugin() {
        return plugin;
    }
}
