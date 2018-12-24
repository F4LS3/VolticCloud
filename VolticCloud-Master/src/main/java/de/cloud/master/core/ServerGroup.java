package de.cloud.master.core;

public class ServerGroup {

    private static String groupName;
    private static int ram;
    private static Wrapper wrapper;
    private static int maxPlayers;
    private static GroupMode groupMode;

    public ServerGroup() {

    }

    public ServerGroup(String groupName, int ram, Wrapper wrapper, int maxPlayers, GroupMode groupMode) {
        this.groupName = groupName;
        this.ram = ram;
        this.wrapper = wrapper;
        this.maxPlayers = maxPlayers;
        this.groupMode = groupMode;
    }

    public static void setGroupName(String groupName) {
        ServerGroup.groupName = groupName;
    }

    public static void setRam(int ram) {
        ServerGroup.ram = ram;
    }

    public static void setWrapper(Wrapper wrapper) {
        ServerGroup.wrapper = wrapper;
    }

    public static void setMaxPlayers(int maxPlayers) {
        ServerGroup.maxPlayers = maxPlayers;
    }

    public static void setGroupMode(GroupMode groupMode) {
        ServerGroup.groupMode = groupMode;
    }

    public static String getGroupName() {
        return groupName;
    }

    public static int getRam() {
        return ram;
    }

    public static Wrapper getWrapper() {
        return wrapper;
    }

    public static int getMaxPlayers() {
        return maxPlayers;
    }

    public static GroupMode getGroupMode() {
        return groupMode;
    }
}
