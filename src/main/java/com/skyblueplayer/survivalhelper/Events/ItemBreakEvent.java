package com.skyblueplayer.survivalhelper.Events;

import com.skyblueplayer.survivalhelper.Handlers.AutoItemInAction;
import com.skyblueplayer.survivalhelper.Main;
import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.EventPriority;
import org.bukkit.event.Listener;
import org.bukkit.event.block.Action;
import org.bukkit.event.player.PlayerInteractEvent;
import org.bukkit.event.player.PlayerItemBreakEvent;
import org.bukkit.inventory.EquipmentSlot;
import org.bukkit.inventory.ItemStack;

import java.util.List;

public class ItemBreakEvent implements Listener {
    private final Main plugin;
    private Action playeraction;
    private EquipmentSlot equipHand;
    private final AutoItemInAction autoItemInAction = new AutoItemInAction();

    public ItemBreakEvent(Main plugin) {
        this.plugin = plugin;
        plugin.getServer().getPluginManager().registerEvents(this, plugin);
    }

    @EventHandler
    public void onItemBreak(PlayerItemBreakEvent e) {
        ItemStack brokenItem = e.getBrokenItem();
        Player p = e.getPlayer();
        List<String> itemBlacklist = Main.getPlugin(Main.class).getConfig().getStringList("AutoItemInAction.ItemBlacklist");
        for (String typeName : itemBlacklist) {
            if (brokenItem.getType().name().equals(typeName.toUpperCase())) {
                return;
            }
        }
        if (brokenItem.getType().name().endsWith("HELMET")
                || brokenItem.getType().name().endsWith("CHESTPLATE") || brokenItem.getType().name().endsWith("LEGGINGS")
                || brokenItem.getType().name().endsWith("BOOST") || brokenItem.getType().name().endsWith("ELYTRA")
        ) {
            return;
        }
        if (brokenItem.getType().name().endsWith("SHOVEL") && playeraction == Action.RIGHT_CLICK_BLOCK ||
                brokenItem.getType().name().endsWith("AXE") && playeraction == Action.RIGHT_CLICK_BLOCK) {
            playeraction = null;
            return;
        }
        if (plugin.getConfig().getBoolean("AutoItemInAction.ToolsBreak")) {
            autoItemInAction.BringItemToHand(p, brokenItem, equipHand);//);
        }
    }

    //ตรวจหาไอเทมชนิดเดียวกันกับที่มันพังเพื่อย้ายมาใส่ในมือผู้เล่น

    @EventHandler(priority = EventPriority.HIGH)
    public void PlayerMouseClick(PlayerInteractEvent e) {
        playeraction = e.getAction();
        equipHand = e.getHand();
    }
}
