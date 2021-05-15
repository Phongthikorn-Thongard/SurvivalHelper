package com.skyblueplayer.survivalhelper.Events;

import com.skyblueplayer.survivalhelper.Main;
import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.player.PlayerQuitEvent;

public class QuitEvent implements Listener {
    public Main plugin = Main.getPlugin(Main.class);

    public QuitEvent(Main plugin) {
        plugin.getServer().getPluginManager().registerEvents(this, plugin);
    }

    @EventHandler
    public void onQuitEvent(PlayerQuitEvent e) {
        Player p = e.getPlayer();
        plugin.playermetric.remove(p.getUniqueId());
    }
}
