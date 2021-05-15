package com.skyblueplayer.survivalhelper.Events.SpecialTools;

import com.skyblueplayer.survivalhelper.Handlers.SpecialTools.ToolType;
import com.skyblueplayer.survivalhelper.Handlers.SpecialTools.ToolsManager;
import com.skyblueplayer.survivalhelper.Main;
import com.skyblueplayer.survivalhelper.Player.data.PlayerMDSdata;
import com.skyblueplayer.survivalhelper.Player.data.PlayerMeasureData;
import com.skyblueplayer.survivalhelper.Player.runnable.LightDetectorRunnable;
import com.skyblueplayer.survivalhelper.utils.animation.ToolEnableAnimation;
import org.bukkit.ChatColor;
import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.EventPriority;
import org.bukkit.event.Listener;
import org.bukkit.event.player.PlayerDropItemEvent;
import org.bukkit.event.player.PlayerItemHeldEvent;
import org.bukkit.event.player.PlayerToggleSneakEvent;
import org.bukkit.inventory.ItemStack;
import org.bukkit.inventory.PlayerInventory;
import org.bukkit.scheduler.BukkitRunnable;

public class EnableMDTSevent implements Listener {
    private final Main plugin = Main.getPlugin(Main.class);
    PlayerMDSdata playerMDSdata;
    PlayerMeasureData playerMeasureData;
    ToolEnableAnimation toolAnimation = new ToolEnableAnimation();
    ToolsManager toolsManager = new ToolsManager();
    private long cooldown = 31L;
    private boolean cancelcooldown;

    public EnableMDTSevent(Main plugin) {
        plugin.getServer().getPluginManager().registerEvents(this, plugin);
    }

    @EventHandler(priority = EventPriority.HIGH)
    public void onPlayerInteract(PlayerToggleSneakEvent e) {
        Player p = e.getPlayer();
        PlayerInventory inventory = p.getInventory();
        playerMDSdata = plugin.playermdspdata.get(p.getUniqueId());
        playerMeasureData = plugin.playermetric.get(p.getUniqueId());
        LightDetectorRunnable lightDetectorRunnable = new LightDetectorRunnable();
        ItemStack iteminhand = inventory.getItemInMainHand();
        if (cancelcooldown) {
            cancelcooldown = false;
        }
        if (plugin.getConfig().getBoolean("DetecteMobSpawn.Enable")) {
            if (iteminhand.getType().name().equals(plugin.getConfig().getString("DetecteMobSpawn.Item").toUpperCase())) {
                new BukkitRunnable() {
                    @Override
                    public void run() {
                        cooldown--;
                        if (!p.isSneaking()) {
                            cancelcooldown = true;
                        }
                        if (cancelcooldown) {
                            cooldown = 31L;
                            cancelcooldown = false;
                            this.cancel();
                        }
                        if (!playerMDSdata.isEnable()) {
                            toolAnimation.EnablingState(p, (int) cooldown / 5.00, "Mob Spawn Detect");
                            if (cooldown <= 0) {
                                playerMDSdata.setEnable(true);
                                playerMeasureData.setEnableMeasureTape(false);
                                playerMeasureData.setSelectedIndex(1);
                                lightDetectorRunnable.LightDetectorRun(p);
                                toolAnimation.MeasureState(p, ChatColor.GOLD + "Mob Spawn Detect" + ChatColor.AQUA + " is activated.");
                                this.cancel();
                            }
                        } else if (playerMDSdata.isEnable()) {
                            toolAnimation.DisablingState(p, (int) cooldown / 5.00, "MobSpawnDetect");
                            if (cooldown <= 0) {
                                playerMDSdata.setEnable(false);
                                toolAnimation.MeasureState(p, ChatColor.GOLD + "Mob Spawn Detect" + ChatColor.RED + " is disabled.");
                                this.cancel();
                            }
                        }
                    }
                }.runTaskTimer(plugin, 0L, 1L);
            }
        }
    }

    @EventHandler(priority = EventPriority.HIGHEST)
    public void onPlayerheldItem(PlayerItemHeldEvent e) {
        Player p = e.getPlayer();

        playerMDSdata = plugin.playermdspdata.get(p.getUniqueId());
        new BukkitRunnable() {
            @Override
            public void run() {
                if (cancelcooldown = false) {
                    cancelcooldown = true;
                }
                if (playerMDSdata.isEnable()) {
                    toolsManager.DisableWhileMeasureNotInHand(p, ToolType.Mob_spawn_Detector);
                    cancelcooldown = true;
                }
            }
        }.runTaskLater(Main.getPlugin(Main.class), 1L);
    }

    @EventHandler
    public void playerDropItem(PlayerDropItemEvent e) {
        Player p = e.getPlayer();
        playerMDSdata = plugin.playermdspdata.get(p.getUniqueId());
        new BukkitRunnable() {
            @Override
            public void run() {
                if (playerMDSdata.isEnable()) {
                    toolsManager.DisableWhileMeasureItemDrop(p, ToolType.Mob_spawn_Detector, e.getItemDrop().getItemStack());
                    cancelcooldown = true;
                }
            }
        }.runTaskLater(Main.getPlugin(Main.class), 1L);
    }
}
