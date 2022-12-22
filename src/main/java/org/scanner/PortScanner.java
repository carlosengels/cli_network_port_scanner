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

    /**
     *
     * @param ipV4Address - host to be scanner
     * @param timeout - timeout for connection in ms
     * @param start - port number to start scan at
     * @param end - port number to end scan at
     * @return a new List of Ports
     */
    public List<Port> scanIP(String ipV4Address, int timeout, int start, int end) {
        System.out.printf("Scanning ports %d to %d. Timeout has been set to %dms", start, end, timeout);
        List<Port> ports = new ArrayList<>();
        for (int i = start; i <= end; i++) {
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
                ports.add(new Port(i, Status.NA));
                System.out.println(e);
            }
        }
        return ports;
    }

    /**
     * Scans the host and updates the host profile with most recent scan
     * @param host - host object to be scanned and updated
     */
    public void scanHost(Host host, int timeout, int start, int end) {
        host.setPorts(scanIP(host.getIpV4Address(), timeout, start, end));
        host.setMostRecentScan(LocalDateTime.now());
    }
}
