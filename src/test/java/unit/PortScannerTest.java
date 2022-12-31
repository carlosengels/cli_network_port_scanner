package unit;

import org.junit.jupiter.api.Test;
import org.scanner.PortScanner;
import org.scanner.port.Host;
import org.scanner.port.Port;

import java.time.LocalDateTime;
import java.util.List;

import static org.junit.jupiter.api.Assertions.*;

public class PortScannerTest {

    int validTimeout = 1000;
    int validPortRange[] = {1,500};

    @Test
    public void scanIP_localAddress_returnsListOfCorrectSize() {
        //GIVEN
        PortScanner portScanner = new PortScanner();

        //WHEN
        List<Port> result = portScanner.scanIP("127.0.0.1", validTimeout, validPortRange[0], validPortRange[1]);

        //THEN
        assertNotNull(result);
        assertEquals(result.size(), (validPortRange[1]),
                "List returned by scanIP() was not the same size as the amount of ports scanned.");
    }

    @Test
    public void scanHost_validData_updatesMostRecentScan() {
        //GIVEN
        Host host = new Host("Test Profile", "localhost", "127.0.0.1");
        PortScanner portScanner = new PortScanner();


        //WHEN
        portScanner.scanHost(host, validTimeout, validPortRange[0], validPortRange[1]);

        //THEN
        assertEquals(host.getMostRecentScanAsLocalDateTime().getHour(), LocalDateTime.now().getHour());
    }
}
