package com.skyblueplayer.survivalhelper.Player.runnable;

import com.skyblueplayer.survivalhelper.Handlers.SpecialTools.LightDetector;
import com.skyblueplayer.survivalhelper.Main;
import com.skyblueplayer.survivalhelper.Player.data.PlayerMDSdata;
import org.bukkit.entity.Player;
import org.bukkit.scheduler.BukkitRunnable;

public class LightDetectorRunnable {

    public Main plguin = Main.getPlugin(Main.class);
    PlayerMDSdata playerMDSdata;
    LightDetector lightDetector = new LightDetector();

    public void LightDetectorRun(Player p) {
        playerMDSdata = plguin.playermdspdata.get(p.getUniqueId());
        new BukkitRunnable() {
            @Override
            public void run() {
                lightDetector.spawnparticleonblock(p, 32);
                if (!playerMDSdata.isEnable()) {
                    this.cancel();
                }
            }
        }.runTaskTimer(Main.getPlugin(Main.class), 0, 25L);
    }
}
