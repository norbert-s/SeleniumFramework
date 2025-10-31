package pageObjectClasses.testclasses.spice;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.interactions.Actions;
import pageObjectClasses.abstracts.PageObjectGenericMethodsImpl;
import pageObjectClasses.pageobjects.ISpiceJetPageLocators;

import java.io.IOException;


public class SpiceJetTest extends PageObjectGenericMethodsImpl implements ISpiceJetTest {
    public SpiceJetTest(WebDriver d) throws IOException {
        super(d);
    }

    public SpiceJetTest goToWebpage() {
        driver.get("https://www.spicejet.com/");
        return this;
    }

    public SpiceJetTest clickOnPassengers() {
        waitForAndClick(ISpiceJetPageLocators.getPassengers());
        return this;
    }

    public SpiceJetTest clickOnAdultsNumberOfTimes(int numberOfPassengers) {
        for (int i = 1; i < numberOfPassengers; i++) {
            waitForAndClick(ISpiceJetPageLocators.getPassengerAdult());
        }
        return this;
    }

    public SpiceJetTest clickOnChildrenNumberOfTimes(int numberOfPassengers) {
        for (int i = 0; i < numberOfPassengers; i++) {
            waitForAndClick(ISpiceJetPageLocators.getPassengerChildren());
        }
        return this;
    }

    public SpiceJetTest clickOnInfantsNumberOfTimes(int numberOfPassengers) {
        for (int i = 0; i < numberOfPassengers; i++) {
            waitForAndClick(ISpiceJetPageLocators.getPassengerInfants());
        }
        return this;
    }

    public SpiceJetTest clickOnAdultsMinusNumberOfTimes(int numberOfPassengers) {
        for (int i = 0; i < numberOfPassengers; i++) {
            waitForAndClick(ISpiceJetPageLocators.getPassengerAdultMinus());
        }
        return this;
    }

    public SpiceJetTest clickOnChildrenMinusNumberOfTimes(int numberOfPassengers) {
        for (int i = 0; i < numberOfPassengers; i++) {
            waitForAndClick(ISpiceJetPageLocators.getPassengerChildrenMinus());
        }
        return this;
    }

    public SpiceJetTest clickOnInfantsMinusNumberOfTimes(int numberOfPassengers) {
        for (int i = 0; i < numberOfPassengers; i++) {
            waitForAndClick(ISpiceJetPageLocators.getPassengerInfantsMinus());
        }
        return this;
    }

    public int getNumberOfAdultsSelected() {
        return Integer.parseInt(fluentWaitWithExpectedConditionToReturnText(ISpiceJetPageLocators.getNumberOfAdultsText()));
    }


    public int getNumberOfChildrenSelected() {
        return Integer.parseInt(fluentWaitWithExpectedConditionToReturnText(ISpiceJetPageLocators.getNumberOfChildrenText()));
    }


    public int getNumberOfInfantsSelected() {
        return Integer.parseInt(fluentWaitWithExpectedConditionToReturnText(ISpiceJetPageLocators.getNumberOfInfantsText()));
    }

    public String getTextAfterPassengerSetupDone() {
        System.out.println(driver.findElement(ISpiceJetPageLocators.getResultOfSettingPassengers()).getText());
        return driver.findElement(ISpiceJetPageLocators.getResultOfSettingPassengers()).getText();
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
