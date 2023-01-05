package org.scanner;

import com.google.gson.reflect.TypeToken;
import org.scanner.port.Host;
import com.google.gson.Gson;
import com.google.gson.GsonBuilder;


import java.lang.reflect.Type;
import java.time.*;
import java.io.*;
import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;

/**
 * Class that interacts manages Hosts and interacts with local JSON files
 */
public class HostRepository {
    private List<Host> hosts = new ArrayList<>();
    private static final String HOST_JSON = "src/main/resources/hosts.json";

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

    /**
     * Updates the local JSON file with current list of hosts
     * @return - True if action was successful
     */
    public boolean updateJson() {
        try {
            BufferedWriter writer = new BufferedWriter(new FileWriter(HOST_JSON));
            String json = new Gson().toJson(hosts);
            writer.write(json);
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
        } catch (IOException e) {
            System.out.println(e);
        }
        System.out.println(hosts.toString());
        return hosts;
    }
}
