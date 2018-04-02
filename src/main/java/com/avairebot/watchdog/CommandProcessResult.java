package com.avairebot.watchdog;

public class CommandProcessResult {

    private final String result;
    private final Exception exception;
    private final boolean success;

    public CommandProcessResult(String result, Exception exception) {
        this.result = result;
        this.exception = exception;
        this.success = exception == null;
    }

    public String getResult() {
        return result;
    }

    public Exception getException() {
        return exception;
    }

    public boolean isSuccess() {
        return success;
    }

    public void printStacktrace() {
        if (exception != null) {
            exception.printStackTrace();
        }
    }
}
