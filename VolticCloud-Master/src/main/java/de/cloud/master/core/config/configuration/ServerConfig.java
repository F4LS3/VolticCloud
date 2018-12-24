package de.cloud.master.core.config.configuration;

import com.google.gson.Gson;
import de.cloud.master.core.ServerGroup;
import de.cloud.master.utils.logger;
import de.cloud.master.utils.loglevel;

import java.io.File;

public class ServerConfig {

    private static ServerConfig serverConfigInstance;

    public static void init() {
        ServerConfig.serverConfigInstance = new ServerConfig();
    }

    public void createDefaultServerConfigs() {
        try {
            new File("./Master/Wrapper/Key").mkdirs();
            new File("./Master/Sign").mkdirs();
            new File("./Master/Proxy").mkdirs();
            new File("./Master/Database").mkdirs();
            new File("./Master/Modules").mkdirs();

            File serverCFG = new File("./services.yml");

            if(!serverCFG.exists()){serverCFG.createNewFile();}


        } catch (Exception e) {
            new logger(loglevel.ERROR, e.getMessage() + " at " + e.getClass().getName());
        }
    }

    public void createServerConfigForNewServerGroup(ServerGroup serverGroup) {
        try {
            new File("./Cloud/Groups/").mkdirs();
            File groupFile = new File("./Cloud/Groups/"+serverGroup.getGroupName()+".json");
            if(!groupFile.exists()){groupFile.createNewFile();}

        } catch(Exception e) {
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
