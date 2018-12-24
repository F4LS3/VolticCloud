package de.cloud.master.commands;

import de.cloud.master.Manager;
import de.cloud.master.manager.Command;

public class HelpCommand implements Command {

    public void execute(String[] args) {
        if(args[0].equalsIgnoreCase("help")) {
            Manager.getManager().commands.forEach(command -> {
                if(command.getUsage() != null) {
                    System.out.println(command.getUsage());
                }
            });
        }
    }

    public String getUsage() {
        return null;
    }
}
