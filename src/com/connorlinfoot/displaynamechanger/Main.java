package com.connorlinfoot.displaynamechanger;

import org.bukkit.Bukkit;
import org.bukkit.ChatColor;
import org.bukkit.Server;
import org.bukkit.command.ConsoleCommandSender;
import org.bukkit.plugin.Plugin;
import org.bukkit.plugin.java.JavaPlugin;


public class Main extends JavaPlugin {
    private static Plugin instance;

    public void onEnable() {
        instance = this;
        getConfig().options().copyDefaults(true);
        saveConfig();
        Server server = getServer();
        ConsoleCommandSender console = server.getConsoleSender();

        console.sendMessage(ChatColor.GREEN + "======== DisplayNameChanger ========");
        console.sendMessage(ChatColor.GREEN + "========== VERSION: 1.1.2 ==========");
        console.sendMessage(ChatColor.GREEN + "======== BY CONNOR LINFOOT! ========");

        Bukkit.getPluginManager().registerEvents(new Listener(),this);
        Bukkit.getPluginCommand("cdn").setExecutor(new Command());
    }

    public void onDisable() {
        getLogger().info(getDescription().getName() + " has been disabled!");
    }

    public static Plugin getInstance() {
        return instance;
    }
}
