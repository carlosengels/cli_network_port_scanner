//package org.scanner;
//
//import org.scanner.port.Host;
//
//import java.util.InputMismatchException;
//import java.util.List;
//import java.util.Scanner;
//
//public class UserInterface {
//    private int mainMenuChoice = -1;
//    private HostRepository hostRepository;
//    private PortScanner portScanner;
//
//    public UserInterface(HostRepository hostRepository, PortScanner portScanner) {
//        this.hostRepository = hostRepository;
//        this.portScanner = portScanner;
//    }
//
//    /**
//     * Syncs menus and user input
//     * @return - tbd status notification
//     */
////    public int displayMenu() {
////        try {
////            mainMenu();
////            // select the next menu based on mainMenu Choice - switch?
////            switch (mainMenuChoice) {
////                case 1 : selectProfile(hostRepository.getHosts());
////                case 2 : createProfile();
////                case 3 : setSettings();
////                case 4 : printManual();
////                case 5 : break;
////                default:
////                    System.out.println("Invalid input.");
////            }
////
////        } catch (InputMismatchException e) {
////            System.out.println(e);
////        }
////        return -1;
////    }
//
//    /**
//     * Produces the main menu GUI and functionality.
//     * @return - returns the main menu choice.
//     */
//    public int mainMenu() {
//        Scanner scan = new Scanner(System.in);
//        System.out.println("Welcome to the groovy portScanner." +
//                "\n   ---  Menu  ---" +
//                "\n1 - Select Profile" +
//                "\n2 - Create Profile" +
//                "\n3 - Settings" +
//                "\n4 - Manual" +
//                "\n5 - Exit");
//        mainMenuChoice = scan.nextInt();
//        System.out.println("You selected: " + mainMenuChoice);
//        return mainMenuChoice;
//    }
//
//    //TODO Implement further menu methods which use the previous methods return value to choose submenu
//
//    /**
//     * Prints out all the current saved Host (printed index starts at 1) and prompts user to select one via index.
//     * @param myHosts
//     * @return - index of chosen Host (index starts at 0)
//     */
//    public int selectProfile(List<Host> myHosts) {
//        Scanner scan = new Scanner(System.in);
//        System.out.printf("These are the currently saved profiles (%d total):\n", myHosts.size());
//        for (int i = 0; i < myHosts.size(); i++) {
//            String menuEntry = (i+1) + " - " + myHosts.get(i).getName();
//            System.out.println(menuEntry);
//        }
//        System.out.println("Choose the entry via index (e.g. 1)\n");
//        int profileIndex = scan.nextInt() - 1;
//        return profileIndex;
//    }
//
//    public String createProfile() {
//        //TODO - Implement
//        return "";
//    }
//
//    public void setSettings() {
////TODO - Implement
//    }
//
//    public void printManual() {
////TODO - Implement
//    }
//
//
//}
