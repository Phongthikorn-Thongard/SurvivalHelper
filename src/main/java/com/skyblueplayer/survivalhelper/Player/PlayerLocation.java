package com.skyblueplayer.survivalhelper.Player;

import org.bukkit.entity.Player;

public class PlayerLocation {
    public static String getCardinalDirection(Player player) {
        double rotation = (player.getLocation().getYaw() + 180) % 360;
        if (rotation < 0) {
            rotation += 360.0;
        } else if (rotation > 360) {
            rotation = 0;
        }
        if (0 <= rotation && rotation < 22.5) {
            return "N";
        }
        if (22.5 <= rotation && rotation < 67.5) {
            return "NE";
        }
        if (67.5 <= rotation && rotation < 112.5) {
            return "E";
        }
        if (112.5 <= rotation && rotation < 157.5) {
            return "SE";
        }
        if (157.5 <= rotation && rotation < 202.5) {
            return "S";
        }
        if (202.5 <= rotation && rotation < 247.5) {
            return "SW";
        }
        if (247.5 <= rotation && rotation < 292.5) {
            return "W";
        }
        if (292.5 <= rotation && rotation < 337.5) {
            return "NW";
        }
        if (337.5 <= rotation && rotation <= 360) {
            return "N";
        }
        return null;
    }


}
