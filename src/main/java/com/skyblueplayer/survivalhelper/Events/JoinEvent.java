package com.skyblueplayer.survivalhelper.Events;

import com.skyblueplayer.survivalhelper.Main;
import com.skyblueplayer.survivalhelper.Player.data.PlayerMDSdata;
import com.skyblueplayer.survivalhelper.Player.data.PlayerMeasureData;
import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.player.PlayerJoinEvent;

public class JoinEvent implements Listener {

    Main plugin = Main.getPlugin(Main.class);

    public JoinEvent(Main plugin) {
        plugin.getServer().getPluginManager().registerEvents(this, plugin);
    }

    @EventHandler
    public void OnJoin(PlayerJoinEvent e) {
        Player p = e.getPlayer();
        plugin.playermetric.put(p.getUniqueId(), new PlayerMeasureData(p.getUniqueId(), null, null, 1, false));
        plugin.playermdspdata.put(p.getUniqueId(), new PlayerMDSdata(false));
    }
}
