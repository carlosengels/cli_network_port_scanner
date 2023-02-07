package unit;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.scanner.HostRepository;
import org.scanner.port.Host;

import java.util.List;

public class HostRepositoryTest {
    Host localHost = new Host("Test Profile", "localhost", "127.0.0.1");
    Host upDatedLocalHost = new Host("Updated Test Profile", "localhost", "127.0.0.1");

    Host remoteHost = new Host("My Website", "https://carlosengels.com/", "104.200.17.209");
    HostRepository hostRepository = new HostRepository();
    @BeforeEach
    void init() {
        hostRepository.removeHost(localHost);
        hostRepository.removeHost(remoteHost);

        hostRepository.addHost(localHost);
        hostRepository.addHost(remoteHost);
    }

    @Test
    public void addHost_validHost_addsNewHost() {
        //GIVEN & WHEN

        //THEN
        Assertions.assertEquals(2, hostRepository.getHosts().size(), "Adding new hosts did not work as expected");
        Assertions.assertEquals("localhost", hostRepository.getHosts().get(0).getHostName(), "Adding new hosts did not work as expected");

    }

    @Test
    public void updateHost_hostExists_updateHostVariables() {
        //GIVEN

        //WHEN
        hostRepository.updateHost(upDatedLocalHost);

        //THEN
        Assertions.assertEquals("Updated Test Profile", hostRepository.getHosts().get(0).getName(),
                "Name variable was not properly updated.");
        hostRepository.removeHost(localHost);
        hostRepository.removeHost(remoteHost);
    }

        //TODO Pick up here to complete JSON implementation
    @Test void updateJson_updatesJsonFile() {
        //GIVEN

        //WHEN
        boolean result = hostRepository.updateJson();

        //THEN
        Assertions.assertTrue(result);

    }

    //TODO make test more thoughtful
    @Test void loadJson_loadsCorrectJsonFile() {
        //GIVEN

        //WHEN
        List<Host> result = hostRepository.loadJson();

        //THEN
        Assertions.assertEquals(result.size(), hostRepository.getHosts().size());
    }
}
