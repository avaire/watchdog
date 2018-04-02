package com.avairebot.watchdog;

import java.io.*;
import java.util.Collections;
import java.util.List;

public class Main {

    private static final Logger LOGGER = new Logger();
    private static boolean isWindows;

    public static void main(String[] args) throws InterruptedException, IOException {
        isWindows = System.getProperty("os.name").toLowerCase().contains("win");

        if (!hasGradleInstalled()) {
            System.exit(0);
        }

        if (!hasGitInstalled()) {
            System.exit(0);
        }

        ApplicationBootstrap bootstrap = new ApplicationBootstrap();

        File avaireDirectory = new File("avaire");
        if (!avaireDirectory.exists() || !avaireDirectory.isDirectory()) {
            Logger.info("AvaIre sources was not found, using git to clone down sources...");
            CommandProcessResult result = Main.runCommandAndReturnOutput(
                Collections.singletonList("git clone https://github.com/avaire/avaire.git avaire")
            );

            if (!result.isSuccess()) {
                Logger.error("Error occurred while trying to update Ava: " + result.getResult());
                result.printStacktrace();
                System.exit(result.getException().hashCode());
            }
            Logger.info("AvaIre sources has been cloned down successfully");
            bootstrap.updateOrInstallAvaIre();
        }

        bootstrap.start();

        BufferedReader consoleReader = new BufferedReader(new InputStreamReader(System.in));

        String consoleLine;
        while ((consoleLine = consoleReader.readLine()) != null) {
            if (consoleLine.equalsIgnoreCase("shutdown")) {
                bootstrap.shutdown();
            }
        }
    }

    private static boolean hasGitInstalled() {
        System.out.print(LOGGER.getPrefix() + "Checking if Git is installed...... ");
        CommandProcessResult git = runCommandAndReturnOutput(Collections.singletonList("git --version"));

        if (git.isSuccess()) {
            System.out.println("Found: " + git.getResult());
            return true;
        }

        System.out.println("Git was not found! Is it installed?");
        git.printStacktrace();

        return false;
    }

    private static boolean hasGradleInstalled() {
        System.out.print(LOGGER.getPrefix() + "Checking if Gradle is installed... ");
        CommandProcessResult gradle = runCommandAndReturnOutput(Collections.singletonList("gradle -v"));

        if (gradle.isSuccess()) {
            String version = gradle.getResult().split("\n")[2];
            System.out.println("Found: " + version);
            return true;
        }

        System.out.println("Gradle was not found! Is it installed?");
        gradle.printStacktrace();

        return false;
    }

    public static CommandProcessResult runCommandAndReturnOutput(List<String> commands) {
        return runCommandAndReturnOutput(commands, false);
    }

    public static CommandProcessResult runCommandAndReturnOutput(List<String> commands, boolean sendOutputDuringRuntime) {
        try {
            Runtime runtime = Runtime.getRuntime();

            Process process = runtime.exec((isWindows ? "cmd /c " : "") + String.join(" && ", commands));

            try (InputStream inputStream = process.getInputStream()) {
                InputStreamReader streamReader = new InputStreamReader(inputStream);
                BufferedReader reader = new BufferedReader(streamReader);

                String line;
                StringBuilder result = new StringBuilder();
                while ((line = reader.readLine()) != null) {
                    if (sendOutputDuringRuntime) {
                        System.out.println(line);
                    }
                    result.append(line);
                    result.append("\n");
                }

                return new CommandProcessResult(result.toString(), null);
            } catch (IOException e) {
                return new CommandProcessResult(e.getMessage(), e);
            }
        } catch (IOException e) {
            return new CommandProcessResult(e.getMessage(), e);
        }
    }
}
