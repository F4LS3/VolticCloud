package de.cloud.master.commands;

import de.cloud.master.manager.Command;
import de.cloud.master.utils.logger;
import de.cloud.master.utils.loglevel;

import java.util.ArrayList;
import java.util.List;

public class ModuleCommand implements Command {

    private List<String> modules = new ArrayList<>();

    private String getLoadedModules() {
        for(String module : modules) {
            return module + ", ";
        }
        return "Es sind keine Module geladen!";
    }

    @Override
    public void execute(String[] args) {
        if(args[0].equalsIgnoreCase("module")) {
            modules.add("Webinterface");
            if (args.length == 1) {
                new logger(loglevel.INFO, getUsage());
            } else if (args.length == 2) {
                if (args[1].equalsIgnoreCase("list")) {
                    new logger(loglevel.BLANK, "Geladene Module: "+getLoadedModules());
                }
            }
        }
    }

    @Override
    public String getUsage() {
        return "Module <load/list> (<MODULE>) -> Läd module wie z.B. das Webinterface";
    }
}
