package de.cloud.wrapper.core.config.configuration;

import de.cloud.wrapper.utils.logger;
import de.cloud.wrapper.utils.loglevel;
import org.simpleyaml.configuration.file.YamlConfiguration;

import java.io.BufferedReader;
import java.io.DataInputStream;
import java.io.File;
import java.io.InputStreamReader;

public class WrapperConfig {

    public static void setWrapperName(String wrapperName) {
        try {
            File file = new File("./Wrapper/Config/Wrapper.yml");
            YamlConfiguration cfg = YamlConfiguration.loadConfiguration(file);
            if (!file.exists()) {
                cfg.options().copyDefaults(true);
                BufferedReader r = new BufferedReader(new InputStreamReader(new DataInputStream(System.in)));
                new logger(loglevel.INFO, "Bitte gebe den Namen des Wrappers ein (z.B. Wrapper-1)!");
                cfg.addDefault("Wrapper.Wrapper-Name", r.readLine());
                cfg.save(file);
                new logger(loglevel.INFO, "Der ");
            }
        } catch (Exception e) {
            new logger(loglevel.ERROR, e.getStackTrace());
        }
    }
}
