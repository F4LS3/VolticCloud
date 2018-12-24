package de.cloud.wrapper.core.config;

import de.cloud.wrapper.core.config.configuration.MasterConfig;
import de.cloud.wrapper.core.config.configuration.ServerConfig;
import de.cloud.wrapper.utils.logger;
import de.cloud.wrapper.utils.loglevel;

public class FileManager {

    private static FileManager fileManagerInstance;

    private static boolean isReady = false;

    public static void init() {
        if(!isReady) {
            new logger(loglevel.INFO, "FileManager loading..");
            FileManager.fileManagerInstance = new FileManager();
            FileManager.isReady = true;
            new logger(loglevel.INFO, "FileManager loaded successfully!");
        } else {
            new logger(loglevel.WARNING, "FileManager is already initialized!");
        }
    }

    public void start() {
        ServerConfig.init();
        ServerConfig.getServerConfig().createDefaultServerConfigs();

        MasterConfig.init();
    }

    public void stop() {
        new logger(loglevel.INFO, "Shutting down FileManager...");
        ServerConfig.getServerConfig().stop();
        this.fileManagerInstance = null;
        this.isReady = false;
        new logger(loglevel.INFO, "FileManager shutted down!");
    }

    public static FileManager getFileManager() {
        return fileManagerInstance;
    }
}
