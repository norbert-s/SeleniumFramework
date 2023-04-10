package pageObjectClasses.testclasses;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.interactions.Actions;
import pageObjectClasses.abstracts.PageObjectGenericMethodsPageObject;
import pageObjectClasses.pageobjects.SpiceJetPageLocators;

import java.io.IOException;

public class SpiceJetTest extends PageObjectGenericMethodsPageObject implements SpiceJetPageLocators {
    public SpiceJetTest(WebDriver d) throws IOException {
        super(d);
    }

    public void baseTest(){
        d.get("https://rahulshettyacademy.com/AutomationPractice");
        waitForElementToBeClickable(By.id("dropdown-class-example"));
        selectFromDropDownByindex(convertByToWebElement(By.id("dropdown-class-example")), 2);
        waitForAndMoveToElement(By.id("mousehover"));
        waitForAndClick(By.linkText("Reload"));
        //waitForElementToBeClickable(By.linkText("Reload"));
        //advancedActions.moveToElement(convertByToWebElement(By.linkText("Reload"))).click().build().perform();
    }
    public SpiceJetTest goToWebpage(){
        d.get("https://www.spicejet.com/");
        //waitForElementToBeClickable(By.cssSelector("svg[data-testid=\"svg-img\"]"));

        return this;
    }


    public SpiceJetTest clickOnPassangers(){
        waitForAndClick(passengers);
        return this;
    }

    public SpiceJetTest clickOnAdultsNumberOfTimes(int numberOfPassangers){
        for (int i = 0; i < numberOfPassangers; i++) {
            waitForAndClick(passengerAdult);
        }
        return this;
    }

    public SpiceJetTest clickOnChildrenNumberOfTimes(int numberOfPassangers){
        for (int i = 0; i < numberOfPassangers; i++) {
            waitForAndClick(passengerChildren);
        }
        return this;
    }

    public SpiceJetTest clickOnInfantsNumberOfTimes(int numberOfPassangers){
        for (int i = 0; i < numberOfPassangers; i++) {
            waitForAndClick(passengerInfants);
        }
        return this;
    }
    public SpiceJetTest clickOnAdultsMinusNumberOfTimes(int numberOfPassangers){
        for (int i = 0; i < numberOfPassangers; i++) {
            waitForAndClick(passengerAdultMinus);
        }
        return this;
    }

    public SpiceJetTest clickOnChildrenMinusNumberOfTimes(int numberOfPassangers){
        for (int i = 0; i < numberOfPassangers; i++) {
            waitForAndClick(passengerChildrenMinus);
        }
        return this;
    }

    public SpiceJetTest clickOnInfantsMinusNumberOfTimes(int numberOfPassangers){
        for (int i = 0; i < numberOfPassangers; i++) {
            waitForAndClick(passengerInfantsMinus);
        }
        return this;
    }


    public int getNumberOfAdultsSelected(){
        //return d.findElement(By.xpath(""//div[@data-testid='Adult-testID-minus-one-cta']//following-sibling::*//div")).getText();
        return Integer.valueOf(fluentWaitWithExpectedConditionToReturnText(By.xpath("//div[@data-testid='Adult-testID-minus-one-cta']//following-sibling::*//div")));
    }


    public int getNumberOfChildrenSelected(){
        return Integer.valueOf(fluentWaitWithExpectedConditionToReturnText(By.xpath("//div[@data-testid='Children-testID-minus-one-cta']//following-sibling::*//div")));
    }


    public int getNumberOfInfantsSelected(){
        return Integer.valueOf(fluentWaitWithExpectedConditionToReturnText(By.xpath("//div[@data-testid='Infant-testID-minus-one-cta']//following-sibling::*//div")));
    }



    public int actions3(){
        Actions advancedActions = new Actions( d);
        advancedActions.moveToElement(convertByToWebElement(passengers)).click().build().perform();
        advancedActions.moveToElement(convertByToWebElement(passengers)).click().build().perform();
        return 0;
    }
}
