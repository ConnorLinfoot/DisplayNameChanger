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
        if( !(sender instanceof Player) )
            return false;

        Player p = (Player) sender;
        if( !p.hasPermission("displayname.change") ){
            p.sendMessage(ChatColor.RED + "You do not have permission to use that");
            return false;
        }

        if( args.length == 0 ) {
            p.sendMessage(ChatColor.RED + "Usage: /cdn <displayname> OR /cdn <player> <displayname>");
            return false;
        }

        if( args.length == 1 ) {
            if (args[0].length() > 16) {
                p.sendMessage(ChatColor.RED + "Can not be more than 16 characters");
                return false;
            }


            names.put(p.getName(), args[0]);
            TagAPI.refreshPlayer(p);

            p.sendMessage("Your display name has been changed to " + args[0]);
        } else if( args.length == 2 ){
            if(Bukkit.getPlayer(args[0]) == null ){
                p.sendMessage(ChatColor.RED + "Player not found");
                return false;
            }

            if (args[1].length() > 16) {
                p.sendMessage(ChatColor.RED + "Can not be more than 16 characters");
                return false;
            }

            names.put(Bukkit.getPlayer(args[0]).getName(), args[1]);
            TagAPI.refreshPlayer(Bukkit.getPlayer(args[0]));
        }

        return false;
    }

}
