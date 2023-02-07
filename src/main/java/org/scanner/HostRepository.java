package org.scanner;

import com.google.gson.GsonBuilder;
import com.google.gson.reflect.TypeToken;
import org.scanner.hostdata.Host;
import com.google.gson.Gson;
import org.scanner.hostdata.Port;
import org.scanner.hostdata.Status;


import java.io.*;

import java.util.ArrayList;
import java.util.List;

/**
 * Class that interacts manages Hosts and interacts with local JSON files
 */
public class HostRepository {
    private List<Host> hosts = new ArrayList<>();
    private static final String HOST_JSON = "src/main/resources/hosts.json";

    /**
     * No argument constructor that initializes hosts list with available JSON data
     */
    public HostRepository() {
        Host testHost = new Host("localhost", "localhost", "127.0.0.1");
        this.hosts.add(testHost);
        this.hosts = loadJson();
    }

    public void addHost(Host host) {
        this.hosts.add(host);
        updateJson();
    }

    public List<Host> getHosts() {
        return this.hosts;
    }

    public boolean removeHost(Host host) {
        for (int i = 0; i < hosts.size(); i++) {
            if (!(host.getHostName().equals(hosts.get(i).getHostName()) && host.getName().equals(hosts.get(i).getName()))) {
                hosts.remove(i);
                System.out.printf("Host %s has been removed", host.getName());
                return true;
            }
        }
        System.out.println("Host not in list and therefore not removed.");
        return false;
    }

    /**
     * Looks for provided host in current hosts lists and replaces it.
     * @param host - to be updated or added if non-existent in hosts list.
     */
    public void updateHost(Host host) {
        for (int i = 0; i < hosts.size(); i++) {
            if (!(host.getHostName().equals(hosts.get(i).getHostName()) && host.getName().equals(hosts.get(i).getName()))) {
                hosts.set(i, host);
                System.out.println("Host updated");
                return;
            }
        }
        addHost(host);
        System.out.println("New Host Added");
    }

    public void printOpenPort (Host host) {
        for (Port port : host.getPorts()) {
            if (port.getStatus().equals(Status.OPEN)) {
                System.out.println("OPEN PORT!");
                System.out.println(port);
            }
        }
    }

    public void printOpenPorts() {
        for (Host host : hosts) {
            System.out.println("Checking open ports for " + host);
            printOpenPort(host);
        }
    }

    /**
     * Updates the local JSON file with current list of hosts
     * @return - True if action was successful
     */
    public boolean updateJson() {
        Gson gson = new GsonBuilder().setPrettyPrinting().create();
        try (BufferedWriter writer = new BufferedWriter(new FileWriter(HOST_JSON))) {
//            String json = new Gson().toJson(hosts);
//            writer.write(json);
            gson.toJson(hosts, writer);
            writer.close();
            return true;
        } catch (IOException e) {
            System.out.println(e);
            return false;
        }
    }

    /**
     * Read Hosts JSON file and return it as a List.
     * @return - List of hosts written to local file
     */
    public List<Host> loadJson() {
        Gson gson = new Gson();

        try (Reader reader = new FileReader(HOST_JSON)) {
            List<Host> hosts = gson.fromJson(reader, new TypeToken<List<Host>>() {}.getType());
            if (hosts == null) {
                System.out.println("Json file empty. Returning empty List");
                return new ArrayList<>();
            }
            return hosts;
        } catch (IOException e) {
            System.out.println(e);
        }
        System.out.println("Json file empty. Returning empty List");
        return new ArrayList<>();
    }

    public List<Host> printHostSelection() {
        List<Host> currentHosts = getHosts();
        for (int i = 0; i < currentHosts.size(); i++) {
            System.out.println(i + " - " + currentHosts.get(i).toString());
        }
        return currentHosts;
    }
}
