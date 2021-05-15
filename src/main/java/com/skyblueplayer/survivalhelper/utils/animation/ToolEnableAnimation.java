package com.skyblueplayer.survivalhelper.utils.animation;

import org.bukkit.ChatColor;
import org.bukkit.Sound;
import org.bukkit.entity.Player;

public class ToolEnableAnimation {
    public void EnablingState(Player p, double state, String ToolName) {
        if (state < 6.00 && state > 5.00) {
            p.sendTitle(" ", ChatColor.GOLD + "Enabling " + ToolName + " " + ChatColor.GREEN + " [" + ChatColor.AQUA +
                    "█" + ChatColor.GRAY + "█████" + ChatColor.GREEN + "]", 0, 10, 0);

        } else if (state < 5.00 && state > 4.00) {
            p.sendTitle(" ", ChatColor.GOLD + "Enabling " + ToolName + " " + ChatColor.GREEN + " [" + ChatColor.AQUA +
                    "██" + ChatColor.GRAY + "████" + ChatColor.GREEN + "]", 0, 10, 0);

        } else if (state < 4.00 && state > 3.00) {
            p.sendTitle(" ", ChatColor.GOLD + "Enabling " + ToolName + " " + ChatColor.GREEN + " [" + ChatColor.AQUA +
                    "███" + ChatColor.GRAY + "███" + ChatColor.GREEN + "]", 0, 10, 0);

        } else if (state < 3 && state > 2.00) {
            p.sendTitle(" ", ChatColor.GOLD + "Enabling " + ToolName + " " + ChatColor.GREEN + " [" + ChatColor.AQUA +
                    "████" + ChatColor.GRAY + "██" + ChatColor.GREEN + "]", 0, 10, 0);


        } else if (state < 2 && state > 1.00) {
            p.sendTitle(" ", ChatColor.GOLD + "Enabling " + ToolName + " " + ChatColor.GREEN + " [" + ChatColor.AQUA +
                    "█████" + ChatColor.GRAY + "█" + ChatColor.GREEN + "]", 0, 10, 0);

        } else if (state < 1 && state > 0.00) {
            p.sendTitle(" ", ChatColor.GOLD + "Enabling " + ToolName + " " + ChatColor.GREEN + " [" + ChatColor.AQUA +
                    "██████" + ChatColor.GREEN + "]", 0, 10, 0);

        }
        playerdingsound(p, state);
    }

    public void DisablingState(Player p, double state, String ToolName) {
        if (state < 6.00 && state > 5.00) {
            p.sendTitle(" ", ChatColor.GOLD + "Disabling " + ToolName + " " + ChatColor.GOLD + " [" + ChatColor.RED +
                    "█" + ChatColor.GRAY + "█████" + ChatColor.GOLD + "]", 0, 10, 0);

        } else if (state < 5.00 && state > 4.00) {
            p.sendTitle(" ", ChatColor.GOLD + "Disabling " + ToolName + " " + ChatColor.GOLD + " [" + ChatColor.RED +
                    "██" + ChatColor.GRAY + "████" + ChatColor.GOLD + "]", 0, 10, 0);

        } else if (state < 4.00 && state > 3.00) {
            p.sendTitle(" ", ChatColor.GOLD + "Disabling " + ToolName + " " + ChatColor.GOLD + " [" + ChatColor.RED +
                    "███" + ChatColor.GRAY + "███" + ChatColor.GOLD + "]", 0, 10, 0);

        } else if (state < 3 && state > 2.00) {
            p.sendTitle(" ", ChatColor.GOLD + "Disabling " + ToolName + " " + ChatColor.GOLD + " [" + ChatColor.RED +
                    "████" + ChatColor.GRAY + "██" + ChatColor.GOLD + "]", 0, 10, 0);

        } else if (state < 2 && state > 1.00) {
            p.sendTitle(" ", ChatColor.GOLD + "Disabling " + ToolName + " " + ChatColor.GOLD + " [" + ChatColor.RED +
                    "█████" + ChatColor.GRAY + "█" + ChatColor.GOLD + "]", 0, 10, 0);

        } else if (state < 1 && state > 0.00) {
            p.sendTitle(" ", ChatColor.GOLD + "Disabling " + ToolName + " " + ChatColor.GOLD + " [" + ChatColor.RED +
                    "██████" + ChatColor.GOLD + "]", 0, 10, 0);
        }
        playerdingsound(p, state);
    }

    public void MeasureState(Player p, String message) {
        p.sendTitle(" ", message, 0, 60, 2);
        p.playSound(p.getLocation(), Sound.BLOCK_NOTE_BLOCK_BELL, 10, 0);
    }

    public void playerdingsound(Player p, double state) {
        if (state == 6) {
            p.playSound(p.getLocation(), Sound.BLOCK_NOTE_BLOCK_BASS, 10, 1);
        } else if (state == 5) {
            p.playSound(p.getLocation(), Sound.BLOCK_NOTE_BLOCK_BASS, 10, 1.1f);
        } else if (state == 4) {
            p.playSound(p.getLocation(), Sound.BLOCK_NOTE_BLOCK_BASS, 10, 1.2f);
        } else if (state == 3) {
            p.playSound(p.getLocation(), Sound.BLOCK_NOTE_BLOCK_BASS, 10, 1.3f);
        } else if (state == 2) {
            p.playSound(p.getLocation(), Sound.BLOCK_NOTE_BLOCK_BASS, 10, 1.4f);
        } else if (state == 1) {
            p.playSound(p.getLocation(), Sound.BLOCK_NOTE_BLOCK_BASS, 10, 1.5f);
        }
    }
}
