package de.cloud.wrapper;

import de.cloud.wrapper.core.Command;
import de.cloud.wrapper.core.config.FileManager;
import de.cloud.wrapper.core.config.configuration.MasterConfig;
import de.cloud.wrapper.netty.Client;
import de.cloud.wrapper.utils.Ansi;
import de.cloud.wrapper.utils.logger;
import de.cloud.wrapper.utils.loglevel;

import java.util.ArrayList;
import java.util.List;

public class Manager {

    private static Manager managerInstance;
    public static boolean isReady = false;
    public static boolean isValid = false;

    public List<Command> commands = new ArrayList<Command>();

    public static void init() {
        Manager.managerInstance = new Manager();
        Manager.isReady = true;
    }

    public void addCommand(Command command) {
        commands.add(command);
    }

    public void start() {
        new logger(loglevel.BLANK, Ansi.ANSI_BLUE+"\n" +
                " _______         _____  _     _ ______  _______ __   __ _______ _______ _______ _______\n" +
                " |       |      |     | |     | |     \\ |______   \\_/   |______    |    |______ |  |  |\n" +
                " |_____  |_____ |_____| |_____| |_____/ ______|    |    ______|    |    |______ |  |  |\n" +
                "                                                                                       \n"+Ansi.ANSI_RESET);

        new logger(loglevel.INFO, "Developer: F4LS3\n");
        new logger(loglevel.INFO, "Loading Wrapper...");
        FileManager.init();
        FileManager.getFileManager().start();
        new logger(loglevel.INFO, "Wrapper started successfully! \n\n");
        MasterConfig.getMasterConfig().checkMasterConfig();
        MasterConfig.getMasterConfig().checkWrapperKey();

        Client.init();
        Client.getClient().runClient();
    }

    public void stop() {
        FileManager.getFileManager().stop();
        new logger(loglevel.INFO, "Shutting down Manager...");
        Client.getChannel().close();
        this.managerInstance = null;
        this.isReady = false;
        new logger(loglevel.INFO, "Manager shutted down!");
        new logger(loglevel.INFO, "Bye! Bye!");
        System.exit(0);
    }

    public static Manager getManager() {
        return managerInstance;
    }
}
