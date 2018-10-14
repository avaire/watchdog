package com.avairebot.watchdog;

import com.avairebot.shared.ExitCodes;
import org.apache.commons.cli.*;

import java.io.File;
import java.io.IOException;

@SuppressWarnings("WeakerAccess")
public class Main {

    public static final boolean isWindows;
    public static final File avaireJar = new File("AvaIre.jar");

    static {
        isWindows = System.getProperty("os.name").toLowerCase().contains("win");
    }

    public static void main(String[] args) throws IOException {
        Options options = new Options();

        options.addOption(new Option("h", "help", false, "Displays this help menu."));
        options.addOption(new Option("v", "version", false, "Displays the current version of the application."));
        options.addOption(new Option("sc", "shard-count", true, "Sets the amount of shards the bot should start up."));
        options.addOption(new Option("nocolor", "no-colors", false, "Disables colors for commands and AI actions in the terminal."));
        options.addOption(new Option("d", "debug", false, "Enables debugging mode, this will log extra information to the terminal."));
        options.addOption(new Option("jarg", "jvm-argument", true, "Sets the JVM arguments that the application should be started with."));

        CommandLineParser parser = new DefaultParser();
        HelpFormatter formatter = new HelpFormatter();

        try {
            CommandLine cmd = parser.parse(options, args);
            if (cmd.hasOption("help")) {
                formatter.printHelp("Help Menu", options);
                System.exit(ExitCodes.EXIT_CODE_NORMAL);
            }

            new Application(cmd);
        } catch (ParseException e) {
            System.out.println(e.getMessage());
            formatter.printHelp("", options);

            System.exit(ExitCodes.EXIT_CODE_NORMAL);
        }
    }
}
