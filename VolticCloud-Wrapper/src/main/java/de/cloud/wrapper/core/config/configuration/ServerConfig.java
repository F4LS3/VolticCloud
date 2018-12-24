package de.cloud.wrapper.core.config.configuration;

import de.cloud.wrapper.utils.logger;
import de.cloud.wrapper.utils.loglevel;

import java.io.File;

public class ServerConfig {

    private static ServerConfig serverConfigInstance;

    public static void init() {
        ServerConfig.serverConfigInstance = new ServerConfig();
    }

    public void createDefaultServerConfigs() {
        try {
            new File("./Wrapper/Config").mkdirs();
            new File("./Wrapper/Sign").mkdirs();
            new File("./Wrapper/Proxy").mkdirs();
            new File("./Wrapper/Database").mkdirs();

            File serverCFG = new File("./Cloud/");

            if(!serverCFG.exists()){serverCFG.createNewFile();}


        } catch (Exception e) {
            new logger(loglevel.ERROR, e.getMessage() + " at " + e.getClass().getName());
        }
    }

    public void stop() {
        this.serverConfigInstance = null;
    }

    public static ServerConfig getServerConfig() {
        return serverConfigInstance;
    }
}
