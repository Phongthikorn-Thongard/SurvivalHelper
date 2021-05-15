package com.skyblueplayer.survivalhelper.Handlers.SpecialTools;

import com.skyblueplayer.survivalhelper.Main;
import com.skyblueplayer.survivalhelper.Player.data.PlayerMDSdata;
import com.skyblueplayer.survivalhelper.Player.data.PlayerMeasureData;
import org.bukkit.ChatColor;
import org.bukkit.Sound;
import org.bukkit.entity.Player;
import org.bukkit.inventory.ItemStack;
import org.bukkit.inventory.PlayerInventory;

public class ToolsManager {

    public void DisableWhileMeasureNotInHand(Player p, ToolType toolType) {
        Main plugin = Main.getPlugin(Main.class);
        PlayerInventory playerInventory = p.getInventory();
        PlayerMeasureData playermetric = plugin.playermetric.get(p.getUniqueId());
        PlayerMDSdata playerMDSdata = plugin.playermdspdata.get(p.getUniqueId());
        if (toolType.equals(ToolType.Tape_Measure)) {
            if (!playerInventory.getItemInOffHand().getType().name().equals(plugin.getConfig().getString("Tape_measure.Item").toUpperCase())
                    && !playerInventory.getItemInMainHand().getType().name().equals(plugin.getConfig().getString("Tape_measure.Item").toUpperCase())
            ) {
                p.sendMessage(ChatColor.GOLD + "[Survival Helper] " + ChatColor.RED + "Tape Measure is disabled.");
                playermetric.setEnableMeasureTape(false);
                playermetric.setSelectedIndex(1);

                p.playSound(p.getLocation(), Sound.BLOCK_NOTE_BLOCK_DIDGERIDOO, 10, 1);

            }
        } else if (toolType.equals(ToolType.Mob_spawn_Detector)) {
            if (!playerInventory.getItemInOffHand().getType().name().equals(plugin.getConfig().getString("DetecteMobSpawn.Item").toUpperCase())
                    && !playerInventory.getItemInMainHand().getType().name().equals(plugin.getConfig().getString("DetecteMobSpawn.Item").toUpperCase())
            ) {
                p.sendMessage(ChatColor.GOLD + "[Survival Helper] " + ChatColor.RED + "Mob Spawn Detector is disabled.");
                playerMDSdata.setEnable(false);
                p.playSound(p.getLocation(), Sound.BLOCK_NOTE_BLOCK_DIDGERIDOO, 10, 1);

            }
        }
    }

    public void DisableWhileMeasureItemDrop(Player p, ToolType toolType, ItemStack dropitem) {
        Main plugin = Main.getPlugin(Main.class);
        PlayerMeasureData playermetric = plugin.playermetric.get(p.getUniqueId());
        PlayerMDSdata playerMDSdata = plugin.playermdspdata.get(p.getUniqueId());
        if (toolType.equals(ToolType.Tape_Measure)) {
            if (dropitem.getType().name().equals(plugin.getConfig().getString("Tape_measure.Item").toUpperCase())) {
                p.sendMessage(ChatColor.GOLD + "[Survival Helper] " + ChatColor.RED + "Tape Measure is disabled.");
                playermetric.setEnableMeasureTape(false);
                playermetric.setSelectedIndex(1);

                p.playSound(p.getLocation(), Sound.BLOCK_NOTE_BLOCK_DIDGERIDOO, 10, 1);

            }
        } else if (toolType.equals(ToolType.Mob_spawn_Detector)) {
            if (dropitem.getType().name().equals(plugin.getConfig().getString("DetecteMobSpawn.Item").toUpperCase())) {
                p.sendMessage(ChatColor.GOLD + "[Survival Helper] " + ChatColor.RED + "Mob Spawn Detector is disabled.");
                playerMDSdata.setEnable(false);
                p.playSound(p.getLocation(), Sound.BLOCK_NOTE_BLOCK_DIDGERIDOO, 10, 1);
            }
        }
    }
}
