package pageObjectClasses.abstracts;

import org.openqa.selenium.By;
import org.openqa.selenium.NoSuchElementException;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.ui.ExpectedCondition;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.FluentWait;
import org.openqa.selenium.support.ui.Wait;
import testSetup.setters.EnvironmentVariables;

import java.io.IOException;
import java.time.Duration;

import static org.testng.AssertJUnit.assertNotNull;

public abstract class PageObjectBaseMethods extends PageObjectBasePage implements EnvironmentVariables {

    public PageObjectBaseMethods(WebDriver driver) throws IOException {
        super(driver);
    }

    public Wait<WebDriver> returnWait() {
        return new FluentWait<WebDriver>(driver)
                .pollingEvery(Duration.ofMillis(100))
                .ignoring(NoSuchElementException.class)
                .withTimeout(Duration.ofSeconds(getWaitForTime()));
    }

    public Wait<WebDriver> returnWait(long timeOut) {
        return new FluentWait<WebDriver>(driver)
                .pollingEvery(Duration.ofMillis(100))
                .ignoring(NoSuchElementException.class)
                .withTimeout(Duration.ofSeconds(timeOut));
    }

    public WebElement convertByToWebElement(By locator) {
        return driver.findElement(locator);
    }

    public void fluentWaitWithExpectedCondition(ExpectedCondition<WebElement> expectedCondition) {
        assertNotNull(new Object() {
        }.getClass().getEnclosingMethod().getName() + " did not succeed " + expectedCondition, returnWait().until(expectedCondition));
    }

    protected void fluentWaitWithExpectedCondition(ExpectedCondition<WebElement> expectedCondition, long timeOut) {
        assertNotNull(new Object() {
        }.getClass().getEnclosingMethod().getName() + " did not succeed " + expectedCondition, returnWait(timeOut).until(expectedCondition));
    }

    protected void waitForAndClick(WebElement element) throws Exception {
        fluentWaitWithExpectedCondition(ExpectedConditions.elementToBeClickable(element));
        if (element instanceof By) {
            driver.findElement((By) element).click();
        }
        element.click();
    }

}
