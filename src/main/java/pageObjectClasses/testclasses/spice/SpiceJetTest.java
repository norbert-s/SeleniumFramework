package pageObjectClasses.testclasses.spice;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.interactions.Actions;
import pageObjectClasses.abstracts.PageObjectGenericMethodsImpl;
import pageObjectClasses.pageobjects.ISpiceJetPageLocators;

import java.io.IOException;

public class SpiceJetTest extends PageObjectGenericMethodsImpl implements ISpiceJetPageLocators, ISpiceJetTest {
    public SpiceJetTest(WebDriver d) throws IOException {
        super(d);
    }

    public SpiceJetTest goToWebpage() {
        driver.get("https://www.spicejet.com/");
        //waitForElementToBeClickable(By.cssSelector("svg[data-testid=\"svg-img\"]"));

        return this;
    }


    public SpiceJetTest clickOnPassangers() {
        waitForAndClick(passengers);
        return this;
    }

    public SpiceJetTest clickOnAdultsNumberOfTimes(int numberOfPassangers) {
        for (int i = 0; i < numberOfPassangers; i++) {
            waitForAndClick(passengerAdult);
        }
        return this;
    }

    public SpiceJetTest clickOnChildrenNumberOfTimes(int numberOfPassangers) {
        for (int i = 0; i < numberOfPassangers; i++) {
            waitForAndClick(passengerChildren);
        }
        return this;
    }

    public SpiceJetTest clickOnInfantsNumberOfTimes(int numberOfPassangers) {
        for (int i = 0; i < numberOfPassangers; i++) {
            waitForAndClick(passengerInfants);
        }
        return this;
    }

    public SpiceJetTest clickOnAdultsMinusNumberOfTimes(int numberOfPassangers) {
        for (int i = 0; i < numberOfPassangers; i++) {
            waitForAndClick(passengerAdultMinus);
        }
        return this;
    }

    public SpiceJetTest clickOnChildrenMinusNumberOfTimes(int numberOfPassangers) {
        for (int i = 0; i < numberOfPassangers; i++) {
            waitForAndClick(passengerChildrenMinus);
        }
        return this;
    }

    public SpiceJetTest clickOnInfantsMinusNumberOfTimes(int numberOfPassangers) {
        for (int i = 0; i < numberOfPassangers; i++) {
            waitForAndClick(passengerInfantsMinus);
        }
        return this;
    }


    public int getNumberOfAdultsSelected() {
        //return d.findElement(By.xpath(""//div[@data-testid='Adult-testID-minus-one-cta']//following-sibling::*//div")).getText();
        return Integer.valueOf(fluentWaitWithExpectedConditionToReturnText(numberOfAdultsText));
    }


    public int getNumberOfChildrenSelected() {
        return Integer.valueOf(fluentWaitWithExpectedConditionToReturnText(numberOfChildrentext));
    }


    public int getNumberOfInfantsSelected() {
        return Integer.valueOf(fluentWaitWithExpectedConditionToReturnText(numberOfInfantsText));
    }

    public String getTextAfterPassengerSetupDone() {
        System.out.println(driver.findElement(resultOfSettingPassengers).getText());
        return driver.findElement(resultOfSettingPassengers).getText();
        //return fluentWaitWithExpectedConditionToReturnText(resultOfSettingPassengers);
    }


    public int actions3() {
        Actions advancedActions = new Actions(driver);
        advancedActions.moveToElement(convertByToWebElement(passengers)).click().build().perform();
        advancedActions.moveToElement(convertByToWebElement(passengers)).click().build().perform();
        return 0;
    }

    public void refreshPage() {
        super.refreshPage();
    }

    public void clickElementUsingJavascript(WebElement element) {
        clickElementUsingJavaScript(element);
    }

    public void testTest() {
        driver.findElement(passengers).sendKeys("test");
        driver.findElement(passengers).submit();
    }
}
