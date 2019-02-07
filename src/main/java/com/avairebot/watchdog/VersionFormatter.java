package com.avairebot.watchdog;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.util.Arrays;

class VersionFormatter {

    static void formatAndSend() {
        ProcessBuilder pb = new ProcessBuilder();

        pb.command(Arrays.asList("java", "-jar", "AvaIre.jar", "--version", "--no-colors"));

        try {
            Process process = pb.start();

            StringBuilder content = new StringBuilder();
            try (InputStream inputStream = process.getInputStream()) {
                InputStreamReader streamReader = new InputStreamReader(inputStream);
                BufferedReader reader = new BufferedReader(streamReader);

                String line;
                while ((line = reader.readLine()) != null) {
                    content.append(line).append("\n");
                }
            } catch (IOException e) {
                e.printStackTrace();
            }
            process.waitFor();
            process.destroy();

            if (content.toString().trim().isEmpty()) {
                System.out.println("No AvaIre.jar file were found, unable to show the version of Ava being used.");
                System.out.println("Watchdog is version: " + AppInfo.getAppInfo().getVersion());
                return;
            }

            for (String line : content.toString().split("\n")) {
                System.out.println(line);
                if (line.contains("Version:")) {
                    StringBuilder watchdogLine = new StringBuilder("\tWatchdog:");
                    int index = line.indexOf(line.trim().split("\\s+")[1]);
                    while (watchdogLine.length() < index) {
                        watchdogLine.append(" ");
                    }
                    System.out.println(watchdogLine.append(AppInfo.getAppInfo().getVersion()).toString());
                }
            }
        } catch (IOException | InterruptedException e) {
            e.printStackTrace();
        }
    }
}
