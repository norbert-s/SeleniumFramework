package testSetup.deviceSetup.base;


import org.openqa.selenium.WebDriver;

import java.io.IOException;


public class BasePage {
    protected WebDriver d;
    public BasePage(WebDriver driver) throws IOException {
        this.d = driver;
    }
}
