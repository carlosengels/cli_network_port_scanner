package unit;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;
import org.scanner.utils.LocalSettings;
import org.scanner.utils.Settings;

import java.io.IOException;

public class LocalSettingsTest {

    LocalSettings localSettings = new LocalSettings();

    @Test
    public void writeSettings_readSettings_readsAndWrites() {
        //GIVEN
        Settings expectedSettings = new Settings();

        //WHEN
        localSettings.writeSettings(new Settings());

        //THEN
        Assertions.assertEquals(expectedSettings, localSettings.loadSettings(),
                "Problem with either reeading or writing to local settings.");

    }

    @Test
    public void printAboutThis_printsAboutTxt() throws IOException {
        //GIVEN & THEN
        localSettings.printAboutThis();
        // WHEN
        // You can manually verify that the about.txt document has printed out correctly.
    }
}
