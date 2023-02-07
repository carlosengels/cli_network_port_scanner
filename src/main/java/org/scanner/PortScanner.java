package org.scanner;

import org.scanner.hostdata.Host;
import org.scanner.hostdata.Port;
import org.scanner.hostdata.Status;

import java.io.IOException;
import java.net.InetSocketAddress;
import java.net.Socket;
import java.net.SocketException;
import java.net.SocketTimeoutException;
import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;

public class PortScanner {

    private int timeout = 200;
    private int startingPort = 1;
    //TODO - increase default max port once multithreading is implemented
    private int endingPort = 500;


    /**
     * @param ipV4Address - scan the ports of given IP
     * @return a new List of Ports
     */
    public List<Port> scanIP(String ipV4Address) {
        System.out.printf("Scanning ports %d to %d. Timeout has been set to %dms.\n", startingPort, endingPort, timeout);
        List<Port> ports = new ArrayList<>();
        for (int i = startingPort; i <= endingPort; i++) {
            try {
                Socket socket = new Socket();
                socket.connect(new InetSocketAddress(ipV4Address, i), timeout);
                socket.close();
                ports.add(new Port(i, Status.OPEN));
            } catch (SocketException | SocketTimeoutException e) {
                ports.add(new Port(i, Status.CLOSED));
            } catch (IllegalArgumentException | IOException e) {
                System.out.println(e);
                ports.add(new Port(i, Status.NA));
            }
        }
        return ports;
    }

    /**
     * Scans the host and updates the host profile with most recent scan
     * @param host - host object to be scanned and updated
     */
    public void scanHost(Host host) {
        System.out.printf("Scanning host %s\n", host.getName());
        System.out.printf("This could take up to %d seconds. \n", timeout*(endingPort-startingPort));
        host.setPorts(scanIP(host.getIpV4Address()));
        host.setMostRecentScan(LocalDateTime.now());
    }

    public void scanAllHosts(List<Host> hosts) {
        System.out.printf("Scanning %d hosts. \n", hosts.size());
        for (int i = 0; i < hosts.size(); i++){
            scanHost(hosts.get(i));
            System.out.printf("Scan of host %s complete. \n", hosts.get(i).getName());
        }
    }

    /** Getters and Setters */
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
