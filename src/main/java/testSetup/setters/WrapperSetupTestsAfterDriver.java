package testSetup.setters;

import org.openqa.selenium.WebDriver;
import testSetup.configreader.ConfigReader;

public class WrapperSetupTestsAfterDriver {
    public static void initializeAttributes(WebDriver driver) {
        SettingUpTimeouts.timeOutSetup(driver);
    }
}
