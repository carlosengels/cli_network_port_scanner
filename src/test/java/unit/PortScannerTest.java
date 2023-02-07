package unit;

import org.junit.jupiter.api.Test;
import org.scanner.PortScanner;
import org.scanner.hostdata.Host;
import org.scanner.hostdata.Port;

import java.time.LocalDateTime;
import java.util.List;

import static org.junit.jupiter.api.Assertions.*;

public class PortScannerTest {


    @Test
    public void scanIP_localAddress_returnsListOfCorrectSize() {
        //GIVEN
        PortScanner portScanner = new PortScanner();

        //WHEN
        List<Port> result = portScanner.scanIP("127.0.0.1");

        //THEN
        assertNotNull(result);
        assertEquals(result.size() - 1, (portScanner.getEndingPort() - portScanner.getStartingPort()),
                "List returned by scanIP() was not the same size as the amount of ports scanned.");
    }

    @Test
    public void scanHost_validData_updatesMostRecentScan() {
        //GIVEN
        Host host = new Host("Test Profile", "localhost", "127.0.0.1");
        PortScanner portScanner = new PortScanner();


        //WHEN
        portScanner.scanHost(host);

        //THEN
        assertEquals(host.getMostRecentScanAsLocalDateTime().getHour(), LocalDateTime.now().getHour());
    }
}
