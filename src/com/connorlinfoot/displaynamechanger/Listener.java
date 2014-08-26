package com.connorlinfoot.displaynamechanger;

import org.bukkit.event.EventHandler;
import org.kitteh.tag.AsyncPlayerReceiveNameTagEvent;

public class Listener implements org.bukkit.event.Listener {

    @EventHandler
    public void onTag(AsyncPlayerReceiveNameTagEvent e){
        if( Command.names.containsKey(e.getNamedPlayer().getName()) ) {
            e.setTag(Command.names.get(e.getNamedPlayer().getName()));
        }
    }

}
