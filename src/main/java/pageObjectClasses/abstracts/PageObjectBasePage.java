package pageObjectClasses.abstracts;

import org.openqa.selenium.WebDriver;

public abstract class PageObjectBasePage {
    protected final WebDriver driver;

    protected PageObjectBasePage(WebDriver driver) {
        this.driver = java.util.Objects.requireNonNull(driver, "WebDriver must not be null");
    }
}
