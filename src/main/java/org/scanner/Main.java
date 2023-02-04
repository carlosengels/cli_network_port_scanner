package org.scanner;

import org.scanner.port.Host;

import java.util.List;
import java.util.Scanner;

public class Main {
    public static void main(String[] args) {
        boolean runApp = true;

        //Dependencies
        HostRepository hostRepository = new HostRepository();
        PortScanner portScanner = new PortScanner();

        /**
         * Basic CLI UI to facilitate navigating and using of port scanner.
         */
        //UI
        System.out.println("Servus! This is the simple Watchtower port scanner.");
        while (runApp) {
            Scanner scanner = new Scanner(System.in);
            System.out.println("Select one of the following options:");
            System.out.println("1 - Add a new host to list\n" +
                    "2 - Scan all hosts\n" +
                    "3 - Scan a Host\n" +
                    "4 - Print open ports for all hosts\n" +
                    "5 - Print open ports for a host\n" +
                    "6 - Settings/Help\n" +
                    "7 - Quit");
            int mainMenuChoice = scanner.nextInt();

            switch (mainMenuChoice) {
                case 1: //TODO implement validating data entry
                    String name;
                    String hostname;
                    String ipV4Address;
                    System.out.println("1 - Add a new host to list");
                    System.out.println("What is the hostname (i.e. URL) of the host?");
                    hostname = scanner.next();
                    System.out.println("What is the IPv4 Address of the host?");
                    ipV4Address = scanner.next();
                    System.out.println("What name do you want to call this host locally?");
                    name = scanner.next();
                    hostRepository.addHost(new Host(name, hostname, ipV4Address));

                    System.out.printf("%s: IP: %s Hostname: %s added to local save file.", name, ipV4Address, hostname);

                case 2: //TODO implement wait time so menu stalls until scan is complete
                    System.out.println("2 - Scan all hosts");
                    portScanner.scanAllHosts(hostRepository.getHosts());
                    boolean jsonStatus = hostRepository.updateJson();
                    System.out.println("Scanning of all hosts finished.");
                    System.out.println("Json updated: " + jsonStatus);
                    break;
                case 3:
                    System.out.println("3 - Scan a host");
                    System.out.println("Select which host to scan by it's preceding index number:");
                    List<Host> currentHosts = hostRepository.getHosts();
                    for (int i = 0; i < currentHosts.size(); i++) {
                        System.out.println(i + currentHosts.get(i).toString());
                    }
                    int scanSelection = scanner.nextInt();
                    portScanner.scanHost(currentHosts.get(scanSelection));
                    System.out.println("Host record has been updated with latest scan result.");
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
    }
}