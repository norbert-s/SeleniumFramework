package testSetup.setters;

import org.apache.logging.log4j.Level;
import org.apache.logging.log4j.core.config.Configurator;


public interface EnvironmentVariables {

    final int RETRY_FAILED = 1;

    final int WAIT_FOR_TIME = 30;

    static boolean getScreenshotOnSuccess() {
        boolean screenshotOnSuccess = Boolean.parseBoolean(System.getProperty(Settings.SCREENSHOT_ON_SUCCESS, String.valueOf(Boolean.FALSE)));
        if (screenshotOnSuccess == true || screenshotOnSuccess == false) return screenshotOnSuccess;
        else return false;
    }

    static String getEmailTo() {
        return System.getProperty(Settings.EMAIL_TO);
    }

    default int getWaitForTime() {
        Integer waitForTimeEnv = Integer.parseInt(System.getProperty(Settings.WAIT_FOR_TIME));
        if (waitForTimeEnv == null) return WAIT_FOR_TIME;
        else if (waitForTimeEnv >= 30 && waitForTimeEnv <= 120) {
            return waitForTimeEnv;
        } else return WAIT_FOR_TIME;
    }

    static String getWaitForTime_static() {
        return System.getProperty(Settings.WAIT_FOR_TIME, String.valueOf(WAIT_FOR_TIME));
    }

    static String getEnvironment() {
        return System.getProperty(Settings.ENVIRONMENT);
    }

    static String getEnvironment_static() {
        return System.getProperty(Settings.ENVIRONMENT);
    }

    static boolean getHeadless() {
        return Boolean.parseBoolean(System.getProperty(Settings.HEADLESS, "true"));
    }

    static void setLogLevelBasedOnEnv() {
        String debugLevel = System.getProperty(Settings.DEBUG_LEVEL, Settings.ERROR);
        if (debugLevel.equals(Settings.DEBUG)) {
            Configurator.setRootLevel(Level.DEBUG);
        } else if (debugLevel.equals(Settings.WARN)) {
            Configurator.setRootLevel(Level.WARN);
        }
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
        String debugLevel = System.getProperty("debuglevel");
        if (System.getProperty("debuglevel") == null) return "error";
        else if (debugLevel.contains("debug")) {
            return "debug";
        } else if (debugLevel.contains("warn")) {
            return "warn";
        } else if (debugLevel.contains("error")) {
            return "error";
        } else {
            return "error";
        }
    }

    static boolean isIncognitoNeeded() {
        boolean incognitoNeeded = Boolean.valueOf(System.getProperty(Settings.INCOGNITO_NEEDED, String.valueOf(Boolean.FALSE)));
        if (incognitoNeeded || incognitoNeeded == false) return incognitoNeeded;
        else return Boolean.FALSE;
    }

    static boolean isDockerNeeded() {
        return Boolean.parseBoolean(System.getProperty(Settings.DOCKER_NEEDED, String.valueOf(Boolean.FALSE)));
    }
}