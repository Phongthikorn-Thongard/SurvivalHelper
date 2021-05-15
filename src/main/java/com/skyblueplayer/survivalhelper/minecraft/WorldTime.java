package com.skyblueplayer.survivalhelper.minecraft;

import org.bukkit.entity.Player;

public class WorldTime {
    public int getHour(Player p) {
        long ticks = p.getWorld().getTime();
        long allhour = (ticks / 5 * 18 / 60 / 60); //ทำให้ชั่วโมงของวันในมายคราฟจาก 20 ชั่วโมง เป็น 30 ชั่วโมง
        return (int) (allhour + 6);
    }

    public int getMinute(Player p) {
        long ticks = p.getWorld().getTime();
        long allhour = (ticks / 5 * 18 / 60 / 60);
        long allminute = (ticks / 5 * 18 / 60);
        return (int) (allminute - (allhour * 60));
    }
}
