package testSetup.setters;

import lombok.extern.slf4j.Slf4j;

@Slf4j
public class SettingsLogger {

    public static void printOutSetupInfo() {
        try {

            log.warn("webdriver instance has been created");
            log.warn("environment -> " + EnvironmentVariables.getEnvironment());
            log.warn("headless -> " + EnvironmentVariables.getHeadless());
            log.warn("debug level -> " + EnvironmentVariables.getDebugLevel());
            log.warn("waitforTime -> " + EnvironmentVariables.getWaitForTime_static());
            log.warn("retry -> " + EnvironmentVariables.setRetryNumberOfTimes());
            log.warn("is incognito needed -> " + EnvironmentVariables.isIncognitoNeeded());

        } catch (Exception e) {
            e.printStackTrace();
            throw (e);
        }
    }

    public static void setupDebuggingLevel() {
        EnvironmentVariables.setLogLevelBasedOnEnv();
    }
}
