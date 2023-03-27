package tests;

import lombok.extern.slf4j.Slf4j;

import org.testng.annotations.Listeners;
import org.testng.annotations.Test;

import listeners.TestListener;
import pageObjectClasses.utilityMethods.testclasses.SpiceJetTest;
import utilityClasses.testSetup.deviceSetup.base.BaseClass;
import utilityClasses.testSetup.setters.GlobalSettingsGetterMethods;

import static org.testng.AssertJUnit.assertTrue;

@Slf4j
@Listeners(TestListener.class)
public class Spice extends BaseClass implements GlobalSettingsGetterMethods {
    //i want to tag this test as smoke test, do it, tag it as smoke test
    @Test(groups = {"smoke"})
    public void testSpice() throws Exception {
        SpiceJetTest spiceJetTest = new SpiceJetTest(getDriver());

        int adult = 3;
        int children = 3;
        int infants = 1;
        spiceJetTest.goToWebpage()
            .clickOnPassangers()
            .clickOnAdultsNumberOfTimes(adult)
            //.clickOnAdultsMinusNumberOfTimes(1)
            .clickOnChildrenNumberOfTimes(children)
            //.clickOnChildrenMinusNumberOfTimes(1)
            .clickOnInfantsNumberOfTimes(infants);

        assertTrue(spiceJetTest.getNumberOfAdultsSelected()==(adult+1));
        assertTrue(spiceJetTest.getNumberOfChildrenSelected()==(children));
        assertTrue(spiceJetTest.getNumberOfInfantsSelected()==(infants));
//        log.info(rahulPages.getNumberOfChildrenSelected());
//        log.info(rahulPages.getNumberOfInfantsSelected());


    }
    @Test(groups = {"smoke"})
    public void testSpice2() throws Exception {
        SpiceJetTest spiceJetTest = new SpiceJetTest(getDriver());

        int adult = 2;
        int children = 2;
        int infants = 2;
        spiceJetTest.goToWebpage()
                .clickOnPassangers()
                .clickOnAdultsNumberOfTimes(adult)
                //.clickOnAdultsMinusNumberOfTimes(1)
                .clickOnChildrenNumberOfTimes(children)
                //.clickOnChildrenMinusNumberOfTimes(1)
                .clickOnInfantsNumberOfTimes(infants);

        assertTrue(spiceJetTest.getNumberOfAdultsSelected()==(adult+1));
        assertTrue(spiceJetTest.getNumberOfChildrenSelected()==(children));
        assertTrue(spiceJetTest.getNumberOfInfantsSelected()==(infants));
//        log.info(rahulPages.getNumberOfChildrenSelected());
//        log.info(rahulPages.getNumberOfInfantsSelected());

        //create a method to convert By to WebElement
    }
    @Test(groups = {"smoke"})
    public void testSpice3() throws Exception {
        SpiceJetTest spiceJetTest = new SpiceJetTest(getDriver());

        int adult = 1;
        int children = 1;
        int infants = 2;
        spiceJetTest.goToWebpage()
                .clickOnPassangers()
                .clickOnAdultsNumberOfTimes(adult)
                //.clickOnAdultsMinusNumberOfTimes(1)
                .clickOnChildrenNumberOfTimes(children)
                //.clickOnChildrenMinusNumberOfTimes(1)
                .clickOnInfantsNumberOfTimes(infants);

        assertTrue(spiceJetTest.getNumberOfAdultsSelected()==(adult+1));
        assertTrue(spiceJetTest.getNumberOfChildrenSelected()==(children));
        assertTrue(spiceJetTest.getNumberOfInfantsSelected()==(infants));
//        log.info(rahulPages.getNumberOfChildrenSelected());


    }
}
