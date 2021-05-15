package com.skyblueplayer.survivalhelper.Handlers.SpecialTools;

import com.skyblueplayer.survivalhelper.Handlers.SpecialTools.Block.BlockBlackList;
import org.bukkit.Location;
import org.bukkit.Material;
import org.bukkit.Particle;
import org.bukkit.block.Block;
import org.bukkit.block.data.Bisected;
import org.bukkit.block.data.BlockData;
import org.bukkit.block.data.type.Slab;
import org.bukkit.block.data.type.Stairs;
import org.bukkit.entity.Player;

public class LightDetector {
    BlockBlackList blockBlackList = new BlockBlackList();

    public void spawnparticleonblock(Player p, int maxradius) {
        int bx = p.getLocation().getBlockX();
        int bz = p.getLocation().getBlockZ();
        int by = p.getLocation().getBlockY();

        for (int xx = bx - maxradius; xx < bx + maxradius; xx++) {
            for (int zz = bz - maxradius; zz < bz + maxradius; zz++) {
                for (int yy = by - maxradius; yy < by + maxradius; yy++) {
                    Location blocklocation = new Location(p.getWorld(), xx, yy, zz);
                    Block blockgetfromlocation = blocklocation.getBlock();
                    Material blocktype = blockgetfromlocation.getType();
                    String[] blockMaterialWithSplit = blocktype.name().split("_");
                    String blocklastname = blockMaterialWithSplit[blockMaterialWithSplit.length - 1];
                    if (blockBlackList.halfblock.contains(blocklastname)) {
                        BlockData blockData = blockgetfromlocation.getBlockData();
                        if (blocklastname.equals("STAIRS")) {
                            Stairs stairs = (Stairs) blockData;
                            if (stairs.getHalf().equals(Bisected.Half.BOTTOM)) {
                                continue;
                            }
                        } else if (blocklastname.equals("SLAB")) {
                            Slab slab = (Slab) blockData;
                            if (slab.getType().equals(Slab.Type.BOTTOM)) {
                                continue;
                            }
                        }
                    }
                    if (blocktype.isSolid() && !blockBlackList.trasparentblock.contains(blocklastname)) {
                        Block blockPlusOneY = blocklocation.clone().add(0, 1, 0).getBlock();
                        if (blockPlusOneY.getLightFromBlocks() <= 7
                                && blockPlusOneY.getType() == Material.AIR) {
                            p.spawnParticle(Particle.END_ROD, blocklocation.clone().add(0.5, 1.5, 0.5), 1, 0, 0, 0, 0);
                        }
                    }
                }
            }
        }
    }
}
