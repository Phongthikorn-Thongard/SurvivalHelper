package com.skyblueplayer.survivalhelper.Events;

import com.skyblueplayer.survivalhelper.Handlers.measure.MeasureHandler;
import com.skyblueplayer.survivalhelper.Main;
import com.skyblueplayer.survivalhelper.Player.data.PlayerMeasureData;
import org.bukkit.Sound;
import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.block.Action;
import org.bukkit.event.player.PlayerInteractEvent;
import org.bukkit.inventory.EquipmentSlot;

public class TapeMeasureEvent implements Listener {

    private final MeasureHandler measureHandler = new MeasureHandler();
    private final Main plugin = Main.getPlugin(Main.class);
    PlayerMeasureData playermetric;


    public TapeMeasureEvent(Main plugin) {
        plugin.getServer().getPluginManager().registerEvents(this, plugin);
    }

    @EventHandler
    public void onClickBlock(PlayerInteractEvent e) {
        Action action = e.getAction();
        Player p = e.getPlayer();
        playermetric = plugin.playermetric.get(p.getUniqueId());

        int Selectedindex = playermetric.getSelectedIndex();
        if (playermetric.isEnableMeasureTape()) {
            if (e.getHand() == EquipmentSlot.HAND) { //ตรวจสอบว่าใช้มือหรือเปล่า (ป้องกันไม่ให้ event run ซ้ำสองรอบ)
                if (action.equals(Action.RIGHT_CLICK_BLOCK)) { //ตรวจสอบว่าผู้คลิกที่บล็อคหรือป่าว
                    if (Selectedindex == 1) { //รับข้อมูล selectedindex ที่อยู่ใน playermeaseuredata
                        playermetric.setBlock1(e.getClickedBlock()); //set ข้อมูลบล็อคที่ผู้เล่นคลิกไปที่ที่เก็บ playerdata ไว้
                        p.playSound(p.getLocation(), Sound.BLOCK_NOTE_BLOCK_BIT, 10, 1); // เล่นเสียงเมื่อของถูกสับเปลี่ยน
                    } else if (Selectedindex == 2) { //หากว่า selectedindex = 2 ให้รันคำสั่ง
                        playermetric.setBlock2(e.getClickedBlock()); //รับข้อมูล selectedindex ที่อยู่ใน playermeaseuredata
                        p.playSound(p.getLocation(), Sound.BLOCK_NOTE_BLOCK_BIT, 10, 2); // เล่นเสียงเมื่อของถูกสับเปลี่ยน
                        int distance = (int) (playermetric.getBlock1().getLocation().distance(playermetric.getBlock2().getLocation()) + 1);
                        p.sendMessage(measureHandler.sendResultString(measureHandler.Measuretype(playermetric.getBlock1(), playermetric.getBlock2()),
                                playermetric.getBlock1(), playermetric.getBlock2(), distance));
                    }
                }
                if (Selectedindex == 1) {
                    playermetric.setSelectedIndex(2);
                } else if (Selectedindex == 2) {
                    playermetric.setSelectedIndex(1);
                }
            }
        }
    }
}
