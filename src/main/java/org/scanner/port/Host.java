package org.scanner.port;

import java.net.Inet4Address;
import java.net.Inet6Address;
import java.util.ArrayList;

public class Host {
    String name;
    String hostName;
    String ipV4Address;
    ArrayList<Port> ports = new ArrayList<>();

    public Host(String name, String hostName, String ipV4Address) {
        this.name = name;
        this.hostName = hostName;
        this.ipV4Address = ipV4Address;
    }

    public String getName() {
        return name;
    }

    public String getHostName() {
        return hostName;
    }

    public String getIpV4Address() {
        return ipV4Address;
    }

//    public Inet6Address getIpV6Address() {
//        return ipV6Address;
//    }

    public ArrayList<Port> getPorts() {
        return ports;
    }
}
