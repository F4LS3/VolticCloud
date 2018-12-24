package de.cloud.master.manager;

public interface Command {

    void execute(String[] args);
    String getUsage();
}
