package org.scanner;


import org.scanner.port.Host;
import org.scanner.port.Port;
import org.scanner.port.Status;

import java.util.List;

public class Main {
    public static void main(String[] args) {

        HostRepository hostRepository = new HostRepository();
        PortScanner portScanner = new PortScanner();


        List<Host> hosts = hostRepository.getHosts();
        for (int i = 0; i < hosts.size(); i++){
            portScanner.scanHost(hosts.get(i));
        }
        hostRepository.updateJson();

        //Print out all open ports
        for (Host host : hosts) {
            System.out.println("Checking open ports for " + host);
            for (Port port : host.getPorts()) {
                if (port.getStatus().equals(Status.OPEN)) {
                    System.out.println("OPEN PORT!");
                    System.out.println(port);
                }
            }
        }
    }
}