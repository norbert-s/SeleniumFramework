package testSetup.setters;

import org.openqa.selenium.WebDriver;

import java.time.Duration;

public class SettingUpTimeouts {
    public static void timeOutSetup(WebDriver d) {
        try {
            Duration timeOut = Duration.ofSeconds(Integer.valueOf(System.getProperty(Settings.WAIT_FOR_TIME)));
            d.manage().timeouts().pageLoadTimeout(timeOut);
            d.manage().timeouts().scriptTimeout(timeOut);
        } catch (Exception e) {
            e.printStackTrace();
            throw (e);
        }
    }
}
