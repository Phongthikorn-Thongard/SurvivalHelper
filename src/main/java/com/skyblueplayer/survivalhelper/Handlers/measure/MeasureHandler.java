package com.skyblueplayer.survivalhelper.Handlers.measure;

import org.bukkit.ChatColor;
import org.bukkit.block.Block;

import java.util.HashMap;
import java.util.Map;

public class MeasureHandler {
    public String sendResultString(String messagetype, Block block1, Block block2, int distance) {

        Map<String, Integer> resultblockaxis = getResultAxisOfTwoblock(block1, block2);
        int resultX = resultblockaxis.get("resultX") + 1;
        int resultY = resultblockaxis.get("resultY") + 1;
        int resultZ = resultblockaxis.get("resultZ") + 1;
        if (messagetype.equals("OneAxis")) {
            return ChatColor.GREEN + "Length between two block: " + ChatColor.AQUA + distance;
        } else if (messagetype.equals("MultipleAxis")) {
            return ChatColor.GREEN + "Length between two blocks: " + ChatColor.AQUA + "[" + "X: " + resultX + " Y: " + resultY + " Z: " + resultZ + "]";
        }
        return ChatColor.RED + "ERROR SOMETHING WENT WRONG!";
    }

    public String Measuretype(Block block1, Block block2) {
        Map<String, Integer> resultblockaix = getResultAxisOfTwoblock(block1, block2);

        int resultX = resultblockaix.get("resultX");
        int resultY = resultblockaix.get("resultY");
        int resultZ = resultblockaix.get("resultZ");

        if (resultX != 0 && resultY == 0 && resultZ == 0) {
            return "OneAxis";
        } else if (resultX == 0 && resultY != 0 && resultZ == 0) {
            return "OneAxis";
        } else if (resultX == 0 && resultY == 0 && resultZ != 0) {
            return "OneAxis";
        } else if (resultX == 0 && resultY == 0 && resultZ == 0) {
            return "OneAxis";
        }
        return "MultipleAxis";
    }

    private Map<String, Integer> getResultAxisOfTwoblock(Block block1, Block block2) {
        int b1x = block1.getLocation().getBlockX();
        int b1y = block1.getLocation().getBlockY();
        int b1z = block1.getLocation().getBlockZ();
        int b2x = block2.getLocation().getBlockX();
        int b2y = block2.getLocation().getBlockY();
        int b2z = block2.getLocation().getBlockZ();

        int resultX = Math.abs(b1x - b2x);
        int resultY = Math.abs(b1y - b2y);
        int resultZ = Math.abs(b1z - b2z);

        Map<String, Integer> resultaxisoftwoblock = new HashMap<>();
        resultaxisoftwoblock.put("resultX", resultX);
        resultaxisoftwoblock.put("resultY", resultY);
        resultaxisoftwoblock.put("resultZ", resultZ);
        return resultaxisoftwoblock;
    }
}
