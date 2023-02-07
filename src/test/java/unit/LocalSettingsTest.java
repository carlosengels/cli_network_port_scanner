package unit;

import org.junit.jupiter.api.Test;
import org.scanner.utils.LocalSettings;

import java.io.IOException;

public class LocalSettingsTest {

    LocalSettings localSettings = new LocalSettings();

    @Test
    public void printAboutThis_printsREADME() throws IOException {
        localSettings.printAboutThis();
    }
}
