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
    private static final String HOST_JSON = "src/main/resources/hosts.txt";

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
            //Initializing writer
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
     * Read local JSON file and replaces current host list
     * @return - List of hosts written to local file
     */
    public List<Host> loadJson() {
        //TODO FIX implementation
//        try {
//            BufferedReader reader = new BufferedReader(new FileReader(HOST_JSON));
//            String json = reader;
//            Gson gson = new Gson();
//
//
//            return savedHosts;
//        } catch (IOException e) {
//            System.out.println("Error reading the local Json file");
//            return new ArrayList<>();
//        }
    }
}
