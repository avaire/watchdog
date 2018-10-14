package com.avairebot.watchdog;

import java.io.BufferedReader;
import java.io.File;
import java.io.IOException;
import java.io.InputStreamReader;

@SuppressWarnings("WeakerAccess")
public class Main {

    public static final boolean isWindows;
    public static final File avaireJar = new File("AvaIre.jar");

    static {
        isWindows = System.getProperty("os.name").toLowerCase().contains("win");
    }

    public static void main(String[] args) throws InterruptedException, IOException {
        Application bootstrap = new Application(args);

        if (!avaireJar.exists()) {
            Logger.info("AvaIre sources was not found!");
            bootstrap.getUpdateState().run();
        }

        bootstrap.start();

        BufferedReader consoleReader = new BufferedReader(new InputStreamReader(System.in));

        String consoleLine;
        while ((consoleLine = consoleReader.readLine()) != null) {
            if (consoleLine.equalsIgnoreCase("shutdown")) {
                bootstrap.getShutdownState().run();
            }
        }
    }
}
