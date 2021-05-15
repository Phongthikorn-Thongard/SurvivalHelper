package com.skyblueplayer.survivalhelper.utils;

import org.bukkit.Bukkit;
import org.bukkit.ChatColor;


public class Debugger {
    public static void sendConsoleMessage(String message) { //ส่งข้อความไปที่ console
        Bukkit.getConsoleSender().sendMessage(ChatColor.translateAlternateColorCodes('&', message));
    }
}
