package com.avairebot.watchdog.states;

import com.avairebot.watchdog.Application;
import com.avairebot.watchdog.contacts.StateHandler;

public class ShutdownState extends StateHandler {

    public ShutdownState(Application application) {
        super(application);
    }

    @Override
    public void run() {
        app.setRunning(false);

        if (app.getProcess() != null) {
            app.getProcess().destroy();
        }

        System.exit(0);
    }
}
