package org.scanner.utils;

import java.util.Objects;

public class Settings {
    private int timeout = 200;
    private int startingPort = 1;

    //TODO - increase default max port once multithreading is implemented
    private int endingPort = 500;

    public Settings() {
    }

    public Settings(int timeout, int startingPort, int endingPort) {
        this.timeout = 200;
        this.startingPort = 1;
        this.endingPort = 500;
    }

    public int getTimeout() {
        return timeout;
    }

    public void setTimeout(int timeout) {
        this.timeout = timeout;
    }

    public int getStartingPort() {
        return startingPort;
    }

    public void setStartingPort(int startingPort) {
        this.startingPort = startingPort;
    }

    public int getEndingPort() {
        return endingPort;
    }

    public void setEndingPort(int endingPort) {
        this.endingPort = endingPort;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (!(o instanceof Settings settings)) return false;
        return getTimeout() == settings.getTimeout() && getStartingPort() == settings.getStartingPort() && getEndingPort() == settings.getEndingPort();
    }

    @Override
    public int hashCode() {
        return Objects.hash(getTimeout(), getStartingPort(), getEndingPort());
    }

    @Override
    public String toString() {
        return "Settings{" +
                "timeout=" + timeout +
                ", startingPort=" + startingPort +
                ", endingPort=" + endingPort +
                '}';
    }
}
