package de.cloud.wrapper.commands;

import de.cloud.wrapper.Manager;
import de.cloud.wrapper.core.Command;

public class StopCommand implements Command {

    public void execute(String[] args) {
        if(args[0].equalsIgnoreCase("stop") || args[0].equalsIgnoreCase("exit")) {
            Manager.getManager().stop();
        }
    }

    public String getUsage() {
        return "Stop -> Stoppt die Cloud und alle ihre Systeme!";
    }
}
