package pageObjectClasses.abstracts;


import org.openqa.selenium.WebDriver;

import java.io.IOException;


public abstract class PageObjectBasePage {
    protected WebDriver d;
    public PageObjectBasePage(WebDriver driver) throws IOException {
        this.d = driver;
    }
}
