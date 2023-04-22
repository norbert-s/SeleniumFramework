package pageObjectClasses.abstracts;

import lombok.extern.slf4j.Slf4j;
import org.openqa.selenium.*;
import org.openqa.selenium.interactions.Actions;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.Select;
import testSetup.setters.GlobalSettingsGetterMethods;

import java.io.IOException;

@Slf4j
public abstract class PageObjectGenericMethodsImpl extends PageObjectBaseMethods implements GlobalSettingsGetterMethods {

    public PageObjectGenericMethodsImpl(WebDriver driver) throws IOException {
        super(driver);

    }
    public void waitForAndMoveToElement(By element) {
        fluentWaitWithExpectedCondition(ExpectedConditions.presenceOfElementLocated(element));
        new Actions(driver).moveToElement(convertByToWebElement(element)).build().perform();
    }

    public void waitForElementToBeClickableBy(By element) {
        fluentWaitWithExpectedCondition(ExpectedConditions.elementToBeClickable(element));
    }

    public void selectFromDropDownByVisibleText(WebElement element, String text) {
        Select select = new Select(element);
        select.selectByVisibleText(text);
    }
    public void selectFromDropDownByindex(WebElement element, int index) {
        Select select = new Select(element);
        select.selectByIndex(index);
    }

    public void selectFromDropDownByValue(WebElement element, String value) {
        Select select = new Select(element);
        select.selectByValue(value);
    }

    public void waitForElementToBeClickable(By element) {
        fluentWaitWithExpectedCondition(ExpectedConditions.elementToBeClickable(element));
    }

    public void waitForElementToBePresent(By element) {
        fluentWaitWithExpectedCondition(ExpectedConditions.presenceOfElementLocated(element));
    }
    public void waitForElementToBePresent(By element,int time) {
        fluentWaitWithExpectedCondition(ExpectedConditions.presenceOfElementLocated(element),time);
    }

    public void waitForVisiblityOfWebelement(WebElement element) {
        fluentWaitWithExpectedCondition(ExpectedConditions.visibilityOf(element));
    }

    public void waitForElementVisiblityByElement(By element) {
        fluentWaitWithExpectedCondition(ExpectedConditions.visibilityOfElementLocated(element));
    }

    /**
     * @see #waitForAndClick(By)
     * custom method waitd for element
     * then asserts its displayed
     * and then clicks on the element
     */
    public void waitForAndClick(By element) {
        waitForElementToBeClickable(element);
        convertByToWebElement(element).click();
    }

    /**
     * @see #waitForAndClick(WebElement)
     * sometimes elements cannot be found by "By elementfactory"
     * custom method waitd for element
     * then asserts its displayed
     * and then clicks on the element
     */
    public void waitForAndClick(WebElement element) throws Exception {
        fluentWaitWithExpectedCondition(ExpectedConditions.elementToBeClickable(element));
        element.click();
    }

    public void waitForAndClick(WebElement element, int time) throws Exception {
        fluentWaitWithExpectedCondition(ExpectedConditions.elementToBeClickable(element),time);
        element.click();
    }

    /**
     * @see #enterTextToSearchForm(By, String)
     * this method should be used when the input field does not contain dropdowns or autocompletion
     * for instance when entering email address to an input field
     */
    protected <T> T enterTextToSearchForm(By element, String text) {
        try {
            clearText(element);
            waitForElementToBePresent(element);
            driver.findElement(element).sendKeys(text + Keys.ENTER);
            //d.findElement(element).sendKeys(Keys.RETURN);
        } catch (Exception e) {
            log.error("entering text into search form  did not succeed " + element + " " + text + " ");
            throw (e);
        }
        return (T) this;
    }

    public void clearText(By element) {
        waitForElementToBeClickable(element);
        driver.findElement(element).clear();
    }


    public PageObjectGenericMethodsImpl clickOnAcceptCookie(By element, int time) {
        waitForElementToBePresent(element, time);
        convertByToWebElement(element).click();
        return this;
    }

    public void clickOnNthElementInList(By element, int index) throws Exception {
        waitForAndClick(driver.findElements(element).get(index));
    }

    public void fluentWaitWithVisibilityOfElementLocated(By locator){
        returnWait().until(ExpectedConditions.visibilityOfElementLocated(locator));
    }

    public void fluentWaitWithElementToBeSelected(By locator){
        returnWait().until(ExpectedConditions.elementToBeSelected(locator));
    }

    public void fluentWaitWithInvisibilityOfElementLocated(By locator){
        returnWait().until(ExpectedConditions.invisibilityOfElementLocated(locator));
    }

    //create a method with fluentWaitWithExpectedCondition to wait for an element and return text

    public String fluentWaitWithExpectedConditionToReturnText(By locator){
        return returnWait().until(ExpectedConditions.presenceOfElementLocated(locator)).getText();
    }


    public void scrollBy(int numberOfPixels) {
        try {
            JavascriptExecutor js = (JavascriptExecutor) driver;
            String scrollByScript = "window.scrollBy(0," + numberOfPixels + ")";
            js.executeScript(scrollByScript, "");
        } catch (Exception e) {
            log.error("scrolling down  did not succeed ");
            throw (e);
        }
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

    public void scrollToElement( WebElement element) {
        JavascriptExecutor js = (JavascriptExecutor) driver;
        js.executeScript("arguments[0].scrollIntoView();", element);
    }

    public void clickElementUsingJavaScript( WebElement element) {
        JavascriptExecutor js = (JavascriptExecutor) driver;
        js.executeScript("arguments[0].click();", element);
    }


    public void setInputValueUsingJavaScript( WebElement inputElement, String value) {
        JavascriptExecutor js = (JavascriptExecutor) driver;
        js.executeScript("arguments[0].value = arguments[1];", inputElement, value);
    }

    public String getInnerTextUsingJavaScript( WebElement element) {
        JavascriptExecutor js = (JavascriptExecutor) driver;
        return (String) js.executeScript("return arguments[0].innerText;", element);
    }

    public void changeBackgroundColor( WebElement element, String color) {
        JavascriptExecutor js = (JavascriptExecutor) driver;
        js.executeScript("arguments[0].style.backgroundColor = arguments[1];", element, color);
    }

    public void refreshPage() {
        JavascriptExecutor js = (JavascriptExecutor) driver;
        js.executeScript("location.reload();");
    }

    public void scrollToBottom() {
        JavascriptExecutor js = (JavascriptExecutor) driver;
        js.executeScript("window.scrollTo(0, document.body.scrollHeight);");
    }

    public void scrollToTop() {
        JavascriptExecutor js = (JavascriptExecutor) driver;
        js.executeScript("window.scrollTo(0, 0);");
    }

    public String getPageTitle() {
        JavascriptExecutor js = (JavascriptExecutor) driver;
        return (String) js.executeScript("return document.title;");
    }

    public boolean isElementDisplayed(WebDriver driver, WebElement element) {
        JavascriptExecutor js = (JavascriptExecutor) driver;
        return (Boolean) js.executeScript("return arguments[0].offsetParent !== null;", element);
    }

}
