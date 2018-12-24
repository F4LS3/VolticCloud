package de.cloud.master.manager;

import de.cloud.master.core.Wrapper;
import de.cloud.master.utils.logger;
import de.cloud.master.utils.loglevel;
import org.simpleyaml.configuration.file.YamlConfiguration;

import java.io.*;
import java.util.*;

public class WrapperManager {

    private static WrapperManager wrapperManagerInstance;
    public List<Wrapper> availableWrappers = new ArrayList<>();

    public static void init() {
        WrapperManager.wrapperManagerInstance = new WrapperManager();
    }

    public void createWrappeKey() {
        String key = UUID.randomUUID().toString() + UUID.randomUUID().toString() + UUID.randomUUID().toString() + UUID.randomUUID().toString();
        try {
            File keyFile = new File("./WRAPPERKEY.CLOUD");
            File keyFile2 = new File("./Master/Wrapper/Key/WRAPPERKEY.CLOUD");
            if(!keyFile.exists() && !keyFile2.exists()) {

                PrintWriter writer = new PrintWriter("./WRAPPERKEY.CLOUD", "UTF-8");
                writer.println(key);
                writer.close();

                PrintWriter writer2 = new PrintWriter("./Master/Wrapper/Key/WRAPPERKEY.CLOUD", "UTF-8");
                writer2.println(key);
                writer2.close();
            } else if(keyFile.exists() && !keyFile2.exists()) {
                PrintWriter writer2 = new PrintWriter("./Master/Wrapper/Key/WRAPPERKEY.CLOUD", "UTF-8");
                writer2.println(key);
                writer2.close();
            }
            new logger(loglevel.INFO, "Bitte kopiere den 'WRAPPERKEY.CLOUD' in das Wrapper Verzeichnis!");
        } catch (Exception e) {
        }
    }

    public String getWrapperKey() {
        File file = new File("./Master/Wrapper/Key/WRAPPERKEY.CLOUD");
        try {
            Scanner scanner = new Scanner(file);
            return scanner.nextLine();

        } catch (Exception e) {
            e.printStackTrace();
        }
        return "";
    }

    public String getAvailableWrappers() {
        for(Wrapper wrapper : availableWrappers) {
            return wrapper.getWrapperName()+", ";
        }
        return "Es sind keine Wrapper vorhanden!";
    }

    public Wrapper getWrapper(String wrapperName) {
        File file = new File("./wrappers.yml");
        YamlConfiguration cfg = YamlConfiguration.loadConfiguration(file);
        if(file.exists()) {
            cfg.options().copyDefaults(true);
            if(cfg.isSet(wrapperName+".Wrapper-Port") && cfg.isSet(wrapperName+".Wrapper-Ip")) {
                int port = cfg.getInt(wrapperName+".Wrapper-Port");
                String wrapperIp = cfg.getString(wrapperName+".Wrapper-Ip");
                return new Wrapper(wrapperIp, wrapperName, port);
            } else {
                new logger(loglevel.ERROR, "Der Wrapper mit dem Namen '"+wrapperName+"' existiert nicht!");
                return null;
            }

        } else {
            new logger(loglevel.ERROR, "Es existieren keine Wrapper!");
        }
        new logger(loglevel.ERROR, "Es existieren keine Wrapper!");
        return null;
    }

    public void addAvailableWrapper(Wrapper wrapper) {
        availableWrappers.add(wrapper);
    }

    public static WrapperManager getWrapperManager() {
        return wrapperManagerInstance;
    }
}
