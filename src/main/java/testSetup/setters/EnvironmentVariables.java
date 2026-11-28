package testSetup.setters;

import testSetup.wait.WaitConfiguration;


public interface EnvironmentVariables {

    final int RETRY_FAILED = 1;

    final int WAIT_FOR_TIME = 30;

    default int getWaitForTime() {
        return Math.toIntExact(WaitConfiguration.fromSystemProperties().timeout().toSeconds());
    }

    static String getWaitForTime_static() {
        return String.valueOf(WaitConfiguration.fromSystemProperties().timeout().toSeconds());
    }

    static String getEnvironment() {
        return System.getProperty(Settings.ENVIRONMENT);
    }

    static boolean getHeadless() {
        return Boolean.parseBoolean(System.getProperty(Settings.HEADLESS, "true"));
    }

    static boolean isScreenshotOnSuccess() {
        return Boolean.parseBoolean(System.getProperty(Settings.SCREENSHOT_ON_SUCCESS, "false"));
    }

    static int setRetryNumberOfTimes() {
        int retry = Integer.valueOf(System.getProperty(Settings.RETRY_FAILED, String.valueOf(RETRY_FAILED)));
        if (retry >= 0 && retry <= 3) {
            return retry;
        } else {
            return RETRY_FAILED;
        }
    }

    static String getDebugLevel() {
        return System.getProperty(Settings.DEBUG_LEVEL, "INFO").toUpperCase(java.util.Locale.ROOT);
    }

    static boolean isIncognitoNeeded() {
        boolean incognitoNeeded = Boolean.valueOf(System.getProperty(Settings.INCOGNITO_NEEDED, String.valueOf(Boolean.FALSE)));
        if (incognitoNeeded || incognitoNeeded == false) return incognitoNeeded;
        else return Boolean.FALSE;
    }

    static boolean isDockerNeeded() {
        return Boolean.parseBoolean(System.getProperty(Settings.DOCKER_NEEDED, String.valueOf(Boolean.TRUE)));
    }
}