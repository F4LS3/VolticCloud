package de.cloud.master.core;

import java.util.UUID;

public class Wrapper {

    private static String ipAddress;
    private static String wrapperName;
    private static int port;

    public Wrapper() {

    }

    public Wrapper(String ipAddress, String wrapperName, int port) {
        this.ipAddress = ipAddress;
        this.wrapperName = wrapperName;
        this.port = port;
    }

    public static void setIpAddress(String ipAddress) {
        Wrapper.ipAddress = ipAddress;
    }

    public static void setWrapperName(String wrapperName) {
        Wrapper.wrapperName = wrapperName;
    }

    public static void setPort(int port) {
        Wrapper.port = port;
    }

    public static String getIpAddress() {
        return ipAddress;
    }

    public static String getWrapperName() {
        return wrapperName;
    }

    public static int getPort() {
        return port;
    }
}
