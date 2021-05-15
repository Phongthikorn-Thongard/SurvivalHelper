package com.skyblueplayer.survivalhelper.Commands.TabCompletetions;

import org.bukkit.command.Command;
import org.bukkit.command.CommandSender;
import org.bukkit.command.TabCompleter;

import java.util.ArrayList;
import java.util.List;

public class SvhelperTabCompletetion implements TabCompleter {

    List<String> arguments = new ArrayList<>();

    @Override
    public List<String> onTabComplete(CommandSender sender, Command command, String alias, String[] args) {
        if(arguments.isEmpty()) {
            arguments.add("reload"); arguments.add("togglehud");
        }
        List<String> result = new ArrayList<>();
        if (args.length > 0) {
            for(String a : arguments) {
                if (a.toLowerCase().startsWith(args[0].toLowerCase()))
                    result.add(a);
            }
            return result;
        }
        return null;
    }
}
