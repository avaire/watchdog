package com.avairebot.watchdog;

import com.avairebot.shared.ExitCodes;

import java.io.*;
import java.util.ArrayList;
import java.util.Arrays;

public class ApplicationBootstrap extends Thread {

    private boolean running = true;

    private int recentBoots = 0;
    private long lastBoot = 0L;

    private Process process;

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
                        updateOrInstallAvaIre();
                        break;

                    case 130:
                    case ExitCodes.EXIT_CODE_NORMAL:
                        Logger.info("Now shutting down...");
                        shutdown();
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

    public void updateOrInstallAvaIre() {
        Logger.info("Updating git repo and re-compiling jar file");

        File avaireDirectory = new File("avaire");
        CommandProcessResult result = Main.runCommandAndReturnOutput(Arrays.asList(
            "cd avaire", "git pull", "gradle build"
        ), true);

        if (!result.isSuccess()) {
            System.exit(0);
        }

        File jarFile = new File(avaireDirectory, "AvaIre.jar");

        if (!jarFile.exists()) {
            Logger.error("Failed to find AvaIar.jar file within the sources directory! Did something go wrong?");
            System.exit(0);
        }

        File oldJar = new File("./AvaIre.jar");
        oldJar.delete();

        jarFile.renameTo(oldJar);
        Logger.info("AvaIre has been successfully updated!");
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
        list.add("-jar");
        list.add("AvaIre.jar");

        pb.command(list);

        return pb.start();
    }

    public Process getProcess() {
        return process;
    }

    public void shutdown() {
        running = false;

        if (getProcess() != null) {
            getProcess().destroy();
        }

        System.exit(0);
    }
}
