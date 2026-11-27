package testSetup.deviceSetup;


import org.openqa.selenium.chrome.ChromeOptions;
import org.openqa.selenium.logging.LogType;
import org.openqa.selenium.logging.LoggingPreferences;
import testSetup.setters.EnvironmentVariables;

import java.util.logging.Level;

public class ChromeDeviceSetup {
    public static ChromeOptions settingUpDevices() throws Exception {
        System.setProperty("webdriver.http.factory", "jdk-http-client");
        ChromeOptions chromeOptions = new ChromeOptions();
        LoggingPreferences loggingPreferences = new LoggingPreferences();
        loggingPreferences.enable(LogType.BROWSER, Level.WARNING);
        chromeOptions.setCapability("goog:loggingPrefs", loggingPreferences);
//            if (browser.toLowerCase().contains("chrome".toLowerCase(Locale.ROOT))) {
//                if(GlobalSettingsGetterMethods.isIncognitoNeeded()){
//                    //chromeOptions.addArguments("--incognito");
//                }
//                chromeOptions.addArguments("start-maximized");
//                chromeOptions.addArguments("disable-extensions");
//                chromeOptions.setHeadless(GlobalSettingsGetterMethods.getHeadless());
//            }
//            if (browser.contains("ipad")) {
//                Map<String, String> mobileEmulation = new HashMap<>();
//                mobileEmulation.put("deviceName", "iPad Pro");
//                HashMap<String, Object> prefs = new HashMap<>();
//                chromeOptions.addArguments("--incognito");
//                chromeOptions.setExperimentalOption("prefs", prefs);
//                chromeOptions.setExperimentalOption("mobileEmulation", mobileEmulation);
//                chromeOptions.setHeadless(GlobalSettingsGetterMethods.getHeadless());
//            }
        if (EnvironmentVariables.getHeadless()) {
            chromeOptions.addArguments("--headless");
        }
        chromeOptions.addArguments("--disable-gpu");
        chromeOptions.addArguments("--window-size=1920,1080");
        chromeOptions.addArguments("--no-sandbox");
        chromeOptions.addArguments("--disable-dev-shm-usage");
        chromeOptions.addArguments("--disable-software-rasterizer");
        chromeOptions.addArguments("disable-extensions");
        if (EnvironmentVariables.isIncognitoNeeded()) {
            chromeOptions.addArguments("--incognito");
        }


        return chromeOptions;
    }
}
