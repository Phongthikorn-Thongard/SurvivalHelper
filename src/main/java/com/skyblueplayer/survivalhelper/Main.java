package com.skyblueplayer.survivalhelper;

import com.skyblueplayer.survivalhelper.Commands.SvhelperCommand;
import com.skyblueplayer.survivalhelper.Commands.TabCompletetions.SvhelperTabCompletetion;
import com.skyblueplayer.survivalhelper.Events.*;
import com.skyblueplayer.survivalhelper.Events.SpecialTools.EnableMDTSevent;
import com.skyblueplayer.survivalhelper.Events.SpecialTools.EnableTapeMeasureEvent;
import com.skyblueplayer.survivalhelper.Player.data.PlayerMDSdata;
import com.skyblueplayer.survivalhelper.Player.data.PlayerMeasureData;
import com.skyblueplayer.survivalhelper.utils.Debugger;
import org.bukkit.Bukkit;
import org.bukkit.entity.Player;
import org.bukkit.plugin.java.JavaPlugin;

import java.util.HashMap;
import java.util.UUID;

public final class Main extends JavaPlugin {

    public static Main instance;
    public HashMap<UUID, PlayerMeasureData> playermetric = new HashMap<>();
    public HashMap<UUID, PlayerMDSdata> playermdspdata = new HashMap<>();


    private static void setInstance(Main instance) {
        Main.instance = instance;
    }

    @Override
    public void onEnable() {
        // Plugin startup logic
        putHashmap();
        Debugger.sendConsoleMessage("Thank you for using Survival Helper plugin!");
        this.getCommand("svhelper").setExecutor(new SvhelperCommand());
        this.getCommand("svhelper").setTabCompleter(new SvhelperTabCompletetion());
        InstanceEvent();
        saveDefaultConfig();
        setInstance(this);
    }

    @Override
    public void onDisable() {

    }

    public void InstanceEvent() {
        new JoinEvent(this);
        new ItemBreakEvent(this);
        new PlaceBlockEvent(this);
        new TapeMeasureEvent(this);
        new QuitEvent(this);
        new EnableTapeMeasureEvent(this);
        new EnableMDTSevent(this);
    }

    private void putHashmap() {
        for (Player p : Bukkit.getOnlinePlayers()) {
            playermetric.put(p.getUniqueId(), new PlayerMeasureData(p.getUniqueId(), null, null, 1, false));
            playermdspdata.put(p.getUniqueId(), new PlayerMDSdata(false));
        }
    }
}
