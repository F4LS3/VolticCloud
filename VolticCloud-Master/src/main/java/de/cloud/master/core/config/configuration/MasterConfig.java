package de.cloud.master.core.config.configuration;

import de.cloud.master.utils.logger;
import de.cloud.master.utils.loglevel;
import org.simpleyaml.configuration.file.YamlConfiguration;
import org.simpleyaml.configuration.file.YamlFile;
import org.yaml.snakeyaml.DumperOptions;
import org.yaml.snakeyaml.Yaml;

import java.io.BufferedReader;
import java.io.DataInputStream;
import java.io.File;
import java.io.InputStreamReader;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.nio.file.StandardOpenOption;
import java.util.HashMap;
import java.util.Map;

public class MasterConfig {

    private static MasterConfig masterConfigInstance;

    public static void init() {
        MasterConfig.masterConfigInstance = new MasterConfig();
    }

    public void createDefaultMasterConfig() {
        File masterFile = new File("./config.yml");
        YamlConfiguration cfg = YamlConfiguration.loadConfiguration(masterFile);
        try {
            if(!masterFile.exists()) {
                masterFile.createNewFile();
                cfg.options().copyDefaults(true);

                new logger(loglevel.INFO, "Beginne mit Master konfiguration... \n");
                BufferedReader reader = new BufferedReader(new InputStreamReader(new DataInputStream(System.in)));

                try {
                    new logger(loglevel.INFO, "Bitte gib den Port des Masters ein (default: 2000)!");
                    cfg.addDefault("Connection.Master-Port", Integer.valueOf(reader.readLine()));
                    cfg.save(masterFile);

                } catch (NumberFormatException e) {
                    new logger(loglevel.ERROR, "Der Master-Port muss eine Zahl sein!");
                }
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    public int getPort() {
        File file = new File("./config.yml");
        YamlConfiguration cfg = YamlConfiguration.loadConfiguration(file);

        return cfg.getInt("Connection.Master-Port");
    }

    public static MasterConfig getMasterConfig() {
        return masterConfigInstance;
    }
}
