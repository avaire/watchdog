package com.avairebot.watchdog;

public class Logger {

    private static final String prefix = "[WATCHDOG] ";

    public static void info(String message) {
        System.out.println(prefix + message);
    }

    public static void error(String message) {
        System.err.println(prefix + message);
    }
}
