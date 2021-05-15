package com.skyblueplayer.survivalhelper.Player.data;

import org.bukkit.block.Block;

import java.util.UUID;

public class PlayerMeasureData {
    private UUID uuid;
    private Block block1;
    private Block block2;
    private int SelectedIndex = 1;
    private boolean isEnableMeasureTape;

    public PlayerMeasureData(UUID uuid, Block block1, Block block2, int selectedIndex, boolean isEnableMeasureTape) {
        this.setUuid(uuid);
        this.setBlock1(block1);
        this.setBlock2(block2);
        this.setSelectedIndex(selectedIndex);
        this.setEnableMeasureTape(isEnableMeasureTape);
    }

    public UUID getUuid() {
        return uuid;
    }

    public void setUuid(UUID uuid) {
        this.uuid = uuid;
    }

    public Block getBlock1() {
        return block1;
    }

    public void setBlock1(Block block1) {
        this.block1 = block1;
    }

    public Block getBlock2() {
        return block2;
    }

    public void setBlock2(Block block2) {
        this.block2 = block2;
    }

    public int getSelectedIndex() {
        return SelectedIndex;
    }

    public void setSelectedIndex(int selectedIndex) {
        SelectedIndex = selectedIndex;
    }

    public boolean isEnableMeasureTape() {
        return isEnableMeasureTape;
    }

    public void setEnableMeasureTape(boolean enableMeasureTape) {
        isEnableMeasureTape = enableMeasureTape;
    }


}
