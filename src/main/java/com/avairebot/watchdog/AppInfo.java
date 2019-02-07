package com.avairebot.watchdog;

import java.io.IOException;
import java.util.Properties;

public class AppInfo {

    private static AppInfo instance;

    private final String version;
    private final String groupId;
    private final String artifactId;

    private AppInfo() {
        Properties properties = new Properties();
        try {
            properties.load(
                getClass().getClassLoader().getResourceAsStream("app.properties")
            );
        } catch (IOException e) {
            throw new RuntimeException("Failed to load app.properties", e);
        }

        this.version = properties.getProperty("version");
        this.groupId = properties.getProperty("groupId");
        this.artifactId = properties.getProperty("artifactId");
    }

    public static AppInfo getAppInfo() {
        if (instance == null) {
            instance = new AppInfo();
        }
        return instance;
    }

    public String getVersion() {
        return version;
    }

    public String getGroupId() {
        return groupId;
    }

    public String getArtifactId() {
        return artifactId;
    }
}
