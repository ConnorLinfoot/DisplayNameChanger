package com.connorlinfoot.displaynamechanger;

import org.bukkit.Bukkit;
import org.bukkit.ChatColor;
import org.bukkit.Server;
import org.bukkit.command.ConsoleCommandSender;
import org.bukkit.configuration.ConfigurationSection;
import org.bukkit.plugin.Plugin;
import org.bukkit.plugin.java.JavaPlugin;

import java.util.Map;


public class Main extends JavaPlugin {
    private static Plugin instance;

    public void onEnable() {
        instance = this;
        getConfig().options().copyDefaults(true);
        saveConfig();
        Server server = getServer();
        ConsoleCommandSender console = server.getConsoleSender();

        console.sendMessage(ChatColor.GREEN + "======== DisplayNameChanger ========");
        console.sendMessage(ChatColor.GREEN + "=========== VERSION: 2.0 ===========");
        console.sendMessage(ChatColor.GREEN + "======== BY CONNOR LINFOOT! ========");

        if( Main.getInstance().getConfig().getBoolean("Remember Names") && instance.getConfig().getConfigurationSection("Players") != null ) {
            ConfigurationSection cs = instance.getConfig().getConfigurationSection("Players");

            for (String player : cs.getKeys(false)) {
                if (instance.getConfig().get("Players." + player + ".Name") != null) {
                    Command.names.put(player,instance.getConfig().getString("Players." + player + ".Name"));
                }
            }
        }

        Bukkit.getPluginManager().registerEvents(new Listener(),this);
        Bukkit.getPluginCommand("cdn").setExecutor(new Command());
    }

    public void onDisable() {
        if( Main.getInstance().getConfig().getBoolean("Remember Names") ) {
            for (Map.Entry<String, String> entry : Command.names.entrySet()) {
                String key = entry.getKey();
                Object value = entry.getValue();
                getConfig().set("Players." + key + ".Name", value);
            }
            saveConfig();
        }
        getLogger().info(getDescription().getName() + " has been disabled!");
    }

    public static Plugin getInstance() {
        return instance;
    }
}
