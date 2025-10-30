package tests;

import lombok.extern.slf4j.Slf4j;
import org.testng.annotations.Listeners;
import org.testng.annotations.Test;
import pageObjectClasses.testclasses.spice.ISpiceJetTest;
import testSetup.deviceSetup.base.DriverBaseClass;
import testSetup.setters.EnvironmentVariables;
import testdata.DataProviderClass;
import testdata.PassengersAsData;

import static org.testng.Assert.*;


@Slf4j
@Listeners(DriverBaseClass.TestListener.class)
public class SpiceJetTest extends DriverBaseClass implements EnvironmentVariables {
    @Test(groups = {"smoke"}, dataProvider = "spiceBasicTestData", dataProviderClass = DataProviderClass.class)
    public void testSpice(PassengersAsData testData) throws Exception {
        ISpiceJetTest spiceJetTest = getTestFactory().createSpiceJetTest();

        int adult = testData.getAdults();
        int children = testData.getChild();
        int infants = testData.getInfants();
        spiceJetTest.goToWebpage()
                .clickOnPassangers()
                .clickOnAdultsNumberOfTimes(adult)
                .clickOnChildrenNumberOfTimes(children)
                .clickOnInfantsNumberOfTimes(infants);

        assertEquals(spiceJetTest.getNumberOfAdultsSelected(), testData.getExpectedAdults());
        assertEquals(spiceJetTest.getNumberOfChildrenSelected(), testData.getChild());
        assertEquals(spiceJetTest.getNumberOfInfantsSelected(), testData.getInfants());
        assertEquals(spiceJetTest.getTextAfterPassengerSetupDone(), testData.getExpectedPassengerText());
        log.info("expected number: " + spiceJetTest.getTextAfterPassengerSetupDone() + " current : " + testData.getExpectedPassengerText());
        log.info(adult + " " + children + " " + infants + " " + Thread.currentThread().getId());
    }
}
