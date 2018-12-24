package de.cloud.master.commands;

import de.cloud.master.core.setup.Setup;
import de.cloud.master.manager.Command;
import de.cloud.master.core.Wrapper;
import de.cloud.master.core.config.configuration.WrapperConfig;
import de.cloud.master.manager.WrapperManager;
import de.cloud.master.utils.logger;
import de.cloud.master.utils.loglevel;

import java.io.BufferedReader;
import java.io.DataInputStream;
import java.io.InputStreamReader;

public class CreateCommand implements Command {

    @Override
    public void execute(String[] args) {
        try {
            if (args.length == 4) {
                if (args[0].equalsIgnoreCase("create")) {
                    if (args[1].equalsIgnoreCase("wrapper")) {
                        String wrapperName = args[2];
                        new logger(loglevel.INFO, "Bitte gib die IP-Adresse des Wrappers ein (z.B. localhost)");
                        BufferedReader r = new BufferedReader(new InputStreamReader(new DataInputStream(System.in)));
                        Wrapper wrapper = new Wrapper();
                        wrapper.setWrapperName(wrapperName);
                        wrapper.setIpAddress(r.readLine());
                        wrapper.setPort(Integer.valueOf(args[3]));
                        WrapperManager.getWrapperManager().addAvailableWrapper(wrapper);
                        WrapperConfig.getWrapperConfig().createWrapperConfig(wrapper);

                    } else {
                        new logger(loglevel.WARNING, "Bitte verwende: " + getUsage());
                    }
                }
            } else if(args.length == 3) {
                if(args[1].equalsIgnoreCase("servergroup")) {
                    String groupName = args[2].toUpperCase();
                    Setup.enterServerGroupSetup(groupName);
                    new logger(loglevel.INFO, "Erstellungsanfrage für die Servergruppe '"+groupName+"' wurde versendet!");
                }
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    @Override
    public String getUsage() {
        return "Create <WRAPPER/SERVERGROUP> NAME -> Ersttelt einen neuen Wrapper oder eine neue Servergruppe!";
    }
}
