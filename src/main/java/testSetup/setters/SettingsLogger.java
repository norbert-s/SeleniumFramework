package testSetup.setters;

import lombok.extern.slf4j.Slf4j;

@Slf4j
public class SettingsLogger {

    public static void printOutSetupInfo() {
        log.info("WebDriver configuration: environment={}, headless={}, logLevel={}, waitTimeout={}s, retry={}, incognito={}",
                EnvironmentVariables.getEnvironment(),
                EnvironmentVariables.getHeadless(),
                EnvironmentVariables.getDebugLevel(),
                EnvironmentVariables.getWaitForTime_static(),
                EnvironmentVariables.setRetryNumberOfTimes(),
                EnvironmentVariables.isIncognitoNeeded());
    }
}
