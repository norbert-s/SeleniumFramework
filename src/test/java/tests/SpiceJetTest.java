package tests;

import lombok.extern.slf4j.Slf4j;
import org.testng.annotations.Listeners;
import org.testng.annotations.Test;
import pageObjectClasses.testclasses.spice.ISpiceJetTest;
import testSetup.deviceSetup.base.DriverBaseClass;
import testSetup.setters.EnvironmentVariables;
import testdata.DataProviderClass;
import testdata.PassengersAsData;


@Slf4j
@Listeners(DriverBaseClass.TestListener.class)
public class SpiceJetTest extends DriverBaseClass implements EnvironmentVariables {


    @Test(groups = {"smoke"}, dataProvider = "spiceBasicTestData", dataProviderClass = DataProviderClass.class)
    public void testSpice(PassengersAsData testData) throws Exception {
        ISpiceJetTest spiceJetTest = getTestFactory().createSpiceJetTest();

//        spiceJetTest.goToWebpage().waitForPageToLoadCompletely();

        int adult = testData.getAdults();
        int children = testData.getChildren();
        int infants = testData.getInfants();
        spiceJetTest.goToWebpage()
                .clickOnPassangers()
                .clickOnAdultsNumberOfTimes(adult)
                .clickOnChildrenNumberOfTimes(children)
                .clickOnInfantsNumberOfTimes(infants);

        getSoftAssert().assertTrue(spiceJetTest.getNumberOfAdultsSelected() == (testData.getExpectedAdults()));
        getSoftAssert().assertTrue(spiceJetTest.getNumberOfChildrenSelected() == (testData.getChildren()));
        getSoftAssert().assertTrue(spiceJetTest.getNumberOfInfantsSelected() == (testData.getInfants()));
        getSoftAssert().assertTrue(testData.getExpectedPassengerText() == spiceJetTest.getTextAfterPassengerSetupDone());
        log.info("expected number: " + spiceJetTest.getTextAfterPassengerSetupDone() + " current : " + testData.getExpectedPassengerText());
        log.info(adult + " " + children + " " + infants + " " + Thread.currentThread().getId());
    }
}
