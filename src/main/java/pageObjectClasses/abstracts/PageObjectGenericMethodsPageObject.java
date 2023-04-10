package pageObjectClasses.abstracts;

import lombok.extern.slf4j.Slf4j;
import org.openqa.selenium.*;
import org.openqa.selenium.interactions.Actions;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.Select;
import testSetup.setters.GlobalSettingsGetterMethods;

import java.io.IOException;

@Slf4j
public abstract class PageObjectGenericMethodsPageObject extends PageObjectPageObjectBaseMethods implements GlobalSettingsGetterMethods {

    public PageObjectGenericMethodsPageObject(WebDriver driver) throws IOException {
        super(driver);

    }
    public void waitForAndMoveToElement(By element) {
        fluentWaitWithExpectedCondition(ExpectedConditions.presenceOfElementLocated(element));
        new Actions(d).moveToElement(convertByToWebElement(element)).build().perform();
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
            d.findElement(element).sendKeys(text + Keys.ENTER);
            //d.findElement(element).sendKeys(Keys.RETURN);
        } catch (Exception e) {
            log.error("entering text into search form  did not succeed " + element + " " + text + " ");
            throw (e);
        }
        return (T) this;
    }

    public void clearText(By element) {
        waitForElementToBeClickable(element);
        d.findElement(element).clear();
    }

    /**
     * @see #scrollBy()
     * this method scrolls down on the page by 150 pixels
     * or an amount wanted
     */
    public void scrollBy(int numberOfPixels) {
        try {
            JavascriptExecutor js = (JavascriptExecutor) d;
            String scrollByScript = "window.scrollBy(0," + numberOfPixels + ")";
            js.executeScript(scrollByScript, "");
        } catch (Exception e) {
            log.error("scrolling down  did not succeed ");
            throw (e);
        }
    }

    public void scrollBy() {
        int numberOfPixels = 150;
        try {
            JavascriptExecutor js = (JavascriptExecutor) d;
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


    public PageObjectGenericMethodsPageObject clickOnAcceptCookie(By element, int time) {
        waitForElementToBePresent(element, time);
        convertByToWebElement(element).click();
        return this;
    }

    public void clickOnNthElementInList(By element, int index) throws Exception {
        waitForAndClick(d.findElements(element).get(index));
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
        return returnWait().until(ExpectedConditions.visibilityOfElementLocated(locator)).getText();
    }

//    public void fluentWaitWithAlertIsPresent(){
//        fluentWait(null).until(ExpectedConditions.alertIsPresent());
//    }
//
//    public void fluentWaitWithFrameToBeAvailableAndSwitchToIt(By locator){
//        fluentWait(locator).until(ExpectedConditions.frameToBeAvailableAndSwitchToIt(locator));
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
//    public void fluentWaitWithTitleIs(String title){
//        fluentWait(null).until(ExpectedConditions.titleIs(title));
//    }
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
//    public void fluentWaitWithVisibilityOf(WebElement element){
//        fluentWait(null).until(ExpectedConditions.visibilityOf(element));
//    }
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
//    public void fluentWaitWithPresenceOfElementLocated(By locator){
//        fluentWait(locator).until(ExpectedConditions.presenceOfElementLocated(locator));
//    }
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
//    public void fluentWaitWithElementToBeClickable(WebElement element){
//        fluentWait(null).until(ExpectedConditions.elementToBeClickable(element));
//    }
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
//    public void fluentWaitWithInvisibilityOfElementWithText(By locator, String text){
//        fluentWait(locator).until(ExpectedConditions.invisibilityOfElementWithText(locator, text));
//    }
//
//    public void fluentWaitWithInvisibilityOf(WebElement element){
//        fluentWait(null).until(ExpectedConditions.invisibilityOf(element));
//    }
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
