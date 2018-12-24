package de.cloud.master.core.config.configuration;

import com.google.gson.Gson;
import com.google.gson.JsonObject;
import de.cloud.master.core.Wrapper;
import de.cloud.master.manager.WrapperManager;
import de.cloud.master.utils.logger;
import de.cloud.master.utils.loglevel;
import org.json.simple.JSONObject;
import org.simpleyaml.configuration.file.YamlConfiguration;
import org.yaml.snakeyaml.DumperOptions;
import org.yaml.snakeyaml.Yaml;

import java.io.File;
import java.io.FileWriter;
import java.io.StringWriter;
import java.nio.file.Files;
import java.nio.file.OpenOption;
import java.nio.file.Paths;
import java.nio.file.StandardOpenOption;
import java.util.HashMap;
import java.util.Map;

public class WrapperConfig {

    private static WrapperConfig wrapperConfigInstance;

    public static void init() {
        WrapperConfig.wrapperConfigInstance = new WrapperConfig();
    }

    public void createWrapperConfig(Wrapper wrapper) {
        File wrapperFile = new File("./wrappers.yml");
        YamlConfiguration cfg = YamlConfiguration.loadConfiguration(wrapperFile);
        try {
            if(!wrapperFile.exists()) {wrapperFile.createNewFile();}
            /**Map<String, Object> data = new HashMap<>();
            Map<String, Object> data2 = new HashMap<>();

            DumperOptions options = new DumperOptions();
            options.setDefaultFlowStyle(DumperOptions.FlowStyle.BLOCK);
            options.setPrettyFlow(true);

            data.put("Wrapper-Port", wrapper.getPort());
            data.put("Wrapper-Ip", wrapper.getIpAddress());
            data2.put(wrapper.getWrapperName(), data);

            Yaml yaml = new Yaml(options);
            String end = yaml.dump(data2);

            Files.write(Paths.get(wrapperFile.getPath()), end.getBytes(), StandardOpenOption.APPEND);
            data.clear(); **/

            cfg.options().copyDefaults(true);
            cfg.addDefault(wrapper.getWrapperName()+".Wrapper-Port", wrapper.getPort());
            cfg.addDefault(wrapper.getWrapperName()+".Wrapper-Ip", wrapper.getIpAddress());
            cfg.save(wrapperFile);

            new logger(loglevel.INFO, "Der Wrapper '"+wrapper.getWrapperName()+"' wurde erfolgreich hinzugefügt!");
            WrapperManager.getWrapperManager().createWrappeKey();

        } catch (Exception e) {
            new logger(loglevel.ERROR, e.getMessage() + " at " + e.getClass().getName());
        }
    }

    public static WrapperConfig getWrapperConfig() {
        return wrapperConfigInstance;
    }
}
