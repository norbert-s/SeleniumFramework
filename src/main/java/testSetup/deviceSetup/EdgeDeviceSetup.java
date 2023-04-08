package testSetup.deviceSetup;


import io.github.bonigarcia.wdm.WebDriverManager;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.edge.EdgeOptions;
import testSetup.setters.GlobalSettingsGetterMethods;

public class EdgeDeviceSetup {
    public static WebDriver driverBuilder() throws Exception {
        EdgeOptions edgeOptions = new EdgeOptions();

        if(GlobalSettingsGetterMethods.getHeadless()){
            edgeOptions.addArguments("--headless");
        }

        edgeOptions.addArguments("disable-gpu", "window-size=1920,1200","ignore-certificate-errors", "disable-extensions", "disable-dev-shm-usage", "no-sandbox");
        WebDriverManager wdm = WebDriverManager.edgedriver().capabilities(edgeOptions);
        WebDriver driver = wdm.create();
        return driver;
    }
}



