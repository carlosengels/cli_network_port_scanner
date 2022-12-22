package org.scanner;

import org.scanner.port.Host;

import java.util.ArrayList;
import java.util.List;

/**
 * Class that interacts manages Hosts and interacts with local JSON files
 */
public class HostRepository {
    private List<Host> hosts = new ArrayList<>();

    public HostRepository() {
    }

    public void updateHost(Host host) {
        //look in list if host exists
        for (int i = 0; i < hosts.size(); i++) {
            if (!(host.getHostName().equals(hosts.get(i).getHostName()) && host.getName().equals(hosts.get(i).getName()))) {
                hosts.set(i, host);
            } else addHost(host);
            System.out.println("New Host Added");
        }
    }

    public void addHost(Host host) {
        hosts.add(host);
    }

    public List<Host> getHosts() {
        return hosts;
    }

    public boolean updateJson() {
        return false;
    }

    public List<Host> loadJson() {
        return new ArrayList<>();
    }
}
