package org.scanner.utils;

import org.scanner.hostdata.Port;
import org.scanner.hostdata.Status;

import java.io.IOException;
import java.net.InetSocketAddress;
import java.net.Socket;
import java.net.SocketException;
import java.net.SocketTimeoutException;
import java.util.ArrayList;
import java.util.List;

public final class BatchPortScanner implements Runnable {
    private final String targetAddress;
    private final int timeout;
    private final int startingPort;
    private final int endingPort;

    private final List<Port> ports = new ArrayList<>();

    public BatchPortScanner(String targetAddress, int timeout, int startingPort, int endingPort) {
        this.targetAddress = targetAddress;
        this.timeout = timeout;
        this.startingPort = startingPort;
        this.endingPort = endingPort;
    }

    public void scanPorts() {
        System.out.printf("A thread is scanning ports %d to %d\n", startingPort, endingPort);
        for (int i = startingPort; i < endingPort; i++) {
            try {
                Socket socket = new Socket();
                socket.connect(new InetSocketAddress(targetAddress, i), timeout);
                socket.close();
                ports.add(new Port(i, Status.OPEN));
            } catch (SocketException | SocketTimeoutException e) {
                ports.add(new Port(i, Status.CLOSED));
            } catch (IllegalArgumentException | IOException e) {
                System.out.println(e);
                ports.add(new Port(i, Status.NA));
            }
        }
    }

    public String getTargetAddress() {
        return targetAddress;
    }

    public int getTimeout() {
        return timeout;
    }

    public int getStartingPort() {
        return startingPort;
    }

    public int getEndingPort() {
        return endingPort;
    }

    public List<Port> getPorts() {
        return ports;
    }

    @Override
    public void run() {
        scanPorts();
    }
}
