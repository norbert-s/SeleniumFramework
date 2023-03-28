package utilityClasses.testSetup.deviceSetup;


import io.github.bonigarcia.wdm.WebDriverManager;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.chrome.ChromeDriverService;
import org.openqa.selenium.chrome.ChromeOptions;
import org.openqa.selenium.remote.RemoteWebDriver;
import utilityClasses.testSetup.configreader.ConfigReader;
import utilityClasses.testSetup.setters.GlobalSettingsGetterMethods;

import java.io.File;

public class ChromeDeviceSetup {
    public static ChromeDriverService service;
    private static ChromeOptions chromeOptions;


    public  static ChromeOptions settingUpDevices() throws Exception {
        System.setProperty("webdriver.http.factory", "jdk-http-client");
        chromeOptions =new ChromeOptions();
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
        if(GlobalSettingsGetterMethods.getHeadless()){
            chromeOptions.addArguments("--disable-gpu");
            chromeOptions.addArguments("--window-size=1920,1080");
            chromeOptions.addArguments("--no-sandbox");
            chromeOptions.addArguments("--disable-dev-shm-usage");
            chromeOptions.addArguments("--disable-software-rasterizer");
            chromeOptions.addArguments("--headless");
        }else{
            chromeOptions.addArguments("start-maximized");


        }
        chromeOptions.addArguments("disable-extensions");
        if(GlobalSettingsGetterMethods.isIncognitoNeeded()){
            chromeOptions.addArguments("--incognito");
        }


        return chromeOptions;
    }


    public static ChromeDriverService driverServiceFactory() throws Exception {
        ChromeDriverService service = new ChromeDriverService.Builder()
                .usingDriverExecutable(new File(ConfigReader.getProps().getProperty("chromedriver")))
                .usingAnyFreePort()
                .build();
        return service;
    }

    public static WebDriver driverBuilder() throws Exception {
        chromeOptions = settingUpDevices();
        WebDriverManager wdm = WebDriverManager.chromedriver();
        WebDriver d;
        wdm = WebDriverManager.chromedriver().capabilities(chromeOptions);
        d = wdm.create();
        return d;
    }
}

