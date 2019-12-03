package com.avairebot.watchdog.states;

import com.avairebot.watchdog.Application;
import com.avairebot.watchdog.contacts.StateHandler;
import com.avairebot.watchdog.contacts.Updater;

public class UpdateState extends StateHandler {

    private Updater updater;

    public UpdateState(Application application) {
        super(application);
    }

    public void setUpdater(Updater updater) {
        this.updater = updater;
    }

    @Override
    public void run() {
        if (updater == null) {
            return;
        }
        
        try {
            updater.run();
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
}
