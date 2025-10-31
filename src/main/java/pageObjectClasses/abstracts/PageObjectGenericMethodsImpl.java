package pageObjectClasses.abstracts;

import lombok.extern.slf4j.Slf4j;
import org.openqa.selenium.*;
import org.openqa.selenium.support.ui.ExpectedConditions;
import testSetup.setters.EnvironmentVariables;

import java.io.IOException;

@Slf4j
public abstract class PageObjectGenericMethodsImpl extends PageObjectBaseMethods implements EnvironmentVariables, IPageObjectGenericMethods {

    public PageObjectGenericMethodsImpl(WebDriver driver) throws IOException {
        super(driver);

    }

    public IPageObjectGenericMethods waitForElementToBeClickable(By element) {
        fluentWaitWithExpectedCondition(ExpectedConditions.elementToBeClickable(element));
        return this;
    }

    public IPageObjectGenericMethods waitForElementToBePresent(By element) {
        fluentWaitWithExpectedCondition(ExpectedConditions.presenceOfElementLocated(element));
        return this;
    }

    public IPageObjectGenericMethods waitForElementToBePresent(By element, int time) {
        fluentWaitWithExpectedCondition(ExpectedConditions.presenceOfElementLocated(element), time);
        return this;
    }

    public IPageObjectGenericMethods waitForVisibilityOfWebElement(WebElement element) {
        fluentWaitWithExpectedCondition(ExpectedConditions.visibilityOf(element));
        return this;
    }

    public IPageObjectGenericMethods waitForAndClick(By element) {
        waitForElementToBeClickable(element);
        convertByToWebElement(element).click();
        return this;
    }

    public IPageObjectGenericMethods waitForAndClick(WebElement element)  {
        fluentWaitWithExpectedCondition(ExpectedConditions.elementToBeClickable(element));
        element.click();
        return this;
    }

    public <T> T enterTextToSearchForm(By element, String text) {
        try {

            waitForElementToBePresent(element);
            clearText(element);
            driver.findElement(element).sendKeys(text + Keys.ENTER);
        } catch (Exception e) {
            log.error("entering text into search form  did not succeed " + element + " " + text + " ");
            throw (e);
        }
        return (T) this;
    }

    public IPageObjectGenericMethods clearText(By element) {
        waitForAndClick(element);
        driver.findElement(element).clear();
        return this;
    }

    public String fluentWaitWithExpectedConditionToReturnText(By locator) {
        return returnWait().until(ExpectedConditions.presenceOfElementLocated(locator)).getText();
    }

    public IPageObjectGenericMethods fluentWaitForAllElementsPresence(By locator) {
        returnWait().until(ExpectedConditions.presenceOfAllElementsLocatedBy(locator));
        return this;
    }

    public <T> T waitForPageToLoadCompletely() {
        try {
            returnWait().until(driver_here -> String
                    .valueOf(((JavascriptExecutor) driver_here).executeScript("return document.readyState"))
                    .equals("complete"));
        } catch (Exception e) {
            log.error("wait for page to complete - did not succeed ");
            throw (e);
        }
        return (T) this;
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
