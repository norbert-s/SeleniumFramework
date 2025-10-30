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

    public void waitForElementToBeClickable(By element) {
        fluentWaitWithExpectedCondition(ExpectedConditions.elementToBeClickable(element));
    }

    public void waitForElementToBePresent(By element) {
        fluentWaitWithExpectedCondition(ExpectedConditions.presenceOfElementLocated(element));
    }

    public void waitForElementToBePresent(By element, int time) {
        fluentWaitWithExpectedCondition(ExpectedConditions.presenceOfElementLocated(element), time);
    }

    public void waitForVisibilityOfWebElement(WebElement element) {
        fluentWaitWithExpectedCondition(ExpectedConditions.visibilityOf(element));
    }

    public void waitForAndClick(By element) {
        waitForElementToBeClickable(element);
        convertByToWebElement(element).click();
    }


    public void waitForAndClick(WebElement element)  {
        fluentWaitWithExpectedCondition(ExpectedConditions.elementToBeClickable(element));
        element.click();
    }


    public <T> T enterTextToSearchForm(By element, String text) {
        try {

            waitForElementToBePresent(element);
            clearText(element);
            driver.findElement(element).sendKeys(text + Keys.ENTER);
            //d.findElement(element).sendKeys(Keys.RETURN);
        } catch (Exception e) {
            log.error("entering text into search form  did not succeed " + element + " " + text + " ");
            throw (e);
        }
        return (T) this;
    }

    public void clearText(By element) {
        waitForAndClick(element);
        driver.findElement(element).clear();
    }

    public String fluentWaitWithExpectedConditionToReturnText(By locator) {
        return returnWait().until(ExpectedConditions.presenceOfElementLocated(locator)).getText();
    }

    public void fluentWaitForAllElementsPresence(By locator) {
        returnWait().until(ExpectedConditions.presenceOfAllElementsLocatedBy(locator));
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
