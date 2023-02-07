package unit;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;
import org.scanner.utils.DataEntryValidator;

public class DataEntryValidatorTest {

    @Test
    public void validateIPv4_validIP_returnsTrue() {
        Assertions.assertTrue(DataEntryValidator.validateIPv4("127.0.0.1"));
    }

    @Test
    public void validateIPv4_notValidIP_returnsFalse() {
        Assertions.assertFalse(DataEntryValidator.validateIPv4("10.0.1"));
        Assertions.assertFalse(DataEntryValidator.validateIPv4("banana"));

    }

    @Test
    public void validateIPv4_validURL_returnsTrue() {
        Assertions.assertTrue(DataEntryValidator.validateHostName("carlosengels.com"));

    }

    @Test
    public void validateIPv4_notValidURL_returnsFalse() {
        Assertions.assertFalse(DataEntryValidator.validateHostName("!@#$%^"));
    }
}
