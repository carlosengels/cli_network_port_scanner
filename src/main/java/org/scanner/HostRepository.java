package org.scanner;

import org.scanner.port.Host;

import java.util.ArrayList;
import java.util.List;

public class HostRepository {
    private List<Host> hosts = new ArrayList<>();

    public HostRepository() {
        //TEST OBJECTS
        hosts.add(new Host("Test Profile", "https://carlosengels.com/", "127.0.0.1"));
        hosts.add(new Host("Another test profile", "https://carlosengels.com/", "127.0.0.1"));
    }

    public List<Host> getHosts() {
        return hosts;
    }

    @Override
    public String toString() {
        return "HostRepository{" +
                "hosts=" + hosts +
                '}';
    }
}
