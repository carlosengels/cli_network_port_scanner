package org.scanner;

import org.scanner.port.Host;
import org.scanner.port.Port;
import org.scanner.port.Status;

import java.io.IOException;
import java.net.InetSocketAddress;
import java.net.Socket;
import java.net.SocketException;
import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;

public class PortScanner {

    private int timeout = 200;
    private int startingPort = 1;
    //TODO - increase default port once multithreading is implemented
    private int endingPort = 500;


    /**
     * @param ipV4Address - scan the ports of given IP
     * @return a new List of Ports
     */
    public List<Port> scanIP(String ipV4Address) {
        System.out.printf("Scanning ports %d to %d. Timeout has been set to %dms.\n", startingPort, endingPort, timeout);
        List<Port> ports = new ArrayList<>();
        for (int i = startingPort; i <= endingPort; i++) {
            //TODO Parse errors to break down different SocketException and IllegalArgument messages
            try {
                Socket socket = new Socket();
                socket.connect(new InetSocketAddress(ipV4Address, i), timeout);
                socket.close();
                ports.add(new Port(i, Status.OPEN));
            } catch (SocketException e) {
                ports.add(new Port(i, Status.CLOSED));
            } catch (IllegalArgumentException e) {
                System.out.println(e);
                ports.add(new Port(i, Status.NA));
            } catch (IOException e) {
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
        host.setPorts(scanIP(host.getIpV4Address()));
        host.setMostRecentScan(LocalDateTime.now());
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
