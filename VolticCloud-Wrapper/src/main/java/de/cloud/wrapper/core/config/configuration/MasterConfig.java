package de.cloud.wrapper.core.config.configuration;

import de.cloud.wrapper.Manager;
import de.cloud.wrapper.utils.logger;
import de.cloud.wrapper.utils.loglevel;
import org.simpleyaml.configuration.file.YamlConfiguration;
import org.simpleyaml.configuration.file.YamlFile;
import org.yaml.snakeyaml.DumperOptions;
import org.yaml.snakeyaml.Yaml;

import java.io.*;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.nio.file.StandardOpenOption;
import java.util.HashMap;
import java.util.Map;
import java.util.Scanner;

public class MasterConfig {

    private static MasterConfig masterConfigInstance;

    public static void init() {
        MasterConfig.masterConfigInstance = new MasterConfig();
    }

    public void checkMasterConfig() {
        File masterFile = new File("./master.yml");
        File file = new File("./Wrapper/Configs/Wrapper.yml");
        YamlConfiguration cfg = YamlConfiguration.loadConfiguration(masterFile);
        YamlConfiguration cfg2 = YamlConfiguration.loadConfiguration(file);
        if((!masterFile.exists() && !file.exists()) || !masterFile.exists() || !file.exists()) {
            try {
                masterFile.createNewFile();
                cfg.options().copyDefaults(true);
                cfg2.options().copyDefaults(true);

                new logger(loglevel.INFO, "Beginne mit Wrapper konfiguration... \n");
                BufferedReader reader = new BufferedReader(new InputStreamReader(new DataInputStream(System.in)));

                new logger(loglevel.INFO, "Bitte gebe den Namen des Wrappers ein (z.B. Wrapper-1)!");
                cfg2.addDefault("Wrapper.Wrapper-Name", reader.readLine());
                cfg2.save(file);

                new logger(loglevel.INFO, "Bitte gebe die Ip-Adresse des Masters ein (z.B. localhost)!");
                cfg.addDefault("Master-Ip", String.valueOf(reader.readLine()));

                new logger(loglevel.INFO, "Bitte gebe den Port des Masters ein (default: 2000)!");
                cfg.addDefault("Master-Port", Integer.valueOf(reader.readLine()));

                cfg.save(masterFile);
                new logger(loglevel.INFO, "Wrapper wurde erfolgreich konfiguriert!");

            } catch (Exception e) {
                e.printStackTrace();
            }
        }
    }

    public String getWrapperName() {
        File file = new File("./Wrapper/Configs/Wrapper.yml");
        YamlConfiguration cfg = YamlConfiguration.loadConfiguration(file);

        return cfg.getString("Wrapper.Wrapper-Name");
    }

    public int getMasterPort() {
        File file = new File("./master.yml");
        YamlConfiguration cfg = YamlConfiguration.loadConfiguration(file);

        return cfg.getInt("Master-Port");
    }

    public String getMasterIp() {
        File file = new File("./master.yml");
        YamlConfiguration cfg = YamlConfiguration.loadConfiguration(file);

        return cfg.getString("Master-Ip");
    }

    public String getWrapperKey() {
        File file = new File("./WRAPPERKEY.CLOUD");
        if(file.exists()) {
            try {
                Scanner s = new Scanner(file);
                return s.nextLine();
            } catch (Exception e) {e.printStackTrace(); }
        }
        return null;
    }

    public void checkWrapperKey() {
        File keyFile = new File("./WRAPPERKEY.CLOUD");
        if(!keyFile.exists()) {
            new logger(loglevel.ERROR, "Der Wrapperkey wurde nicht gefunden! Bitte erstelle im Master einen neuen Wrapper und kopiere den Wrapperkey in das Wrapper Verzeichnis!");
            new logger(loglevel.INFO, "Wenn du den Wrapperkey eingefügt hast, dann starte den Wrapper bitte neu um alle änderungen zu übernehmen!");
            Manager.isValid = false;
        } else {
            Manager.isValid = true;
        }
    }

    public static MasterConfig getMasterConfig() {
        return masterConfigInstance;
    }
}
