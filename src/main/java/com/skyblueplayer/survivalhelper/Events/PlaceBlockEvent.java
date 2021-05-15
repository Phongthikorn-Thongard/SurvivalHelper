package com.skyblueplayer.survivalhelper.Events;

import com.skyblueplayer.survivalhelper.Handlers.AutoItemInAction;
import com.skyblueplayer.survivalhelper.Main;
import org.bukkit.GameMode;
import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.EventPriority;
import org.bukkit.event.Listener;
import org.bukkit.event.block.BlockPlaceEvent;
import org.bukkit.event.player.PlayerInteractEvent;
import org.bukkit.inventory.EquipmentSlot;
import org.bukkit.inventory.ItemStack;
import org.bukkit.inventory.PlayerInventory;
import org.bukkit.inventory.meta.Damageable;


public class PlaceBlockEvent implements Listener {
    private final AutoItemInAction autoItemInAction = new AutoItemInAction();
    private final Main plugin = Main.getPlugin(Main.class);
    private EquipmentSlot interacthand;

    public PlaceBlockEvent(Main plugin) {
        plugin.getServer().getPluginManager().registerEvents(this, (plugin));
    }

    @EventHandler(priority = EventPriority.HIGH)
    public void onBlockPlaceEvent(BlockPlaceEvent e) {

        Player p = e.getPlayer();
        PlayerInventory inventory = p.getInventory();
        ItemStack itemInHand = p.getInventory().getItem(EquipmentSlot.valueOf(String.valueOf(interacthand)));
        int blockinhandamount = itemInHand.getAmount();

        if (plugin.getConfig().getBoolean("AutoItemInAction.BlockPlace")) {
            //ตรวจสอบว่าไอเทมที่ถืออยู่ในเมือเป็นขวานหริอพลั่วหรือเปล่า
            if (itemInHand.getType().name().endsWith("SHOVEL") || itemInHand.getType().name().endsWith("AXE")) {
                int itemdamage = ((Damageable) itemInHand.getItemMeta()).getDamage(); //getdamage ของไอเทมนั้นๆ
                int durabilityleft = itemInHand.getType().getMaxDurability() - itemdamage; // รัข้อมูลความคงทนของไอเทม - ความเสียหายของไอเทม
                if (durabilityleft <= 1) { //หากว่าความคงทนน้อยกว่าหรือมีค่า = 1
                    inventory.setItem(EquipmentSlot.valueOf(String.valueOf(interacthand)), autoItemInAction.findSameBlockType(p, itemInHand));
                }
                return;
            }
            if (blockinhandamount <= 1 && p.getGameMode() != GameMode.CREATIVE) {
                inventory.setItem(EquipmentSlot.valueOf(String.valueOf(interacthand)), autoItemInAction.findSameBlockType(p, itemInHand)); //set ไอเทมที่อยู่ในมือผู้เล่น
            }
        }
    }

    @EventHandler(priority = EventPriority.HIGHEST)
    public void onPlayerInteract(PlayerInteractEvent e) {
        interacthand = e.getHand();
    }
}
