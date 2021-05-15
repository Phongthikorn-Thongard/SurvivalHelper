package com.skyblueplayer.survivalhelper.Commands;

import com.skyblueplayer.survivalhelper.Main;
import com.skyblueplayer.survivalhelper.Player.data.PlayerMDSdata;
import com.skyblueplayer.survivalhelper.Player.data.PlayerMeasureData;
import com.skyblueplayer.survivalhelper.Player.runnable.HudRunnable;
import org.bukkit.ChatColor;
import org.bukkit.command.Command;
import org.bukkit.command.CommandExecutor;
import org.bukkit.command.CommandSender;
import org.bukkit.entity.Player;

public class SvhelperCommand implements CommandExecutor {
    public HudRunnable hudRunnable = new HudRunnable();


    public boolean onCommand(CommandSender sender, Command command, String label, String[] args) {
        if (sender instanceof Player) {
            Player p = (Player) sender;

            if (args.length > 0) {
                if (args[0].equalsIgnoreCase("reload")) { //เมื่อคำสั้งคือ reload
                    hudRunnable.ishudon = false;
                    Main.getPlugin(Main.class).playermetric.put(p.getUniqueId(), new PlayerMeasureData(p.getUniqueId(), null, null, 1, false));
                    Main.getPlugin(Main.class).playermdspdata.put(p.getUniqueId(), new PlayerMDSdata(false));
                    p.sendMessage(ChatColor.GOLD + "[Survival Helper] " + ChatColor.GREEN + "Reload!");
                } else if (args[0].equalsIgnoreCase("togglehud")) { //เมื่แคำสั่งคือ togglegud
                    if (hudRunnable.ishudon) { // ปิด HUD
                        hudRunnable.hudRunCancel();
                    } else { //เปิด HUD
                        hudRunnable.ishudon = true;
                        hudRunnable.HudRun(p);
                    }
                } else {
                    p.sendMessage(ChatColor.RED + "This command doesn't exist.");
                }
            } else {
                p.sendMessage(ChatColor.RED + "[Survival Helper] Not enough arguments. : /svhelper [reload/togglehud]");
            }
        }
        return false;
    }
}
    /*public void HudRun(Player p) {
        new BukkitRunnable() {
            @Override
            public void run() {
                //รับข้อความ Actionbar จาก config.yml
                String ActionbarMessage = Main.getPlugin(Main.class).getConfig().getString("Hud.Text");
                ActionbarMessage = messageManager.formateActionBar(ActionbarMessage,p);
                p.spigot().sendMessage(ChatMessageType.ACTION_BAR, new TextComponent(ActionbarMessage));
                if (!ishudon) { // ถ้า ishudon มีค่าเป็น false
                    this.cancel();
                    p.spigot().sendMessage(ChatMessageType.ACTION_BAR, new TextComponent("")); //ลบ hud ออกไปทันที
                }
            }
        }.runTaskTimer(Main.getPlugin(Main.class),0,1L);
    }*/
