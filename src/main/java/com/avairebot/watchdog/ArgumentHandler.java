package com.avairebot.watchdog;

import org.apache.commons.cli.CommandLine;

import java.util.ArrayList;
import java.util.Arrays;
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

        if (commandLine.hasOption("use-plugin-index")) {
            list.add("--use-plugin-index");
        }

        if (commandLine.hasOption("use-environment-variables")) {
            list.add("--use-environment-variables");
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
            list.addAll(Arrays.asList(commandLine.getOptionValue("jvm-argument", "").split(" ")));
        }
    }
}
