package de.cloud.master.commands;

import de.cloud.master.Manager;
import de.cloud.master.manager.Command;
import de.cloud.master.netty.handler.NetworkStringHandler;

public class StopCommand implements Command {

    public void execute(String[] args) {
        if(args[0].equalsIgnoreCase("stop") || args[0].equalsIgnoreCase("exit")) {
            Manager.getManager().stop();
            NetworkStringHandler.getChannel().writeAndFlush("close");
        }
    }

    public String getUsage() {
        return "Stop -> Stoppt die Cloud und alle ihre Systeme!";
    }
}
