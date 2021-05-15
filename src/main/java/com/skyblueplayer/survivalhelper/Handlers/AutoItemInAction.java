package com.skyblueplayer.survivalhelper.Handlers;

import com.skyblueplayer.survivalhelper.Main;
import net.md_5.bungee.api.ChatMessageType;
import net.md_5.bungee.api.chat.TextComponent;
import org.bukkit.Material;
import org.bukkit.Sound;
import org.bukkit.entity.Player;
import org.bukkit.inventory.EquipmentSlot;
import org.bukkit.inventory.ItemStack;
import org.bukkit.inventory.PlayerInventory;
import org.bukkit.scheduler.BukkitRunnable;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.Map;

public class AutoItemInAction {
    MessageManager messageManager = new MessageManager();
    String autoItemInActionMessage = "§a[§bAuto item in action.§a]";

    public void BringItemToHand(Player p, ItemStack brokenitem, EquipmentSlot hand) {

        ArrayList<ItemStack> inventoryitem = new ArrayList<>(); //เก็บข้อมูล item ใน inventory ของผู้เล่น
        PlayerInventory inventory = p.getInventory();


        for (int i = 0; i < 35; i++) { // เก็บข้อมููลใน hotbar กับ storage ธรรมดา
            if (i != inventory.getHeldItemSlot()) { //รับข้อมูล หมายเลขช่องที่มือผู้เล่นถืออยู่
                ItemStack item = inventory.getItem(i); //รับข้อมูลไอเทมที่อยู่ในช่องเก็บของด้วยหมายเลขช่องที่กำหนด
                if (item != null) { //ตรวจสอบว่าไอเทมมีอยู่จริงแล้วเอาเข้า inventoryitem เพื่อเก็บข้อมูล
                    inventoryitem.add(item);
                }
            }
        }

        for (ItemStack itemStack : inventoryitem) { //เรียกใช้ข้อมูลไอเทมที่อยู่ใน inventory
            if (itemStack.getType().name().equals(brokenitem.getType().name())) {
                new BukkitRunnable() {
                    @Override
                    public void run() {
                        inventory.setItem(hand, itemStack);
                        p.playSound(p.getLocation(), Sound.BLOCK_NOTE_BLOCK_BELL, 10, 10);
                    }
                }.runTaskLater(Main.getPlugin(Main.class), 1L);
                p.spigot().sendMessage(ChatMessageType.ACTION_BAR, new TextComponent(
                        messageManager.rawColorMessage(autoItemInActionMessage)));
                inventory.removeItem(itemStack); //ทำลายไอเทมที่ตรงกันทิ้ง
                return;
            }
        }
    }

    public ItemStack findSameBlockType(Player p, ItemStack blockitem) {

        HashMap<ItemStack, Integer> inventoryitem = new HashMap<>(); //เก็บข้อมูล item ใน inventory ของผู้เล่น
        PlayerInventory inventory = p.getInventory();

        for (int i = 0; i < 35; i++) {
            //เอาไอเทมทุกอย่างที่อยู่ใน inventory ยกเว้นไอเทมที่อยู่ใน slot ในบล็อคที่ใช้หมดไป
            if (i != inventory.getHeldItemSlot()) { //ให้รันคำสั่งต่อไปหาก i ไม่ใช่ slot เดี่ยวกับบล็อคที่หมดไป
                ItemStack item = inventory.getItem(i); //รับไอเทมเข้ามา
                if (item != null) { // ถ้าไอเทมไม่ใช่ null
                    inventoryitem.put(item, i); // ใส่ไอเทมลงใน hashmap
                }
            }
        }

        for (Map.Entry<ItemStack, Integer> slotanditem : inventoryitem.entrySet()) { // forloop ไอเทมที่อยู่ใน hashmap
            //หากว่า item stack ตรงกับไอเทมบล็อคที่หมดไป
            if (slotanditem.getKey().getType().name().equals(blockitem.getType().name())) {
                inventory.setItem(slotanditem.getValue(), new ItemStack(Material.AIR)); //set ไอเทมส่วนที่จะดึงมาเปลี่ยนด้วย AIR
                p.playSound(p.getLocation(), Sound.BLOCK_NOTE_BLOCK_BELL, 10, 1); // เล่นเสียงเมื่อของถูกสับเปลี่ยน
                p.spigot().sendMessage(ChatMessageType.ACTION_BAR, new TextComponent(
                        messageManager.rawColorMessage(autoItemInActionMessage)));
                return slotanditem.getKey(); // return ItemStack ของไอเทมที่ถูกเงื่อนไขกลับมา
            }
        }
        return null; //หากว่าไม่เข้าพวกเลยให้ส่ง null
    }
}
