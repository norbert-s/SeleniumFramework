package pageObjectClasses.abstracts;

import lombok.extern.slf4j.Slf4j;
import org.openqa.selenium.*;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.Wait;
import testSetup.wait.WaitConfiguration;
import testSetup.wait.WaitService;

@Slf4j
public abstract class PageObjectGenericMethodsImpl extends PageObjectBasePage {
    private final WaitService waitService;

    protected PageObjectGenericMethodsImpl(WebDriver driver) {
        this(driver, WaitConfiguration.fromSystemProperties());
    }

    protected PageObjectGenericMethodsImpl(WebDriver driver, WaitConfiguration waitConfiguration) {
        super(driver);
        this.waitService = new WaitService(driver, waitConfiguration);
    }

    protected Wait<WebDriver> returnWait() {
        return waitService.returnWait();
    }

    protected Wait<WebDriver> returnWait(long timeoutSeconds) {
        return waitService.returnWait(timeoutSeconds);
    }

    public PageObjectGenericMethodsImpl waitForElementToBeClickable(By element) {
        returnWait().until(ExpectedConditions.elementToBeClickable(element));
        return this;
    }

    public PageObjectGenericMethodsImpl waitForElementToBePresent(By element) {
        returnWait().until(ExpectedConditions.presenceOfElementLocated(element));
        return this;
    }

    public PageObjectGenericMethodsImpl waitForElementToBePresent(By element, int time) {
        returnWait(time).until(ExpectedConditions.presenceOfElementLocated(element));
        return this;
    }

    public PageObjectGenericMethodsImpl waitForVisibilityOfWebElement(WebElement element) {
        returnWait().until(ExpectedConditions.visibilityOf(element));
        return this;
    }

    public PageObjectGenericMethodsImpl waitForAndClick(By element) {
        waitForElementToBeClickable(element);
        driver.findElement(element).click();
        return this;
    }

    public PageObjectGenericMethodsImpl waitForAndClick(WebElement element) {
        returnWait().until(ExpectedConditions.elementToBeClickable(element));
        element.click();
        return this;
    }

    public PageObjectGenericMethodsImpl enterTextToSearchForm(By element, String text) {
        try {
            waitForElementToBePresent(element);
            clearText(element);
            driver.findElement(element).sendKeys(text + Keys.ENTER);
        } catch (Exception e) {
            log.error("Entering text into search form failed for locator {}", element, e);
            throw e;
        }
        return this;
    }

    public PageObjectGenericMethodsImpl clearText(By element) {
        waitForAndClick(element);
        driver.findElement(element).clear();
        return this;
    }

    public String fluentWaitWithExpectedConditionToReturnText(By locator) {
        return returnWait().until(ExpectedConditions.presenceOfElementLocated(locator)).getText();
    }

    public PageObjectGenericMethodsImpl fluentWaitForAllElementsPresence(By locator) {
        returnWait().until(ExpectedConditions.presenceOfAllElementsLocatedBy(locator));
        return this;
    }

    public PageObjectGenericMethodsImpl waitForPageToLoadCompletely() {
        try {
            returnWait().until(driver_here -> String
                    .valueOf(((JavascriptExecutor) driver_here).executeScript("return document.readyState"))
                    .equals("complete"));
        } catch (Exception e) {
            log.error("Waiting for page load completion failed", e);
            throw e;
        }
        return this;
    }

    public void clickElementUsingJavaScript(WebElement element) {
        JavascriptExecutor js = (JavascriptExecutor) driver;
        js.executeScript("arguments[0].click();", element);
    }

    public void refreshPage() {
        JavascriptExecutor js = (JavascriptExecutor) driver;
        js.executeScript("location.reload();");
    }

}
