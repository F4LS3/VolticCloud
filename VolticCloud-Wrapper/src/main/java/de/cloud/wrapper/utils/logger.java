package de.cloud.wrapper.utils;

import java.sql.Timestamp;
import java.text.SimpleDateFormat;
import java.util.Date;

public class logger {

    private static SimpleDateFormat sdf = new SimpleDateFormat("[dd.MM.yyyy] [HH:mm:ss]");

    public logger(loglevel level, Object loggedMessage) {
        if(level.equals(loglevel.INFO)) {
            loggedMessage = "&green" + loggedMessage + " &reset";
            System.out.println(sdf.format(new Timestamp(System.currentTimeMillis())) + " [INFO] " + loggedMessage.toString().replaceAll("&red", Ansi.ANSI_RED).replaceAll("&black", Ansi.ANSI_BLACK).replaceAll("&blue", Ansi.ANSI_BLUE).replaceAll("&cyan", Ansi.ANSI_CYAN).replaceAll("&green", Ansi.ANSI_GREEN).replaceAll("&purple", Ansi.ANSI_PURPLE).replaceAll("&yellow", Ansi.ANSI_YELLOW).replaceAll("&white", Ansi.ANSI_WHITE).replaceAll("&reset", Ansi.ANSI_RESET));
        } else if(level.equals(loglevel.WARNING)) {
            loggedMessage = "&yellow" + loggedMessage + " &reset";
            System.out.println(sdf.format(new Timestamp(System.currentTimeMillis())) + " [WARNING] " + loggedMessage.toString().replaceAll("&red", Ansi.ANSI_RED).replaceAll("&black", Ansi.ANSI_BLACK).replaceAll("&blue", Ansi.ANSI_BLUE).replaceAll("&cyan", Ansi.ANSI_CYAN).replaceAll("&green", Ansi.ANSI_GREEN).replaceAll("&purple", Ansi.ANSI_PURPLE).replaceAll("&yellow", Ansi.ANSI_YELLOW).replaceAll("&white", Ansi.ANSI_WHITE).replaceAll("&reset", Ansi.ANSI_RESET));
        } else if(level.equals(loglevel.ERROR)) {
            loggedMessage = "&red" + loggedMessage + " &reset";
            System.out.println(sdf.format(new Timestamp(System.currentTimeMillis())) + " [ERROR] " + loggedMessage.toString().replaceAll("&red", Ansi.ANSI_RED).replaceAll("&black", Ansi.ANSI_BLACK).replaceAll("&blue", Ansi.ANSI_BLUE).replaceAll("&cyan", Ansi.ANSI_CYAN).replaceAll("&green", Ansi.ANSI_GREEN).replaceAll("&purple", Ansi.ANSI_PURPLE).replaceAll("&yellow", Ansi.ANSI_YELLOW).replaceAll("&white", Ansi.ANSI_WHITE).replaceAll("&reset", Ansi.ANSI_RESET));
        } else if(level.equals(loglevel.BLANK)) {
            System.out.println(loggedMessage);
        }
    }

    /**public logger(loglevel level, String loggedMessage) {
        if(level.equals(loglevel.INFO)) {
            System.out.println(sdf.format(timestamp) + " [INFO] " + loggedMessage.replaceAll(""));
        } else if(level.equals(loglevel.WARNING)) {
            System.out.println(sdf.format(timestamp) + " [WARNING] " + loggedMessage);
        } else if(level.equals(loglevel.ERROR)) {
            System.out.println(sdf.format(timestamp) + " [ERROR] " + loggedMessage);
        } else if(level.equals(loglevel.BLANK)) {
            System.out.println(loggedMessage);
        }
    }**/
}
