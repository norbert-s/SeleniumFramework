package pageObjectClasses.pageobjects;

import org.openqa.selenium.By;

public interface ISpiceJetPageLocators {
    By passengers = By.xpath("//div[@data-testid='home-page-travellers']");
    By passengerAdult = By.xpath("//div[@data-testid='Adult-testID-plus-one-cta']");

    By passengerChildren = By.xpath("//div[@data-testid='Children-testID-plus-one-cta']");
    By passengerInfants = By.xpath("//div[@data-testid='Infant-testID-plus-one-cta']");

    By passengerAdultMinus = By.xpath("//div[@data-testid='Adult-testID-minus-one-cta']");
    By passengerChildrenMinus = By.xpath("//div[@data-testid='Children-testID-minus-one-cta']");
    By passengerInfantsMinus = By.xpath("//div[@data-testid='Infant-testID-minus-one-cta']");

    By resultOfSettingPassengers = By.xpath("//div[@data-testid='home-page-travellers']//descendant::div[3]");


    By numberOfInfantsText = By.xpath("//div[@data-testid='Infant-testID-minus-one-cta']//following-sibling::*//div");

    By numberOfAdultsText = By.xpath("//div[@data-testid='Adult-testID-minus-one-cta']//following-sibling::*//div");

    By numberOfChildrentext = By.xpath("//div[@data-testid='Children-testID-minus-one-cta']//following-sibling::*//div");


}
