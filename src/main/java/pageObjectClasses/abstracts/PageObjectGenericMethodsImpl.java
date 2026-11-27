package pageObjectClasses.abstracts;

import lombok.extern.slf4j.Slf4j;
import org.openqa.selenium.*;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.Wait;
import testSetup.wait.WaitConfiguration;
import testSetup.wait.WaitService;

import java.util.concurrent.atomic.AtomicReference;

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
        WebElement target = returnWait().until(ExpectedConditions.presenceOfElementLocated(element));
        ((JavascriptExecutor) driver).executeScript(
                "arguments[0].scrollIntoView({block: 'center', inline: 'center'});", target);
        AtomicReference<String> blocker = new AtomicReference<>("unknown");
        try {
            returnWait().until(currentDriver -> {
                try {
                    WebElement currentTarget = currentDriver.findElement(element);
                    if (!currentTarget.isDisplayed() || !currentTarget.isEnabled()) {
                        return false;
                    }
                    String obstruction = (String) ((JavascriptExecutor) currentDriver).executeScript(
                            "const target = arguments[0];"
                                    + "const rect = target.getBoundingClientRect();"
                                    + "if (rect.width === 0 || rect.height === 0) return 'target has no visible area';"
                                    + "const hit = document.elementFromPoint("
                                    + "rect.left + rect.width / 2, rect.top + rect.height / 2);"
                                    + "if (hit === target || target.contains(hit)) return '';"
                                    + "if (!hit) return 'no element at target center';"
                                    + "const modal = hit.closest('[aria-modal=true]');"
                                    + "return JSON.stringify({tag: hit.tagName, id: hit.id, "
                                    + "className: (typeof hit.className === 'string' ? hit.className : '').slice(0, 160), "
                                    + "dataTestId: hit.getAttribute('data-testid'), "
                                    + "blocksWithAppLoader: Boolean(modal && modal.querySelector('[data-testid=app-loader]'))});",
                            currentTarget);
                    if (obstruction.isEmpty()) {
                        return true;
                    }
                    blocker.set(obstruction);
                    return false;
                } catch (StaleElementReferenceException e) {
                    return false;
                }
            });
        } catch (TimeoutException timeout) {
            throw new TimeoutException(
                    "Timed out waiting for " + element + " to be unobstructed; blocker: " + blocker.get(),
                    timeout);
        }
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

    public PageObjectGenericMethodsImpl waitForElementToDisappear(By element) {
        returnWait().until(ExpectedConditions.invisibilityOfElementLocated(element));
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
