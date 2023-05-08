package testSetup.setters;

import org.openqa.selenium.WebDriver;

import java.time.Duration;

public class SettingUpTimeouts {
    public static void timeOutSetup(WebDriver d){
        try{
            d.manage().timeouts().implicitlyWait(Duration.ofSeconds(EnvironmentVariables.getWaitForTime_static()));
            d.manage().timeouts().pageLoadTimeout(Duration.ofSeconds(EnvironmentVariables.getWaitForTime_static()));
            d.manage().timeouts().scriptTimeout(Duration.ofSeconds(EnvironmentVariables.getWaitForTime_static()));
        }catch (Exception e){
            e.printStackTrace();
            throw (e);
        }
    }
}
