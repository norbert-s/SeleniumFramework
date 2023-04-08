package tests;

import lombok.extern.slf4j.Slf4j;

import org.testng.annotations.Test;

import pageObjectClasses.testclasses.SpiceJetTest;
import testSetup.deviceSetup.base.BaseClass;
import testSetup.setters.GlobalSettingsGetterMethods;

import static org.testng.AssertJUnit.assertTrue;

@Slf4j
public class Parallel extends BaseClass implements GlobalSettingsGetterMethods {

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
        log.info(adult+" "+children+" "+infants+" "+Thread.currentThread().getId());


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
        log.info(adult+" "+children+" "+infants+" "+Thread.currentThread().getId());

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
        log.info(adult+" "+children+" "+infants+" "+Thread.currentThread().getId());
    }
}
