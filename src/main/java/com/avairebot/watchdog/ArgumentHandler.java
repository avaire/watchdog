package com.avairebot.watchdog;

import org.apache.commons.cli.CommandLine;

import java.util.ArrayList;
import java.util.List;

class ArgumentHandler {

    static void addApplicationArguments(CommandLine commandLine, List<String> list) {
        if (commandLine.hasOption("version")) {
            list.add("--version");
        }

        if (commandLine.hasOption("music")) {
            list.add("--music");
        }

        if (commandLine.hasOption("no-colors")) {
            list.add("--no-colors");
        }

        if (commandLine.hasOption("debug")) {
            list.add("--debug");
        }

        if (commandLine.hasOption("shard-count")) {
            list.add("--shard-count=" + commandLine.getOptionValue("shard-count", "1"));
        }
    }

    static void addJVMArguments(CommandLine commandLine, ArrayList<String> list) {
        if (commandLine.hasOption("jvm-argument")) {
            list.add(commandLine.getOptionValue("jvm-argument", ""));
        }
    }
}
