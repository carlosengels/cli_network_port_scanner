package org.scanner.port;

import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;
import java.util.Objects;

public class Host {
    private String name;
    private String hostName;
    private String ipV4Address;
    private List<Port> ports = new ArrayList<>();
    // Stored default LocalDateTime format as String to facilitate writing to JSON.
    private String mostRecentScan;

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

    public List<Port> getPorts() {
        return ports;
    }

    public String getMostRecentScan() {
        return mostRecentScan;
    }
    public LocalDateTime getMostRecentScanAsLocalDateTime() {
        return LocalDateTime.parse(mostRecentScan);
    }

    public void setName(String name) {
        this.name = name;
    }

    public void setHostName(String hostName) {
        this.hostName = hostName;
    }

    public void setIpV4Address(String ipV4Address) {
        this.ipV4Address = ipV4Address;
    }

    public void setPorts(List<Port> ports) {
        this.ports = ports;
    }

    public void setMostRecentScan(LocalDateTime mostRecentScan) {
        this.mostRecentScan = mostRecentScan.toString();
    }

    @Override
    public String toString() {
        return "Host{" +
                "name='" + name + '\'' +
                ", hostName='" + hostName + '\'' +
                ", ipV4Address='" + ipV4Address + '\'' +
                ", ports=" + ports +
                ", mostRecentScan=" + mostRecentScan +
                '}';
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (!(o instanceof Host host)) return false;
        return getName().equals(host.getName()) && getHostName().equals(host.getHostName()) && getIpV4Address().equals(host.getIpV4Address()) && getPorts().equals(host.getPorts()) && getMostRecentScan().equals(host.getMostRecentScan());
    }

    @Override
    public int hashCode() {
        return Objects.hash(getName(), getHostName(), getIpV4Address(), getPorts(), getMostRecentScan());
    }
}
