package de.cloud.master;

import de.cloud.master.utils.logger;
import de.cloud.master.utils.loglevel;

public class Launcher {

    public static void main(String[] args) {
        Manager.init();
        if(Manager.isReady) {
            Manager.getManager().start();
        }
    }
}
