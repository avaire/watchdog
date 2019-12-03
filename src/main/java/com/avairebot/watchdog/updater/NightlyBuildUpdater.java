package com.avairebot.watchdog.updater;

import com.avairebot.watchdog.Application;
import com.avairebot.watchdog.Logger;
import com.avairebot.watchdog.Main;
import com.avairebot.watchdog.contacts.Updater;

import java.net.URL;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.nio.file.StandardCopyOption;

public class NightlyBuildUpdater extends Updater {

    public NightlyBuildUpdater(Application application) {
        super(application);
    }

    @Override
    public void run() throws Exception {
        Logger.info("Downloading the latest version using the nightly build.");

        System.setProperty("http.agent", "Chrome");

        String nightBuildUrl = "https://avairebot.com/nightly-build.jar";
        Files.copy(
            new URL(nightBuildUrl).openStream(),
            Paths.get(Main.avaireJar.toURI()),
            StandardCopyOption.REPLACE_EXISTING
        );

        Logger.info("The nightly build has been downloaded successfully!");
    }
}
