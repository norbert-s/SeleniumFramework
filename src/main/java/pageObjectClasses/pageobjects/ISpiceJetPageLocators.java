package pageObjectClasses.pageobjects;

import org.openqa.selenium.By;

public final class ISpiceJetPageLocators {
    private ISpiceJetPageLocators() { /* utility class */ }

    private static final By PASSENGERS = By.id("divpaxinfo");
    private static final By PASSENGER_ADULT = By.id("ControlGroupSearchView_AvailabilitySearchInputSearchView_DropDownListPassengerType_ADT");

    private static final By PASSENGER_CHILDREN = By.id("ControlGroupSearchView_AvailabilitySearchInputSearchView_DropDownListPassengerType_CHD");
    private static final By PASSENGER_INFANTS = By.id("ControlGroupSearchView_AvailabilitySearchInputSearchView_DropDownListPassengerType_INFANT");

    private static final By NUMBER_OF_INFANTS_TEXT = PASSENGER_INFANTS;
    private static final By NUMBER_OF_ADULTS_TEXT = PASSENGER_ADULT;
    private static final By NUMBER_OF_CHILDREN_TEXT = PASSENGER_CHILDREN;

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

    public static By getResultOfSettingPassengers() {
        return PASSENGERS;
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
