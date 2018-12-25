package de.cloud.wrapper;

import de.cloud.wrapper.setup.Setup;
import de.cloud.wrapper.utils.logger;
import de.cloud.wrapper.utils.loglevel;

import java.net.Inet4Address;
import java.net.InetAddress;
import java.net.UnknownHostException;

public class Launcher {

    private static String wrapperName;
    private static InetAddress ipAddress;

    public static void main(String[] args) {
        setWrapperName(Setup.setupWrapperName());
        setIpAddress();
        Manager.init();
        if(Manager.isReady) {
            Manager.getManager().start();
        }
    }

    public static void setWrapperName(String wrapperName) {
        Launcher.wrapperName = wrapperName;
    }

    public static void setIpAddress() {
        try {
            Launcher.ipAddress = InetAddress.getLocalHost();
        } catch (UnknownHostException e) {
            new logger(loglevel.ERROR, e.getStackTrace());
        }
    }

    public static String getWrapperName() {
        return wrapperName;
    }

    public static InetAddress getIpAddress() {
        return ipAddress;
    }
}
