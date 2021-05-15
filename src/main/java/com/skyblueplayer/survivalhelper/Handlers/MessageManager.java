package com.skyblueplayer.survivalhelper.Handlers;

import com.skyblueplayer.survivalhelper.Main;
import com.skyblueplayer.survivalhelper.Player.PlayerLocation;
import com.skyblueplayer.survivalhelper.minecraft.WorldTime;
import org.apache.commons.lang.StringUtils;
import org.bukkit.ChatColor;
import org.bukkit.entity.Player;

public class MessageManager {
    private final WorldTime worldtime = new WorldTime();
    private Main plugin;

    public String rawColorMessage(String message) {
        if (message == null) return "";
        return ChatColor.translateAlternateColorCodes('&', message);
    }

    /**
     * @param message ข้อความที่จะนำมา replace
     * @param player  เก็บข้อมูลผู้เล่นไว้เพื่อนำข้อมมูลนั้นไปแทนที่
     * @return String ที่ผ่านการ replaceแล้ว
     */
    public String formatPlaceholders(String message, Player player) {
        String returnString = message;
        returnString = StringUtils.replace(returnString, "%PLAYERLOCX%", Integer.toString((int) player.getLocation().getX()));
        returnString = StringUtils.replace(returnString, "%PLAYERLOCY%", Integer.toString((int) player.getLocation().getY()));
        returnString = StringUtils.replace(returnString, "%PLAYERLOCZ%", Integer.toString((int) player.getLocation().getZ()));
        returnString = StringUtils.replace(returnString, "%LOOKDIRECTION%", PlayerLocation.getCardinalDirection(player));
        returnString = StringUtils.replace(returnString, "%HH%", addZero(worldtime.getHour(player) % 24));
        returnString = StringUtils.replace(returnString, "%MM%", addZero(worldtime.getMinute(player) % 60));
        return returnString;
    }

    public String formatActionBar(String message, Player p) {
        plugin = Main.getPlugin(Main.class);
        String returnString = formatPlaceholders(message, p);
        returnString = formatPlaceholders(returnString, p);
        if (worldtime.getHour(p) >= 6 && worldtime.getHour(p) <= 18) {
            returnString = StringUtils.replace(returnString, "$TIMECOLOR$"
                    , plugin.getConfig().getString("Hud.TimeColor.DayTimeColor"));
            returnString = StringUtils.replace(returnString, "$TIMESYMBOL$", "☀");
        } else {
            returnString = StringUtils.replace(returnString, "$TIMECOLOR$"
                    , plugin.getConfig().getString("Hud.TimeColor.NightTimeColor"));
            returnString = StringUtils.replace(returnString, "$TIMESYMBOL$", "☾");
        }
        return rawColorMessage(formatPlaceholders(returnString, p));
    }

    public String addZero(int number) { //เพิ่มเลข 0 ไว้ข้างหน้าเมื่อมีเลขหลักเดียว
        if (number < 10) {
            return "0" + number;
        }
        return "" + number;
    }
}
