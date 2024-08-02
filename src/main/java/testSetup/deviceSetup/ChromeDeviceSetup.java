package testSetup.deviceSetup;


import org.openqa.selenium.chrome.ChromeOptions;
import testSetup.setters.EnvironmentVariables;

public class ChromeDeviceSetup {
    private static ChromeOptions chromeOptions;


    public static ChromeOptions settingUpDevices() throws Exception {
        System.setProperty("webdriver.http.factory", "jdk-http-client");
        chromeOptions = new ChromeOptions();
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

