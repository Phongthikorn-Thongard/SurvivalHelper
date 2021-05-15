package com.skyblueplayer.survivalhelper.Events.SpecialTools;

import com.skyblueplayer.survivalhelper.Handlers.SpecialTools.ToolType;
import com.skyblueplayer.survivalhelper.Handlers.SpecialTools.ToolsManager;
import com.skyblueplayer.survivalhelper.Main;
import com.skyblueplayer.survivalhelper.Player.data.PlayerMDSdata;
import com.skyblueplayer.survivalhelper.Player.data.PlayerMeasureData;
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

public class EnableTapeMeasureEvent implements Listener {

    public EnableTapeMeasureEvent(Main plugin) {
        plugin.getServer().getPluginManager().registerEvents(this, plugin);
    }
    private final Main plugin = Main.getPlugin(Main.class);
    PlayerMeasureData playermetric;
    PlayerMDSdata playerMDSdata;
    ToolEnableAnimation toolAnimation = new ToolEnableAnimation();
    ToolsManager toolsManager = new ToolsManager();
    private long cooldown = 31L;
    private boolean cancelcooldown;
    @EventHandler(priority = EventPriority.HIGH)
    public void onPlayerInteract(PlayerToggleSneakEvent e) {
        Player p = e.getPlayer();
        PlayerInventory inventory = p.getInventory();
        playermetric = plugin.playermetric.get(p.getUniqueId());
        playerMDSdata = plugin.playermdspdata.get(p.getUniqueId());
        ItemStack iteminhand = inventory.getItemInMainHand();
        if (cancelcooldown) {
            cancelcooldown = false;
        }
        if (plugin.getConfig().getBoolean("Tape_measure.Enable")) {
            if (iteminhand.getType().name().equals(plugin.getConfig().getString("Tape_measure.Item").toUpperCase())
            ) {
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
                        if (!playermetric.isEnableMeasureTape()) {
                            toolAnimation.EnablingState(p, (int) cooldown / 5.00, "TapeMeasure");
                            if (cooldown <= 0) {
                                playermetric.setEnableMeasureTape(true);
                                playermetric.setSelectedIndex(1);
                                playerMDSdata.setEnable(false);
                                toolAnimation.MeasureState(p, ChatColor.GOLD + "Tape Measure" + ChatColor.AQUA + " is activated.");
                                this.cancel();
                            }
                        } else if (playermetric.isEnableMeasureTape()) {
                            toolAnimation.DisablingState(p, (int) cooldown / 5.00, "TapeMeasure");
                            if (cooldown <= 0) {
                                playermetric.setEnableMeasureTape(false);
                                playermetric.setSelectedIndex(1);
                                toolAnimation.MeasureState(p, ChatColor.GOLD + "Tape Measure" + ChatColor.RED + " is disabled.");
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
        playermetric = plugin.playermetric.get(p.getUniqueId());
        new BukkitRunnable() {
            @Override
            public void run() {
                if (playermetric.isEnableMeasureTape()) {
                    toolsManager.DisableWhileMeasureNotInHand(p,ToolType.Tape_Measure);
                    cancelcooldown = true;
                }
            }
        }.runTaskLater(Main.getPlugin(Main.class), 1L);
    }
    @EventHandler
    public void playerDropItem(PlayerDropItemEvent e) {
        Player p = e.getPlayer();
        playermetric = plugin.playermetric.get(p.getUniqueId());
        new BukkitRunnable() {
            @Override
            public void run() {
                if (playermetric.isEnableMeasureTape()) {
                    toolsManager.DisableWhileMeasureItemDrop(p,ToolType.Tape_Measure,e.getItemDrop().getItemStack());
                    cancelcooldown = true;
                }
            }
        }.runTaskLater(Main.getPlugin(Main.class), 1L);
    }
}
