package org.scanner;


import org.scanner.port.Port;

import java.util.List;

public class Main {
    public static final String SAVE_FILE_PATH = "C:\\Users\\engel\\JavaProjects\\PortScanner\\untitled\\src\\main\\resources\\scanResults.txt";
    public static final HostRepository hostrepository = new HostRepository();

    public static void main(String[] args) {


        PortScanner portScanner = new PortScanner();
        List<Port> results = portScanner.scanIP("localhost", 250, 1, 1000);
        System.out.println(results);

        results = portScanner.scanIP("104.200.17.209", 250, 1, 1000);
        System.out.println(results);
        


    }
}