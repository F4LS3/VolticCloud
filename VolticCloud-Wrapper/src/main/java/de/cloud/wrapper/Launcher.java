package de.cloud.wrapper;

import de.cloud.wrapper.setup.Setup;

import java.net.Inet4Address;
import java.net.InetAddress;
import java.net.UnknownHostException;

public class Launcher {

    private static String wrapperName;
    private static InetAddress ipAddress;

    public static void main(String[] args) {
        setWrapperName(Setup.setupWrapperName());
        Manager.init();
        if(Manager.isReady) {
            Manager.getManager().start();
        }
    }

    public static void setWrapperName(String wrapperName) {
        Launcher.wrapperName = wrapperName;
    }

    public static void setIpAddress() throws Exception {
        Launcher.ipAddress = InetAddress.getLocalHost();
    }

    public static String getWrapperName() {
        return wrapperName;
    }

    public static InetAddress getIpAddress() {
        return ipAddress;
    }
}
