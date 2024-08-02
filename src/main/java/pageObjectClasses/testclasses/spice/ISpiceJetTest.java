package pageObjectClasses.testclasses.spice;

public interface ISpiceJetTest {
    ISpiceJetTest goToWebpage();

    ISpiceJetTest clickOnPassangers();

    ISpiceJetTest clickOnAdultsNumberOfTimes(int numberOfPassangers);

    SpiceJetTest clickOnChildrenNumberOfTimes(int children);

    int getNumberOfAdultsSelected();

    int getNumberOfChildrenSelected();

    int getNumberOfInfantsSelected();

    String getTextAfterPassengerSetupDone();

    public <T> T waitForPageToLoadCompletely();

}

