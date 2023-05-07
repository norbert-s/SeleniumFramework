package testSetup.setters;

import lombok.extern.slf4j.Slf4j;

@Slf4j
public class SettingsLogger {

    public static void printOutSetupInfo() {
        try {
            log.warn("webdriver instance has been created");
            log.warn("headless -> " + GlobalSettingsGetterMethods.getHeadless());
            log.warn("debug level -> " + GlobalSettingsGetterMethods.getDebugLevel());
            log.warn("waitforTime -> " + GlobalSettingsGetterMethods.getwaitForTime_static());
            log.warn("retry -> " + GlobalSettingsGetterMethods.setRetryNumberOfTimes());
            log.warn("is incognito needed -> " + GlobalSettingsGetterMethods.isIncognitoNeeded());

        } catch (Exception e) {
            e.printStackTrace();
            throw (e);
        }
    }

    public static void setupDebuggingLevel() {
        GlobalSettingsGetterMethods.setLogLevelBasedOnEnv();
    }
}
