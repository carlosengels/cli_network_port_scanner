package org.scanner.utils;

public class DataEntryValidator {

    private final static String IP_REGEX = "^\\d{1,3}\\.\\d{1,3}\\.\\d{1,3}\\.\\d{1,3}$";
    private final static String HOSTNAME_REGEX = ".*\\.\\w*$";

    public static boolean validateIPv4(String target) {
        return target.matches(IP_REGEX);
    }

    public static boolean validateHostName(String target) {
        return target.matches(HOSTNAME_REGEX);
    }
}
