package com.avairebot.watchdog.contacts;

import com.avairebot.watchdog.Application;

public abstract class Updater {

    protected final Application app;

    public Updater(Application application) {
        this.app = application;
    }

    public abstract void run() throws Exception;
}
