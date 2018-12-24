package de.cloud.master.manager;

import de.cloud.master.core.ServerGroup;

import java.util.ArrayList;
import java.util.List;

public class GroupManager {

    private static GroupManager groupManagerInstance;

    public List<ServerGroup> availableServerGroups = new ArrayList<>();

    public static void init() {
        GroupManager.groupManagerInstance = new GroupManager();
    }

    public void addAvailableServerGroup(ServerGroup serverGroup) {
        availableServerGroups.add(serverGroup);
    }

    public static GroupManager getGroupManager() {
        return groupManagerInstance;
    }
}
