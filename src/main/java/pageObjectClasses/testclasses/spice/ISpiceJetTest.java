package pageObjectClasses.testclasses.spice;

public interface ISpiceJetTest {
    ISpiceJetTest goToWebpage();

    ISpiceJetTest clickOnPassengers();

    ISpiceJetTest clickOnAdultsNumberOfTimes(int numberOfPassengers);

    ISpiceJetTest clickOnChildrenNumberOfTimes(int children);

    ISpiceJetTest clickOnInfantsNumberOfTimes(int infants);

    ISpiceJetTest clickOnAdultsMinusNumberOfTimes(int numberOfPassengers);

    ISpiceJetTest clickOnChildrenMinusNumberOfTimes(int numberOfPassengers);

    ISpiceJetTest clickOnInfantsMinusNumberOfTimes(int numberOfPassengers);

    int getNumberOfAdultsSelected();

    int getNumberOfChildrenSelected();

    int getNumberOfInfantsSelected();

    String getTextAfterPassengerSetupDone();

    <T> T waitForPageToLoadCompletely();
}
