package org.scanner;


import org.scanner.port.Host;
import org.scanner.port.Port;
import org.scanner.port.Status;

import java.util.List;
import java.util.Scanner;

public class Main {
    public static void main(String[] args) {
        boolean runApp = true;

        //Dependencies
        HostRepository hostRepository = new HostRepository();
        PortScanner portScanner = new PortScanner();

        //UI
        System.out.println("Servus! This is the simple Watchtower port scanner.");
        while (runApp) {
            Scanner scanner = new Scanner(System.in);
            System.out.println("Select one of the following options:");
            System.out.println("1 - Add/delete a new host to list\n" +
                    "2 - Scan all hosts\n" +
                    "3 - Scan a Host\n" +
                    "4 - Print open ports for all hosts\n" +
                    "5 - Print open ports for a host\n" +
                    "6 - Settings/Help\n" +
                    "7 - Quit");
            int mainMenuChoice = scanner.nextInt();

            switch (mainMenuChoice) {
                case 1:
                    System.out.println("1 - Add/delete a new host to list");
                case 2:
                    System.out.println("2 - Scan all hosts");
                    portScanner.scanAllHosts(hostRepository.getHosts());
                    boolean jsonStatus = hostRepository.updateJson();
                    System.out.println("Scanning of all hosts finished.");
                    System.out.println("Json updated: " + jsonStatus);
                    break;
                case 3: //TODO Implement
                    System.out.println("3 - Scan a host");
                    System.out.println("Feature not yet supported.");
                    break;
                case 4:
                    System.out.println("4 - Print open ports for all hosts");
                    hostRepository.printOpenPorts();
                    break;
                case 5:// TODO Implement
                    System.out.println("3 - Print open ports for a host");
                    System.out.println("Feature not yet supported.");
                    break;
                case 6:  // TODO Implement
                    System.out.println("6 - Settings/Help");
                    System.out.println("Feature not yet supported.");
                    break;
                default:
                    System.out.println("You are exiting the Watchtower port scanner app. Pfiad di!");
                    runApp = false;

            }
        }

//        List<Host> hosts = hostRepository.getHosts();

    }
}