package de.cloud.wrapper;

import de.cloud.wrapper.core.Wrapper;
import de.cloud.wrapper.setup.Setup;

public class Launcher implements Wrapper {

    public static void main(String[] args) {
        Manager.init();
        if(Manager.isReady) {
            Manager.getManager().start();
        }
    }

    @Override
    public void setWrapperName(String wrapperName) {
        Setup.enterWrapperSetup();
    }

    @Override
    public void setWrapperIp(String wrapperIp) {

    }

    @Override
    public void setWrapperPort(int wrapperPort) {

    }
}
