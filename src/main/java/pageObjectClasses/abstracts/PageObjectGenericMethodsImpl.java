package pageObjectClasses.abstracts;

import lombok.extern.slf4j.Slf4j;
import org.openqa.selenium.*;
import org.openqa.selenium.interactions.Actions;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.Select;
import testSetup.setters.EnvironmentVariables;

import java.io.IOException;
import java.util.List;
import java.util.function.Function;

@Slf4j
public abstract class PageObjectGenericMethodsImpl extends PageObjectBaseMethods implements EnvironmentVariables, IPageObjectGenericMethods {

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

    public void waitForElementToBePresent(By element, int time) {
        fluentWaitWithExpectedCondition(ExpectedConditions.presenceOfElementLocated(element), time);
    }

    public void waitForVisibilityOfWebElement(WebElement element) {
        fluentWaitWithExpectedCondition(ExpectedConditions.visibilityOf(element));
    }

    public void waitForElementVisiblityByElement(By element) {
        fluentWaitWithExpectedCondition(ExpectedConditions.visibilityOfElementLocated(element));
    }

    public void waitForAndClick(By element) {
        waitForElementToBeClickable(element);
        convertByToWebElement(element).click();
    }


    public void waitForAndClick(WebElement element) throws Exception {
        fluentWaitWithExpectedCondition(ExpectedConditions.elementToBeClickable(element));
        element.click();
    }

