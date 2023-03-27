package pageObjectClasses.utilityMethods.pageobjects;

import org.openqa.selenium.By;

public interface SpiceJetPageLocators {
    By passengers = By.xpath("//div[@data-testid='home-page-travellers']");
    By passengerAdult = By.xpath("//div[@data-testid='Adult-testID-plus-one-cta']");

    By passengerChildren = By.xpath("//div[@data-testid='Children-testID-plus-one-cta']");
    By passengerInfants = By.xpath("//div[@data-testid='Infant-testID-plus-one-cta']");

    By passengerAdultMinus = By.xpath("//div[@data-testid='Adult-testID-minus-one-cta']");
    By passengerChildrenMinus = By.xpath("//div[@data-testid='Children-testID-minus-one-cta']");
    By passengerInfantsMinus = By.xpath("//div[@data-testid='Infant-testID-minus-one-cta']");


}
