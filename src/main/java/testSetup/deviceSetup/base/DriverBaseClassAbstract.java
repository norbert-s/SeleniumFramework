package testSetup.deviceSetup.base;

import lombok.extern.slf4j.Slf4j;
import org.openqa.selenium.WebDriver;

@Slf4j
public abstract class DriverBaseClassAbstract {

    public DriverBaseClassAbstract() {
        log.info("BaseClass constructor called");
    }

    public WebDriver getDriver() {
        return DriverProvider.getDriver();
    }
}
