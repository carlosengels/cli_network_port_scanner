package org.scanner;



public class Main {
    public static final String SAVE_FILE_PATH = "C:\\Users\\engel\\JavaProjects\\PortScanner\\untitled\\src\\main\\resources\\scanResults.txt";

    public static void main(String[] args) {
        //Dependencies
        PortScanner portScanner = new PortScanner();
        HostRepository hostRepository = new HostRepository();
        UserInterface userInterface = new UserInterface(hostRepository,portScanner);

        // Displays Main menu and collects users choice.

        //TODO Review if this is best way for GUI (framework?)
        int menu1Choice = userInterface.mainMenu();
        int hostChoice;
        switch (menu1Choice) {
            case 1 : hostChoice = userInterface.selectProfile(hostRepository.getHosts());
            portScanner.scan(hostRepository.getHosts().get(hostChoice));
            case 2 : userInterface.createProfile();
            case 3 : userInterface.setSettings();
            case 4 : userInterface.printManual();
            case 5 : break;
            default:
                System.out.println("Invalid input.");
        }

    }
}