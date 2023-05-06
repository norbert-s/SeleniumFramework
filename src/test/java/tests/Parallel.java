package tests;

import lombok.extern.slf4j.Slf4j;
import org.testng.annotations.Listeners;
import org.testng.annotations.Test;
import pageObjectClasses.factory.ITestFactory;
import pageObjectClasses.pageobjects.SpiceJetPageLocators;
import pageObjectClasses.testclasses.ISpiceJetTest;
import pageObjectClasses.testclasses.SpiceJetTest;
import testSetup.deviceSetup.base.DriverBaseClass;
import testSetup.setters.GlobalSettingsGetterMethods;
import testdata.DataProviderClass;
import testdata.PassengersAsData;


@Slf4j
@Listeners(DriverBaseClass.TestListener.class)
public class Parallel extends DriverBaseClass implements GlobalSettingsGetterMethods, SpiceJetPageLocators {

    @Test(groups = {"smoke"}, dataProvider = "spiceBasicTestData",dataProviderClass = DataProviderClass.class)
    public void testSpice(PassengersAsData testData) throws Exception {
        ISpiceJetTest spiceJetTest = getTestFactory().createSpiceJetTest();

        int adult = testData.getAdults();
        int children = testData.getChildren();
        int infants = testData.getInfants();
        spiceJetTest.goToWebpage()
            .clickOnPassangers()
            .clickOnAdultsNumberOfTimes(adult)
            .clickOnChildrenNumberOfTimes(children)
            .clickOnInfantsNumberOfTimes(infants);

        getSoftAssert().assertTrue(spiceJetTest.getNumberOfAdultsSelected()==(testData.getExpectedAdults()));
        getSoftAssert().assertTrue(spiceJetTest.getNumberOfChildrenSelected()==(testData.getChildren()));
        getSoftAssert().assertTrue(spiceJetTest.getNumberOfInfantsSelected()==(testData.getInfants()));
        getSoftAssert().assertTrue(testData.getExpectedPassengerText()== spiceJetTest.getTextAfterPassengerSetupDone());
        log.info("expected number: "+spiceJetTest.getTextAfterPassengerSetupDone()+" current : "+testData.getExpectedPassengerText());
        log.info(adult+" "+children+" "+infants+" "+Thread.currentThread().getId());
    }
}
