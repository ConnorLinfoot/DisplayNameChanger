package com.connorlinfoot.displaynamechanger;

import org.bukkit.Bukkit;
import org.bukkit.ChatColor;
import org.bukkit.command.CommandExecutor;
import org.bukkit.command.CommandSender;
import org.bukkit.entity.Player;
import org.kitteh.tag.TagAPI;

import java.util.HashMap;

public class Command implements CommandExecutor {

    static HashMap<String,String> names = new HashMap<String, String>();

    @Override
    public boolean onCommand(CommandSender sender, org.bukkit.command.Command command, String s, String[] args) {
        if( args.length == 0 ) {
            sender.sendMessage(ChatColor.RED + "Usage: /cdn <displayname> OR /cdn <player> <displayname>");
            return false;
        } else if( args.length == 1 ) {
            if( !(sender instanceof Player) )
                return false;
            Player p = (Player) sender;
            if( !p.hasPermission("displayname.change.self") ){
                p.sendMessage(ChatColor.RED + "You do not have permission to use that");
                return false;
            }
            if (args[0].length() > 16) {
                sender.sendMessage(ChatColor.RED + "Display name can not be more than 16 characters");
                return false;
            }

            names.put(p.getName(), ChatColor.translateAlternateColorCodes('&',args[0]));
            if( Main.getInstance().getConfig().getBoolean("Change Chat Display Name") ) p.setDisplayName(ChatColor.translateAlternateColorCodes('&',args[0]));
            TagAPI.refreshPlayer(p);

            p.sendMessage("Your display name has been changed to " + args[0]);
        } else if( args.length == 2 ){
            if( sender instanceof Player ){
                Player p = (Player) sender;
                if( !p.hasPermission("displayname.change.others") ){
                    p.sendMessage(ChatColor.RED + "You do not have permission to use that");
                    return false;
                }
            }

            if(Bukkit.getPlayer(args[0]) == null ){
                sender.sendMessage(ChatColor.RED + "Player not found");
                return false;
            }

            if(Bukkit.getPlayer(args[0]).hasPermission("displayname.change.ignore")){
                sender.sendMessage(ChatColor.RED + "You can not change that players display name");
                return false;
            }

            if (args[1].length() > 16) {
                sender.sendMessage(ChatColor.RED + "Display name can not be more than 16 characters");
                return false;
            }

            names.put(Bukkit.getPlayer(args[0]).getName(), ChatColor.translateAlternateColorCodes('&',args[1]));
            if( Main.getInstance().getConfig().getBoolean("Change Chat Display Name") ) Bukkit.getPlayer(args[0]).setDisplayName(ChatColor.translateAlternateColorCodes('&',args[1]));
            TagAPI.refreshPlayer(Bukkit.getPlayer(args[0]));
        }

        return false;
    }
}
