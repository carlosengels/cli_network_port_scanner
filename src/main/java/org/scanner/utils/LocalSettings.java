package org.scanner.utils;

import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;

public class LocalSettings {
    private final String PATH_TO_SETTINGS_FILE = "src/main/resources/settings.json";
    private final String PATH_TO_ABOUT_FILE = "src/main/resources/about.txt";

    private int timeout = 200;
    private int startingPort = 1;
    //TODO - increase default max port once multithreading is implemented
    private int endingPort = 500;

    public LocalSettings() {
    }

    public void adjustSettings() {
        //TODO
    }

    public void resetDefaults() {
        //TODO
    }

    public void printAboutThis() throws IOException {
        FileReader reader = new FileReader(PATH_TO_ABOUT_FILE);
        try {
            BufferedReader br = new BufferedReader(reader);
            String line = br.readLine();
            while (line != null) {
                System.out.println(line);
                line = br.readLine();
            }
            br.close();
        } catch (IOException e) {
            System.out.println("Local about file cannot be read.");
            e.printStackTrace();
        }
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
}