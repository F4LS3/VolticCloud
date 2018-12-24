package de.cloud.master;

import de.cloud.master.core.config.configuration.MasterConfig;
import de.cloud.master.manager.Command;
import de.cloud.master.commands.CreateCommand;
import de.cloud.master.commands.HelpCommand;
import de.cloud.master.commands.StopCommand;
import de.cloud.master.core.config.FileManager;
import de.cloud.master.manager.GroupManager;
import de.cloud.master.manager.WrapperManager;
import de.cloud.master.netty.Server;
import de.cloud.master.netty.handler.NetworkStringHandler;
import de.cloud.master.utils.logger;
import de.cloud.master.utils.loglevel;

import java.io.BufferedReader;
import java.io.DataInputStream;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.List;

public class Manager {

    private static Manager managerInstance;
    public static boolean isReady = false;
    public static boolean isAuthenticated = false;

    public static List<Command> commands = new ArrayList<Command>();

    public static void init() {
        Manager.managerInstance = new Manager();
        Manager.isReady = true;
    }

    public void registerCommands() {
        addCommand(new StopCommand());
        addCommand(new HelpCommand());
        addCommand(new CreateCommand());

        try {
            BufferedReader reader = new BufferedReader(new InputStreamReader(new DataInputStream(System.in)));
            String line;
            while((line = reader.readLine()) != null) {
                if(line.length() != 0) {
                    for(Command command : commands) {
                        command.execute(line.split(" "));
                    }
                }
            }

        } catch (Exception e) {
            new logger(loglevel.ERROR, e.getMessage() + " at " + e.getClass().getName());
        }
    }

    public void addCommand(Command command) {
        commands.add(command);
    }

    public void start() {
        new logger(loglevel.BLANK, "\n" +
                " _______         _____  _     _ ______  _______ __   __ _______ _______ _______ _______\n" +
                " |       |      |     | |     | |     \\ |______   \\_/   |______    |    |______ |  |  |\n" +
                " |_____  |_____ |_____| |_____| |_____/ ______|    |    ______|    |    |______ |  |  |\n" +
                "                                                                                       \n");

        new logger(loglevel.INFO, "Developer: F4LS3 \n");
        new logger(loglevel.INFO, "Loading Master...");
        FileManager.init();
        FileManager.getFileManager().start();
        WrapperManager.init();
        GroupManager.init();
        NetworkStringHandler.init();
        new logger(loglevel.INFO, "&greenMaster started successfully! \n\n");

        Server.init();
        Server.getServer().runServer(MasterConfig.getMasterConfig().getPort());
    }

    public void stop() {
        try {
            FileManager.getFileManager().stop();
            if(NetworkStringHandler.isConnected) { NetworkStringHandler.getChannel().writeAndFlush("close"); NetworkStringHandler.getChannel().close(); }
            new logger(loglevel.INFO, "Shutting down Manager...");
            this.managerInstance = null;
            this.commands.clear();
            this.isReady = false;
            NetworkStringHandler.isConnected = false;
            new logger(loglevel.INFO, "Manager shutted down!");
            new logger(loglevel.INFO, "Bye! Bye!");
            System.exit(0);
        } catch (Exception e) { e.printStackTrace(); }
    }

    public static Manager getManager() {
        return managerInstance;
    }
}
