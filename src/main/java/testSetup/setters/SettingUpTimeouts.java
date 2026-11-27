package testSetup.setters;

import org.openqa.selenium.WebDriver;
import testSetup.wait.WaitConfiguration;

public class SettingUpTimeouts {
    public static void timeOutSetup(WebDriver d) {
        java.time.Duration timeout = WaitConfiguration.fromSystemProperties().timeout();
        d.manage().timeouts().pageLoadTimeout(timeout);
        d.manage().timeouts().scriptTimeout(timeout);
    }
}
