package de.cloud.wrapper.setup;

import de.cloud.wrapper.utils.logger;
import de.cloud.wrapper.utils.loglevel;

import java.io.BufferedReader;
import java.io.DataInputStream;
import java.io.InputStreamReader;

public class Setup {

    public static String setupWrapperName() {
        try {
            BufferedReader r = new BufferedReader(new InputStreamReader(new DataInputStream(System.in)));
            new logger(loglevel.INFO, "Bitte gebe den Namen des Wrappers ein (z.B. Wrapper-1)!");
            WrapperConfig.setWrapperName(r.readLine());
            return r.readLine();

        } catch (Exception e) {
            new logger(loglevel.ERROR, "&red" + e.getStackTrace());
        }
        return "Error: Connot set Wrapper name!";
    }
}
