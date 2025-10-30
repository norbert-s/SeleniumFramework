package pageObjectClasses.pageobjects;

import org.openqa.selenium.By;

/**
 * Centralized locators for the SpiceJet page.
 *
 * Converted from an interface of By fields to a final utility class with private
 * static constants and public static getters. This enforces encapsulation while
 * preserving the locators for tests and page objects.
 */
public final class ISpiceJetPageLocators {
    private ISpiceJetPageLocators() { /* utility class */ }

    private static final By PASSENGERS = By.xpath("//div[@data-testid='home-page-travellers']");
    private static final By PASSENGER_ADULT = By.xpath("//div[@data-testid='Adult-testID-plus-one-cta']");

    private static final By PASSENGER_CHILDREN = By.xpath("//div[@data-testid='Children-testID-plus-one-cta']");
    private static final By PASSENGER_INFANTS = By.xpath("//div[@data-testid='Infant-testID-plus-one-cta']");

    private static final By PASSENGER_ADULT_MINUS = By.xpath("//div[@data-testid='Adult-testID-minus-one-cta']");
    private static final By PASSENGER_CHILDREN_MINUS = By.xpath("//div[@data-testid='Children-testID-minus-one-cta']");
    private static final By PASSENGER_INFANTS_MINUS = By.xpath("//div[@data-testid='Infant-testID-minus-one-cta']");

    private static final By RESULT_OF_SETTING_PASSENGERS = By.xpath("//div[@data-testid='home-page-travellers']//descendant::div[3]");

    private static final By NUMBER_OF_INFANTS_TEXT = By.xpath("//div[@data-testid='Infant-testID-minus-one-cta']//following-sibling::*//div");

    private static final By NUMBER_OF_ADULTS_TEXT = By.xpath("//div[@data-testid='Adult-testID-minus-one-cta']//following-sibling::*//div");

    private static final By NUMBER_OF_CHILDREN_TEXT = By.xpath("//div[@data-testid='Children-testID-minus-one-cta']//following-sibling::*//div");

    // Getters
    public static By getPassengers() {
        return PASSENGERS;
    }

    public static By getPassengerAdult() {
        return PASSENGER_ADULT;
    }

    public static By getPassengerChildren() {
        return PASSENGER_CHILDREN;
    }

    public static By getPassengerInfants() {
        return PASSENGER_INFANTS;
    }

    public static By getPassengerAdultMinus() {
        return PASSENGER_ADULT_MINUS;
    }

    public static By getPassengerChildrenMinus() {
        return PASSENGER_CHILDREN_MINUS;
    }

    public static By getPassengerInfantsMinus() {
        return PASSENGER_INFANTS_MINUS;
    }

    public static By getResultOfSettingPassengers() {
        return RESULT_OF_SETTING_PASSENGERS;
    }

    public static By getNumberOfInfantsText() {
        return NUMBER_OF_INFANTS_TEXT;
    }

    public static By getNumberOfAdultsText() {
        return NUMBER_OF_ADULTS_TEXT;
    }

    public static By getNumberOfChildrenText() {
        return NUMBER_OF_CHILDREN_TEXT;
    }
}
