package org.scanner;

import org.scanner.hostdata.Host;
import org.scanner.hostdata.Port;
import org.scanner.hostdata.Status;
import org.scanner.utils.BatchPortScanner;
import org.scanner.utils.LocalSettings;

import java.io.IOException;
import java.net.InetSocketAddress;
import java.net.Socket;
import java.net.SocketException;
import java.net.SocketTimeoutException;
import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;

public class PortScanner {

    private int timeout;
    private int startingPort;
    private int endingPort;
    private int threadCount = 4;

    private LocalSettings localSettings = new LocalSettings();

    public PortScanner() {
        reloadSettings();
    }

    /**
     * PortScanner implements multithreaded functionality by dividing the scanning range over the specified thread count.
     * @param ipV4Address - scan the ports of given IP
     * @return a new List of Ports
     */
    public List<Port> scanIP(String ipV4Address) {
        System.out.printf("Scanning ports %d to %d. Connection timeout is set to %dms.\n", startingPort, endingPort, timeout);
        List<Port> ports = new ArrayList<>();
        int portScanRange = endingPort - startingPort + 1; // +1 range because we scan the last port number inclusively

        List<BatchPortScanner> batchPortScanners = new ArrayList<>();
        List<Thread> threads = new ArrayList<>();

        //Create threads with a batchPortScanner in a loop
        int rangePerThread = portScanRange / threadCount;
        int startingPortForScan = startingPort;
        for (int i = 0; i < 4; i++) {
            int threadStartPort = startingPortForScan;
            int threadEndingPort = startingPortForScan + rangePerThread;
            BatchPortScanner batchPortScanner = new BatchPortScanner(ipV4Address, timeout, threadStartPort, threadEndingPort);
            batchPortScanners.add(batchPortScanner);
            Thread thread = new Thread(batchPortScanner);
            threads.add(thread);
            thread.start();
            startingPortForScan = threadEndingPort;
        }
        try {
            waitForThreadsToComplete(threads);
        } catch (InterruptedException e) {
            System.out.println("Issue with scanning port in on of the threads." + e.getMessage());
        }

        // Merge the port scan results from each BatchPortScanner to the final result
        for (BatchPortScanner batchPortScanner : batchPortScanners) {
            ports.addAll(batchPortScanner.getPorts());
        }
        return ports;
    }

    /**
     * Scans the host and updates the host profile with most recent scan
     * @param host - host object to be scanned and updated
     */
    public void scanHost(Host host) {
        reloadSettings();
        System.out.printf("Scanning host %s\n", host.getName());
        System.out.printf("This could take up to %d seconds. \n", timeout*(endingPort-startingPort) / threadCount);
        host.setPorts(scanIP(host.getIpV4Address()));
        host.setMostRecentScan(LocalDateTime.now());
    }

    /**
     * Scan all hosts in the hosts list
     * @param hosts - List of hosts
     */
    public void scanAllHosts(List<Host> hosts) {
        System.out.printf("Scanning %d hosts. \n", hosts.size());
        for (int i = 0; i < hosts.size(); i++){
            scanHost(hosts.get(i));
            System.out.printf("Scan of host %s complete. \n", hosts.get(i).getName());
        }
    }

    public void reloadSettings() {
        localSettings.loadSettings();
        this.timeout = localSettings.getCurrentSettings().getTimeout();
        this.startingPort = localSettings.getCurrentSettings().getStartingPort();
        this.endingPort = localSettings.getCurrentSettings().getEndingPort();
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

    /**
     * Makes the thread calling this method wait until passed in threads are done executing before proceeding.
     *
     * @param threads to wait on
     * @throws InterruptedException
     */
    public static void waitForThreadsToComplete(List<Thread> threads) throws InterruptedException {
        for (Thread thread : threads) {
            thread.join();
        }
    }
}
