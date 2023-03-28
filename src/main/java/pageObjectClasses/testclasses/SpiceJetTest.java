package pageObjectClasses.testclasses;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import pageObjectClasses.abstracts.PageObjectGenericMethods;
import pageObjectClasses.pageobjects.SpiceJetPageLocators;

import java.io.IOException;

public class SpiceJetTest extends PageObjectGenericMethods implements SpiceJetPageLocators {
    public SpiceJetTest(WebDriver d) throws IOException {
        super(d);
    }

    public void baseTest(){
        d.get("https://rahulshettyacademy.com/AutomationPractice");
        waitForElementToBeClickable(By.id("dropdown-class-example"));
        selectFromDropDownByindex(convertByToWebElement(By.id("dropdown-class-example")), 2);
    }
    public SpiceJetTest goToWebpage(){
        d.get("https://www.spicejet.com/");
        //waitForElementToBeClickable(By.cssSelector("svg[data-testid=\"svg-img\"]"));
        //waitfor element to be clickable and click on it
        return this;
    }

    //use waitfor element to be clickable and click on it
    public SpiceJetTest clickOnPassangers(){
        waitForAndClick(passengers);
        return this;
    }

    //create another method to click on the number of passangers
    public SpiceJetTest clickOnAdultsNumberOfTimes(int numberOfPassangers){
        for (int i = 0; i < numberOfPassangers; i++) {
            waitForAndClick(passengerAdult);
        }
        return this;
    }
    //create a method to click on the number of children a number of times
    public SpiceJetTest clickOnChildrenNumberOfTimes(int numberOfPassangers){
        for (int i = 0; i < numberOfPassangers; i++) {
            waitForAndClick(passengerChildren);
        }
        return this;
    }
    //create a method to click on the number of infants a number of times
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
    //create a method to click on the number of children a number of times
    public SpiceJetTest clickOnChildrenMinusNumberOfTimes(int numberOfPassangers){
        for (int i = 0; i < numberOfPassangers; i++) {
            waitForAndClick(passengerChildrenMinus);
        }
        return this;
    }
    //create a method to click on the number of infants a number of times
    public SpiceJetTest clickOnInfantsMinusNumberOfTimes(int numberOfPassangers){
        for (int i = 0; i < numberOfPassangers; i++) {
            waitForAndClick(passengerInfantsMinus);
        }
        return this;
    }

    //create a method to check how many adults are selected
    public int getNumberOfAdultsSelected(){
        //return d.findElement(By.xpath(""//div[@data-testid='Adult-testID-minus-one-cta']//following-sibling::*//div")).getText();
        return Integer.valueOf(fluentWaitWithExpectedConditionToReturnText(By.xpath("//div[@data-testid='Adult-testID-minus-one-cta']//following-sibling::*//div")));
    }

    //create a method to check how many children are selected
    public int getNumberOfChildrenSelected(){
        return Integer.valueOf(fluentWaitWithExpectedConditionToReturnText(By.xpath("//div[@data-testid='Children-testID-minus-one-cta']//following-sibling::*//div")));
    }

    //create a method to check how many infants are selected
    public int getNumberOfInfantsSelected(){
        return Integer.valueOf(fluentWaitWithExpectedConditionToReturnText(By.xpath("//div[@data-testid='Infant-testID-minus-one-cta']//following-sibling::*//div")));
    }








}
