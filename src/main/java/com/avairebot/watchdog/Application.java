package com.avairebot.watchdog;

import com.avairebot.shared.ExitCodes;
import com.avairebot.watchdog.states.ShutdownState;
import com.avairebot.watchdog.states.UpdateState;
import org.apache.commons.cli.CommandLine;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.util.ArrayList;

public class Application extends Thread {

    private final UpdateState updateState;
    private final ShutdownState shutdownState;

    private final CommandLine commandArguments;

    private boolean running = true;

    private int recentBoots = 0;
    private long lastBoot = 0L;

    private Process process;

    Application(CommandLine commandLine) throws IOException {
        this.commandArguments = commandLine;

        this.updateState = new UpdateState(this);
        this.shutdownState = new ShutdownState(this);

        if (!Main.avaireJar.exists()) {
            Logger.info("AvaIre sources was not found!");
            updateState.run();
        }

        start();

        BufferedReader consoleReader = new BufferedReader(new InputStreamReader(System.in));

        String consoleLine;
        while ((consoleLine = consoleReader.readLine()) != null) {
            if (consoleLine.equalsIgnoreCase("shutdown")) {
                shutdownState.run();
            }
        }
    }

    @Override
    public void run() {
        OUTER:
        while (true) {
            if (!running) break;

            try {
                process = boot();

                try (InputStream inputStream = process.getInputStream()) {
                    InputStreamReader streamReader = new InputStreamReader(inputStream);
                    BufferedReader reader = new BufferedReader(streamReader);

                    String line;
                    while ((line = reader.readLine()) != null) {
                        System.out.println(line);
                    }
                } catch (IOException e) {
                    e.printStackTrace();
                }

                process.waitFor();
                Logger.info("Bot exited with code " + process.exitValue());

                switch (process.exitValue()) {
                    case ExitCodes.EXIT_CODE_UPDATE:
                        Logger.info("Now updating...");
                        updateState.run();
                        break;

                    case 130:
                    case ExitCodes.EXIT_CODE_NORMAL:
                        Logger.info("Now shutting down...");
                        shutdownState.run();
                        break OUTER;

                    // SIGINT received or clean exit
                    default:
                        Logger.info("Now restarting..");
                        break;
                }
            } catch (IOException | InterruptedException e) {
                e.printStackTrace();
            }
        }
    }

    private Process boot() throws IOException {
        // Check that we are not booting too quick (we could be stuck in a login loop)
        if (System.currentTimeMillis() - lastBoot > 3000 * 1000) {
            recentBoots = 0;
        }

        recentBoots++;
        lastBoot = System.currentTimeMillis();

        if (recentBoots >= 4) {
            Logger.info("Failed to restart 3 times, probably due to login errors. Exiting...");
            System.exit(ExitCodes.EXIT_CODE_ERROR);
        }

        ProcessBuilder pb = new ProcessBuilder()
            .inheritIO();

        ArrayList<String> list = new ArrayList<>();

        list.add("java");

        if (Main.isWindows) {
            list.add("-Dfile.encoding=UTF-8");
        }
        ArgumentHandler.addJVMArguments(commandArguments, list);

        list.add("-jar");
        list.add("AvaIre.jar");

        ArgumentHandler.addApplicationArguments(commandArguments, list);

        Logger.info("Starting process: " + String.join(" ", list));

        pb.command(list);

        return pb.start();
    }

    public Process getProcess() {
        return process;
    }

    public void setRunning(boolean running) {
        this.running = running;
    }
}
