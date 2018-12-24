package de.cloud.wrapper.commands;

import de.cloud.wrapper.Manager;
import de.cloud.wrapper.core.Command;

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
