package pageObjectClasses.testclasses.spice;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.interactions.Actions;
import org.openqa.selenium.support.ui.Select;
import pageObjectClasses.abstracts.PageObjectGenericMethodsImpl;
import pageObjectClasses.pageobjects.ISpiceJetPageLocators;

import java.io.IOException;


public class SpiceJetTest extends PageObjectGenericMethodsImpl implements ISpiceJetTest {
    public SpiceJetTest(WebDriver d) throws IOException {
        super(d);
    }

    public SpiceJetTest goToWebpage() {
        driver.get("https://book.spicejet.com/Select.aspx");
        return this;
    }

    public SpiceJetTest clickOnPassengers() {
        waitForAndClick(ISpiceJetPageLocators.getPassengers());
        return this;
    }

    public SpiceJetTest clickOnAdultsNumberOfTimes(int numberOfPassengers) {
        selectPassengerCount(ISpiceJetPageLocators.getPassengerAdult(), numberOfPassengers);
        return this;
    }

    public SpiceJetTest clickOnChildrenNumberOfTimes(int numberOfPassengers) {
        selectPassengerCount(ISpiceJetPageLocators.getPassengerChildren(), numberOfPassengers);
        return this;
    }

    public SpiceJetTest clickOnInfantsNumberOfTimes(int numberOfPassengers) {
        selectPassengerCount(ISpiceJetPageLocators.getPassengerInfants(), numberOfPassengers);
        return this;
    }

    public SpiceJetTest clickOnAdultsMinusNumberOfTimes(int numberOfPassengers) {
        int currentCount = getPassengerCount(ISpiceJetPageLocators.getPassengerAdult());
        selectPassengerCount(ISpiceJetPageLocators.getPassengerAdult(), Math.max(1, currentCount - numberOfPassengers));
        return this;
    }

    public SpiceJetTest clickOnChildrenMinusNumberOfTimes(int numberOfPassengers) {
        int currentCount = getPassengerCount(ISpiceJetPageLocators.getPassengerChildren());
        selectPassengerCount(ISpiceJetPageLocators.getPassengerChildren(), Math.max(0, currentCount - numberOfPassengers));
        return this;
    }

    public SpiceJetTest clickOnInfantsMinusNumberOfTimes(int numberOfPassengers) {
        int currentCount = getPassengerCount(ISpiceJetPageLocators.getPassengerInfants());
        selectPassengerCount(ISpiceJetPageLocators.getPassengerInfants(), Math.max(0, currentCount - numberOfPassengers));
        return this;
    }

    public int getNumberOfAdultsSelected() {
        return getPassengerCount(ISpiceJetPageLocators.getNumberOfAdultsText());
    }


    public int getNumberOfChildrenSelected() {
        return getPassengerCount(ISpiceJetPageLocators.getNumberOfChildrenText());
    }


    public int getNumberOfInfantsSelected() {
        return getPassengerCount(ISpiceJetPageLocators.getNumberOfInfantsText());
    }

    public String getTextAfterPassengerSetupDone() {
        return fluentWaitWithExpectedConditionToReturnText(ISpiceJetPageLocators.getResultOfSettingPassengers());
    }

    private void selectPassengerCount(By locator, int count) {
        waitForElementToBePresent(locator);
        new Select(driver.findElement(locator)).selectByValue(String.valueOf(count));
    }

    private int getPassengerCount(By locator) {
        waitForElementToBePresent(locator);
        return Integer.parseInt(new Select(driver.findElement(locator)).getFirstSelectedOption().getText());
    }
    
    @Override
    public <T> T waitForPageToLoadCompletely() {
        return (T) super.waitForPageToLoadCompletely();
    }

    public int actions3() {
        Actions advancedActions = new Actions(driver);
        advancedActions.moveToElement(convertByToWebElement(ISpiceJetPageLocators.getPassengers())).click().build().perform();
        advancedActions.moveToElement(convertByToWebElement(ISpiceJetPageLocators.getPassengers())).click().build().perform();
        return 0;
    }

    public void refreshPage() {
        super.refreshPage();
    }

    public void clickElementUsingJavascript(WebElement element) {
        clickElementUsingJavaScript(element);
    }

    public void testTest() {
        driver.findElement(ISpiceJetPageLocators.getPassengers()).sendKeys("test");
        driver.findElement(ISpiceJetPageLocators.getPassengers()).submit();
    }
}
