package com.avairebot.wrapper;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;

public class Main {


    private static final Logger LOGGER = new Logger();
    private static final String jarName = "AvaIre.jar";

    public static void main(String[] args) throws InterruptedException, IOException {
        ApplicationBootstrap bootstrap = new ApplicationBootstrap();

        bootstrap.start();

        BufferedReader consoleReader = new BufferedReader(new InputStreamReader(System.in));

        String consoleLine;
        while ((consoleLine = consoleReader.readLine()) != null) {
            if (consoleLine.equalsIgnoreCase("shutdown")) {
                bootstrap.shutdown();
            }
        }
    }
}
