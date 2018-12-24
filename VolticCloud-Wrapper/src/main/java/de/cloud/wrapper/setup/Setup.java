package de.cloud.wrapper.setup;

import de.cloud.wrapper.utils.logger;
import de.cloud.wrapper.utils.loglevel;

public class Setup {

    public static void enterWrapperSetup() {
        try {


        } catch (Exception e) {
            new logger(loglevel.ERROR, "&red" + e.getStackTrace());
        }
    }
}