    public void waitForAndClick(WebElement element, int time) throws Exception {
        fluentWaitWithExpectedCondition(ExpectedConditions.elementToBeClickable(element), time);
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


    public PageObjectGenericMethodsImpl clickOnAcceptCookie(By element, int time) {
        waitForElementToBePresent(element, time);
        convertByToWebElement(element).click();
        return this;
    }

    public void clickOnNthElementInList(By element, int index) throws Exception {
        waitForAndClick(driver.findElements(element).get(index));
    }

    public void fluentWaitWithVisibilityOfElementLocated(By locator) {
        returnWait().until(ExpectedConditions.visibilityOfElementLocated(locator));
    }

    public void fluentWaitWithElementToBeSelected(By locator) {
        returnWait().until(ExpectedConditions.elementToBeSelected(locator));
    }

    public void fluentWaitWithInvisibilityOfElementLocated(By locator) {
        returnWait().until(ExpectedConditions.invisibilityOfElementLocated(locator));
    }

    //create a method with fluentWaitWithExpectedCondition to wait for an element and return text

    public String fluentWaitWithExpectedConditionToReturnText(By locator) {
        return returnWait().until(ExpectedConditions.presenceOfElementLocated(locator)).getText();
    }

    public void fluentWaitForAllElementsPresence(By locator) {
        returnWait().until(ExpectedConditions.presenceOfAllElementsLocatedBy(locator));
    }

    public List<WebElement> returnAllElements(By locator) {
        fluentWaitForAllElementsPresence(locator);
        return driver.findElements(locator);
    }

    public List<WebElement> fluentWaitForJsExecutorWithQuerySelectorAll(String script) {
        JavascriptExecutor jsExecutor = (JavascriptExecutor) driver;
        List<WebElement> allProductSections = returnWait().until(new Function<WebDriver, List<WebElement>>() {
            public List<WebElement> apply(WebDriver driver) {
                List<WebElement> elements = (List<WebElement>) jsExecutor.executeScript(
                        script);
                return elements.size() > 0 ? elements : null;
            }
        });
        return allProductSections;
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

    public void scrollToElement(WebElement element) {
        JavascriptExecutor js = (JavascriptExecutor) driver;
        js.executeScript("arguments[0].scrollIntoView();", element);
    }

    public void clickElementUsingJavaScript(WebElement element) {
        JavascriptExecutor js = (JavascriptExecutor) driver;
        js.executeScript("arguments[0].click();", element);
    }


    public void setInputValueUsingJavaScript(WebElement inputElement, String value) {
        JavascriptExecutor js = (JavascriptExecutor) driver;
        js.executeScript("arguments[0].value = arguments[1];", inputElement, value);
    }

    public String getInnerTextUsingJavaScript(WebElement element) {
        JavascriptExecutor js = (JavascriptExecutor) driver;
        return (String) js.executeScript("return arguments[0].innerText;", element);
    }

    public void changeBackgroundColor(WebElement element, String color) {
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

    public void goToPage(String url) {
        driver.get(url);
    }

    public void fluentWaitWithTextToBePresentInElementLocated(By locator, String text) {
        returnWait().until(ExpectedConditions.textToBePresentInElementLocated(locator, text));
    }

    public void fluentWaitWithTextToBePresentInElementValue(By locator, String text) {
        returnWait().until(ExpectedConditions.textToBePresentInElementValue(locator, text));
    }

    public void fluentWaitWithTitleIs(String title) {
        returnWait().until(ExpectedConditions.titleIs(title));
    }

    //
//    public void fluentWaitWithTitleContains(String title){
//        fluentWait(null).until(ExpectedConditions.titleContains(title));
//    }
//
//    public void fluentWaitWithUrlContains(String url){
//        fluentWait(null).until(ExpectedConditions.urlContains(url));
//    }
//
//    public void fluentWaitWithUrlToBe(String url){
//        fluentWait(null).until(ExpectedConditions.urlToBe(url));
//    }
//
//    public void fluentWaitWithUrlMatches(String url){
//        fluentWait(null).until(ExpectedConditions.urlMatches(url));
//    }
//
//    public void fluentWaitWithNumberOfWindowsToBe(int number){
//        fluentWait(null).until(ExpectedConditions.numberOfWindowsToBe(number));
//    }
//
//    public void fluentWaitWithNumberOfElementsToBe(By locator, int number){
//        fluentWait(locator).until(ExpectedConditions.numberOfElementsToBe(locator, number));
//    }
//
//    public void fluentWaitWithNumberOfElementsToBeMoreThan(By locator, int number){
//        fluentWait(locator).until(ExpectedConditions.numberOfElementsToBeMoreThan(locator, number));
//    }
//
//    public void fluentWaitWithNumberOfElementsToBeLessThan(By locator, int number){
//        fluentWait(locator).until(ExpectedConditions.numberOfElementsToBeLessThan(locator, number));
//    }
//
//    public void fluentWaitWithVisibilityOfAllElementsLocatedBy(By locator){
//        fluentWait(locator).until(ExpectedConditions.visibilityOfAllElementsLocatedBy(locator));
//    }
//
//
//    public void fluentWaitWithVisibilityOfAllElements(List<WebElement> elements){
//        fluentWait(null).until(ExpectedConditions.visibilityOfAllElements(elements));
//    }
//
//    public void fluentWaitWithVisibilityOfAllElements(WebElement... elements){
//        fluentWait(null).until(ExpectedConditions.visibilityOfAllElements(elements));
//    }
//
//    public void fluentWaitWithVisibilityOfAnyElementsLocatedBy(By locator){
//        fluentWait(locator).until(ExpectedConditions.visibilityOfAnyElementsLocatedBy(locator));
//    }
//
//    public void fluentWaitWithVisibilityOfAnyElements(List<WebElement> elements){
//        fluentWait(null).until(ExpectedConditions.visibilityOfAnyElements(elements));
//    }
//
//    public void fluentWaitWithVisibilityOfAnyElements(WebElement... elements){
//        fluentWait(null).until(ExpectedConditions.visibilityOfAnyElements(elements));
//    }
//
    public void fluentWaitWithVisibilityOf(WebElement element) {
        returnWait().until(ExpectedConditions.visibilityOf(element));
    }

    //
//    public void fluentWaitWithStalenessOf(WebElement element){
//        fluentWait(null).until(ExpectedConditions.stalenessOf(element));
//    }
//
//    public void fluentWaitWithPresenceOfAllElementsLocatedBy(By locator){
//        fluentWait(locator).until(ExpectedConditions.presenceOfAllElementsLocatedBy(locator));
//    }
//
//    public void fluentWaitWithPresenceOfAllElements(List<WebElement> elements){
//        fluentWait(null).until(ExpectedConditions.presenceOfAllElements(elements));
//    }
//
//    public void fluentWaitWithPresenceOfAllElements(WebElement... elements){
//        fluentWait(null).until(ExpectedConditions.presenceOfAllElements(elements));
//    }
//
    public void fluentWaitWithPresenceOfElementLocated(By locator) {
        returnWait().until(ExpectedConditions.presenceOfElementLocated(locator));
    }

    //
//    public void fluentWaitWithPresenceOfNestedElementLocatedBy(By locator, By locator2){
//        fluentWait(locator).until(ExpectedConditions.presenceOfNestedElementLocatedBy(locator, locator2));
//    }
//
//    public void fluentWaitWithPresenceOfNestedElementsLocatedBy(By locator, By locator2){
//        fluentWait(locator).until(ExpectedConditions.presenceOfNestedElementsLocatedBy(locator, locator2));
//    }
//
//    public void fluentWaitWithPresenceOfElementLocated(WebElement element, By locator){
//        fluentWait(null).until(ExpectedConditions.presenceOfElementLocated(element, locator));
//    }
//
//    public void fluentWaitWithPresenceOfNestedElementLocatedBy(WebElement element, By locator){
//        fluentWait(null).until(ExpectedConditions.presenceOfNestedElementLocatedBy(element, locator));
//    }
//
//    public void fluentWaitWithPresenceOfNestedElementsLocatedBy(WebElement element, By locator){
//        fluentWait(null).until(ExpectedConditions.presenceOfNestedElementsLocatedBy(element, locator));
//    }
//
//    public void fluentWaitWithElementSelectionStateToBe(WebElement element, boolean selected){
//        fluentWait(null).until(ExpectedConditions.elementSelectionStateToBe(element, selected));
//    }
//
//    public void fluentWaitWithElementSelectionStateToBe(By locator, boolean selected){
//        fluentWait(locator).until(ExpectedConditions.elementSelectionStateToBe(locator, selected));
//    }
//
    public void fluentWaitWithElementToBeClickable(WebElement element) {
        returnWait().until(ExpectedConditions.elementToBeClickable(element));
    }

    //
//    public void fluentWaitWithElementToBeClickable(By locator){
//        fluentWait(locator).until(ExpectedConditions.elementToBeClickable(locator));
//    }
//
//    public void fluentWaitWithElementToBeSelected(WebElement element){
//        fluentWait(null).until(ExpectedConditions.elementToBeSelected(element));
//    }
//
//    public void fluentWaitWithElementToBeSelected(By locator){
//        fluentWait(locator).until(ExpectedConditions.elementToBeSelected(locator));
//    }
//
//    public void fluentWaitWithFrameToBeAvailableAndSwitchToIt(WebElement element){
//        fluentWait(null).until(ExpectedConditions.frameToBeAvailableAndSwitchToIt(element));
//    }
//
//    public void fluentWaitWithFrameToBeAvailableAndSwitchToIt(By locator){
//        fluentWait(locator).until(ExpectedConditions.frameToBeAvailableAndSwitchToIt(locator));
//    }
//
//    public void fluentWaitWithFrameToBeAvailableAndSwitchToIt(int index){
//        fluentWait(null).until(ExpectedConditions.frameToBeAvailableAndSwitchToIt(index));
//    }
//
//    public void fluentWaitWithFrameToBeAvailableAndSwitchToIt(String nameOrId){
//        fluentWait(null).until(ExpectedConditions.frameToBeAvailableAndSwitchToIt(nameOrId));
//    }
//
//    public void fluentWaitWithInvisibilityOfElementLocated(By locator){
//        fluentWait(locator).until(ExpectedConditions.invisibilityOfElementLocated(locator));
//    }
//
    public void fluentWaitWithInvisibilityOfElementWithText(By locator, String text) {
        returnWait().until(ExpectedConditions.invisibilityOfElementWithText(locator, text));
    }

    //
    public void fluentWaitWithInvisibilityOf(WebElement element) {
        returnWait().until(ExpectedConditions.invisibilityOf(element));
    }
//
//    public void fluentWaitWithAlertIsPresent(){
//        fluentWait(null).until(ExpectedConditions.alertIsPresent());
//    }
//
//    public void fluentWaitWithTitleIs(String title){
//        fluentWait(null).until(ExpectedConditions.titleIs(title));
//    }
//
//    public void fluentWaitWithTitleContains(String title){
//        fluentWait(null).until(ExpectedConditions.titleContains(title));
//    }
//
//    public void fluentWaitWithUrlToBe(String url){
//        fluentWait(null).until(ExpectedConditions.urlToBe(url));
//    }
//
//    public void fluentWaitWithUrlContains(String url){
//        fluentWait(null).until(ExpectedConditions.urlContains(url));
//    }
//
//    public void fluentWaitWithUrlMatches(String url){
//        fluentWait(null).until(ExpectedConditions.urlMatches(url));
//    }
//
//    public void fluentWaitWithTextToBe(By locator, String text){
//        fluentWait(locator).until(ExpectedConditions.textToBe(locator, text));
//    }
//
//    public void fluentWaitWithTextToBePresentInElement(WebElement element, String text){
//        fluentWait(null).until(ExpectedConditions.textToBePresentInElement(element, text));
//    }
//
//    public void fluentWaitWithTextToBePresentInElementLocated(By locator, String text){
//        fluentWait(locator).until(ExpectedConditions.textToBePresentInElementLocated(locator, text));
//    }
//
//    public void fluentWaitWithTextToBePresentInElementValue(By locator, String text){
//        fluentWait(locator).until(ExpectedConditions.textToBePresentInElementValue(locator, text));
//    }
//
//    public void fluentWaitWithTextToBePresentInElementValue(WebElement element, String text){
//        fluentWait(null).until(ExpectedConditions.textToBePresentInElementValue(element, text));
//    }
//
//    public void fluentWaitWithNumberOfElementsToBe(By locator, int count){
//        fluentWait(locator).until(ExpectedConditions.numberOfElementsToBe(locator, count));
//    }
//
//    public void fluentWaitWithNumberOfElementsToBeMoreThan(By locator, int count){
//        fluentWait(locator).until(ExpectedConditions.numberOfElementsToBeMoreThan(locator, count));
//    }
//
//    public void fluentWaitWithNumberOfElementsToBeLessThan(By locator, int count){
//        fluentWait(locator).until(ExpectedConditions.numberOfElementsToBeLessThan(locator, count));
//    }
//
//    public void fluentWaitWithVisibilityOfAllElementsLocatedBy(By locator){
//        fluentWait(locator).until(ExpectedConditions.visibilityOfAllElementsLocatedBy(locator));
//    }
//
//    public void visibilityOfAllElements(List<WebElement> elements){
//        fluentWait(null).until(ExpectedConditions.visibilityOfAllElements(elements));
//    }
//
//    public void visibilityOfAllElements(WebElement... elements){
//        fluentWait(null).until(ExpectedConditions.visibilityOfAllElements(elements));
//    }

}
