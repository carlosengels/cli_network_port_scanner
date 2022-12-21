package org.scanner;

import org.scanner.port.Host;
import org.scanner.port.Port;
import org.scanner.port.Status;

import java.io.BufferedWriter;
import java.io.File;
import java.io.FileWriter;
import java.io.IOException;
import java.net.Socket;
import java.time.LocalDateTime;

public class PortScanner {

    private Socket s;

    /**
     * @param host that will be scanned via IP in its variables
     * @return - String of port scan results
     */
    public String scan(Host host) {
        try {
            BufferedWriter bufferedWriter = new BufferedWriter(new FileWriter(Main.SAVE_FILE_PATH));
            bufferedWriter.write("   ---   Scanning: " + host.getIpV4Address() + "   ---   " + LocalDateTime.now());
            bufferedWriter.newLine();

            for (int i = 1; i < 500; i++) {
                try {
                    s = new Socket(host.getIpV4Address(), i);
                    Port scannedPort = new Port(i, Status.OPEN);
                    host.getPorts().add(scannedPort);
                    bufferedWriter.write(scannedPort.toString());

                } catch (IOException e) {
                    Port scannedPort = new Port(i, Status.CLOSED);
                    host.getPorts().add(scannedPort);
                    bufferedWriter.write(scannedPort.toString());
                }
            }
            bufferedWriter.close();
        } catch (IOException e) {
            System.out.println(e);
            System.out.println("Error accessing scanResults file.");
        }
        System.out.printf("%d entries were added to the Host's Ports entry", host.getPorts().size());
    }

}
