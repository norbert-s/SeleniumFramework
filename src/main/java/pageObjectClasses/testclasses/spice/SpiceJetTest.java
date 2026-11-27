package pageObjectClasses.testclasses.spice;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.By;
import org.openqa.selenium.interactions.Actions;
import org.openqa.selenium.support.ui.Select;
import pageObjectClasses.abstracts.PageObjectGenericMethodsImpl;
import pageObjectClasses.pageobjects.ISpiceJetPageLocators;
import lombok.extern.slf4j.Slf4j;

@Slf4j
public class SpiceJetTest extends PageObjectGenericMethodsImpl implements ISpiceJetTest {
    public SpiceJetTest(WebDriver d) {
        super(d);
    }

    public SpiceJetTest goToWebpage() {
        driver.get("https://book.spicejet.com/Select.aspx");
        waitForPageToLoadCompletely();
        waitForElementToBePresent(ISpiceJetPageLocators.getPassengerAdult());
        return this;
    }

    public SpiceJetTest clickOnPassengers() {
        waitForAndClick(ISpiceJetPageLocators.getPassengers());
        return this;
    }

    public SpiceJetTest clickOnAdultsNumberOfTimes(int numberOfPassengers) {
        setPassengerCount(ISpiceJetPageLocators.getPassengerAdult(), numberOfPassengers);
        return this;
    }

    public SpiceJetTest clickOnChildrenNumberOfTimes(int numberOfPassengers) {
        setPassengerCount(ISpiceJetPageLocators.getPassengerChildren(), numberOfPassengers);
        return this;
    }

    public SpiceJetTest clickOnInfantsNumberOfTimes(int numberOfPassengers) {
        setPassengerCount(ISpiceJetPageLocators.getPassengerInfants(), numberOfPassengers);
        return this;
    }

    public SpiceJetTest clickOnAdultsMinusNumberOfTimes(int numberOfPassengers) {
        decreasePassengerCount(ISpiceJetPageLocators.getPassengerAdult(), numberOfPassengers);
        return this;
    }

    public SpiceJetTest clickOnChildrenMinusNumberOfTimes(int numberOfPassengers) {
        decreasePassengerCount(ISpiceJetPageLocators.getPassengerChildren(), numberOfPassengers);
        return this;
    }

    public SpiceJetTest clickOnInfantsMinusNumberOfTimes(int numberOfPassengers) {
        decreasePassengerCount(ISpiceJetPageLocators.getPassengerInfants(), numberOfPassengers);
        return this;
    }

    public int getNumberOfAdultsSelected() {
        return getSelectedPassengerCount(ISpiceJetPageLocators.getNumberOfAdultsText());
    }


    public int getNumberOfChildrenSelected() {
        return getSelectedPassengerCount(ISpiceJetPageLocators.getNumberOfChildrenText());
    }


    public int getNumberOfInfantsSelected() {
        return getSelectedPassengerCount(ISpiceJetPageLocators.getNumberOfInfantsText());
    }

    public String getTextAfterPassengerSetupDone() {
        return driver.findElement(ISpiceJetPageLocators.getResultOfSettingPassengers()).getText();
    }

    private void setPassengerCount(By locator, int count) {
        new Select(driver.findElement(locator)).selectByValue(String.valueOf(count));
    }

    private void decreasePassengerCount(By locator, int numberOfPassengers) {
        Select passengerSelect = new Select(driver.findElement(locator));
        int currentCount = Integer.parseInt(passengerSelect.getFirstSelectedOption().getAttribute("value"));
        setPassengerCount(locator, Math.max(0, currentCount - numberOfPassengers));
    }

    private int getSelectedPassengerCount(By locator) {
        Select passengerSelect = new Select(driver.findElement(locator));
        return Integer.parseInt(passengerSelect.getFirstSelectedOption().getAttribute("value"));
    }
    
    @Override
    public SpiceJetTest waitForPageToLoadCompletely() {
        super.waitForPageToLoadCompletely();
        return this;
    }

    public int actions3() {
        Actions advancedActions = new Actions(driver);
        advancedActions.moveToElement(driver.findElement(ISpiceJetPageLocators.getPassengers())).click().build().perform();
        advancedActions.moveToElement(driver.findElement(ISpiceJetPageLocators.getPassengers())).click().build().perform();
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
