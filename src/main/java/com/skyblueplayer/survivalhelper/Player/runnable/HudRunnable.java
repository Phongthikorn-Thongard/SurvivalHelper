package com.skyblueplayer.survivalhelper.Player.runnable;

import com.skyblueplayer.survivalhelper.Handlers.MessageManager;
import com.skyblueplayer.survivalhelper.Main;
import net.md_5.bungee.api.ChatMessageType;
import net.md_5.bungee.api.chat.TextComponent;
import org.bukkit.entity.Player;
import org.bukkit.scheduler.BukkitRunnable;

public class HudRunnable {
    public boolean ishudon = false;
    MessageManager messageManager = new MessageManager();

    public void HudRun(Player p) {
        new BukkitRunnable() {
            @Override
            public void run() {
                //รับข้อความ Actionbar จาก config.yml
                String ActionbarMessage = Main.getPlugin(Main.class).getConfig().getString("Hud.Text");
                ActionbarMessage = messageManager.formatActionBar(ActionbarMessage, p);
                p.spigot().sendMessage(ChatMessageType.ACTION_BAR, new TextComponent(ActionbarMessage));
                if (!ishudon) { // ถ้า ishudon มีค่าเป็น false
                    this.cancel();
                }
            }
        }.runTaskTimer(Main.getPlugin(Main.class), 0, 1L);
    }

    public void hudRunCancel() {
        ishudon = false;
    }
}
