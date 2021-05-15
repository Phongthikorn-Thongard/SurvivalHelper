package com.skyblueplayer.survivalhelper.Handlers.SpecialTools.Block;

import java.util.ArrayList;
import java.util.Arrays;

public class BlockBlackList {
    public ArrayList<String> trasparentblock = new ArrayList<>(Arrays.asList(
            "LEAVES",
            "GLASS",
            "PANE",
            "GATE",
            "FENCE",
            "PATH",
            "WALL",
            "TRAPDOOR"
    ));
    public ArrayList<String> halfblock = new ArrayList<>(Arrays.asList(
            "SLAB",
            "STAIRS"
    ));
}
