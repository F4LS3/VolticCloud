package de.cloud.wrapper.core;

public interface Command {

    void execute(String[] args);
    String getUsage();
}
