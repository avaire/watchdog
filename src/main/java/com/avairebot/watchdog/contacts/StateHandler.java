package com.avairebot.watchdog.contacts;

import com.avairebot.watchdog.Application;

public abstract class StateHandler implements Runnable {

    protected final Application app;

    public StateHandler(Application application) {
        this.app = application;
    }
}
