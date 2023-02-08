package org.scanner.utils;

import com.google.gson.Gson;
import com.google.gson.GsonBuilder;
import com.google.gson.reflect.TypeToken;

import java.io.*;
import java.util.Scanner;

public class LocalSettings {
    private final String PATH_TO_SETTINGS_FILE = "src/main/resources/settings.json";

    private Settings currentSettings = new Settings();

    public LocalSettings() {
    }

    public void displaySettingsMenu() {
        boolean settingsMenu = true;
        while (settingsMenu) {
            System.out.println("Settings/Other Menu:");
            System.out.println("1 - Set timeout/scan starting port/scan ending port\n" +
                    "2 - Reset settings to default\n" +
                    "3 - Learn about this CLI application\n" +
                    "4 - Return to main menu");

            Scanner scanner = new Scanner(System.in);
            int settingsMenuSelection = scanner.nextInt();
            switch (settingsMenuSelection) {
                case 1:
                    System.out.printf("Current settings: %s ms timeout, port scan starts at port %s and ends in %s.",
                            currentSettings.getTimeout(), currentSettings.getStartingPort(), currentSettings.getEndingPort());
                    System.out.println("\nEnter how many ms you would like to set the socket connection timeout to.");
                    int timeout = scanner.nextInt();
                    currentSettings.setTimeout(timeout);
                    System.out.println("Enter the port number you want your port scans to start at.");
                    int startPort = scanner.nextInt();
                    currentSettings.setStartingPort(startPort);
                    System.out.println("Enter the port number you want your port scans to end at.");
                    int endPort = scanner.nextInt();
                    currentSettings.setEndingPort(endPort);
                    System.out.println();
                    writeSettings(currentSettings);
                    System.out.printf("Settings have been saved to %s ms timeout, %s starting port scan and %s ending port scan",
                            timeout, startPort, endPort);
                    System.out.println();
                    break;
                case 2:
                    resetDefaults();
                    System.out.println("Settings have been reset to default settings");
                    break;
                case 3:
                    printAboutThis();
                    break;
                default:
                    System.out.println("Going back to main menu.");
                    settingsMenu = false;
            }
        }
    }

    public void writeSettings(Settings settings) {
        Gson gson = new GsonBuilder().setPrettyPrinting().create();
        try (BufferedWriter writer = new BufferedWriter(new FileWriter(PATH_TO_SETTINGS_FILE))) {
            gson.toJson(settings, writer);
            writer.close();
        } catch (IOException e) {
            System.out.println("Error trying to save settings to local settings.json file.");
        }
    }

    public Settings loadSettings() {
        Gson gson = new Gson();

        try (Reader reader = new FileReader(PATH_TO_SETTINGS_FILE)) {
            Settings settings = gson.fromJson(reader, new TypeToken<Settings>() {}.getType());
            currentSettings = settings;
            return settings;
        } catch (IOException e) {
            System.out.println("Error loading settings from settings file.");
        }
        System.out.println("Loading default settings since none were found in local settings.json file.");
        return new Settings();
    }

    public void resetDefaults() {
        this.currentSettings = new Settings();
        writeSettings(currentSettings);
    }

    /**
     * Prints information about the application. Data is saved in the local resources/about.txt file.
     */

    public void printAboutThis() {
        String PATH_TO_ABOUT_FILE = "src/main/resources/about.txt";
        try (FileReader reader = new FileReader(PATH_TO_ABOUT_FILE)) {
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

    public Settings getCurrentSettings() {
        return currentSettings;
    }
}