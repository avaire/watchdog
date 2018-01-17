package com.avairebot.wrapper;

public class Logger {

    private static final String prefix = "[AVAIRE-WRAPPER] ";

    public static void info(String message) {
        System.out.println(prefix + message);
    }

    public static void error(String message) {
        System.err.println(prefix + message);
    }
}
