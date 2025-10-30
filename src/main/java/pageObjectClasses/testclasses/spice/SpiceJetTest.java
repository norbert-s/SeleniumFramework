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

    public SpiceJetTest clickOnPassangers() {
        waitForAndClick(ISpiceJetPageLocators.getPassengers());
        return this;
    }

    public SpiceJetTest clickOnAdultsNumberOfTimes(int numberOfPassangers) {
        for (int i = 1; i < numberOfPassangers; i++) {
            waitForAndClick(ISpiceJetPageLocators.getPassengerAdult());
        }
        return this;
    }

    public SpiceJetTest clickOnChildrenNumberOfTimes(int numberOfPassangers) {
        for (int i = 0; i < numberOfPassangers; i++) {
            waitForAndClick(ISpiceJetPageLocators.getPassengerChildren());
        }
        return this;
    }

    public SpiceJetTest clickOnInfantsNumberOfTimes(int numberOfPassangers) {
        for (int i = 0; i < numberOfPassangers; i++) {
            waitForAndClick(ISpiceJetPageLocators.getPassengerInfants());
        }
        return this;
    }

    public SpiceJetTest clickOnAdultsMinusNumberOfTimes(int numberOfPassangers) {
        for (int i = 0; i < numberOfPassangers; i++) {
            waitForAndClick(ISpiceJetPageLocators.getPassengerAdultMinus());
        }
        return this;
    }

    public SpiceJetTest clickOnChildrenMinusNumberOfTimes(int numberOfPassangers) {
        for (int i = 0; i < numberOfPassangers; i++) {
            waitForAndClick(ISpiceJetPageLocators.getPassengerChildrenMinus());
        }
        return this;
    }

    public SpiceJetTest clickOnInfantsMinusNumberOfTimes(int numberOfPassangers) {
        for (int i = 0; i < numberOfPassangers; i++) {
            waitForAndClick(ISpiceJetPageLocators.getPassengerInfantsMinus());
        }
        return this;
    }


    public int getNumberOfAdultsSelected() {
        //return d.findElement(By.xpath(""//div[@data-testid='Adult-testID-minus-one-cta']//following-sibling::*//div")).getText();
        return Integer.valueOf(fluentWaitWithExpectedConditionToReturnText(ISpiceJetPageLocators.getNumberOfAdultsText()));
    }


    public int getNumberOfChildrenSelected() {
        return Integer.valueOf(fluentWaitWithExpectedConditionToReturnText(ISpiceJetPageLocators.getNumberOfChildrenText()));
    }


    public int getNumberOfInfantsSelected() {
        return Integer.valueOf(fluentWaitWithExpectedConditionToReturnText(ISpiceJetPageLocators.getNumberOfInfantsText()));
    }

    public String getTextAfterPassengerSetupDone() {
        System.out.println(driver.findElement(ISpiceJetPageLocators.getResultOfSettingPassengers()).getText());
        return driver.findElement(ISpiceJetPageLocators.getResultOfSettingPassengers()).getText();
        //return fluentWaitWithExpectedConditionToReturnText(resultOfSettingPassengers);
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
