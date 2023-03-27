package tests;

import lombok.extern.slf4j.Slf4j;
import org.testng.annotations.Test;
import pageObjectClasses.utilityMethods.testclasses.SpiceJetTest;
import utilityClasses.testSetup.deviceSetup.base.BaseClass;
import utilityClasses.testSetup.setters.GlobalSettingsGetterMethods;

import static org.testng.AssertJUnit.assertTrue;

@Slf4j
//@Listeners(TestListener.class)
public class Spice3 extends BaseClass implements GlobalSettingsGetterMethods {

    @Test(groups = {"smoke"})
    public void testSpice() throws Exception {
        SpiceJetTest spiceJetTest = new SpiceJetTest(getDriver());
        log.info("we are in testSpice");
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
//        log.info(rahulPages.getNumberOfInfantsSelected());
        Thread.sleep(5000);

    }
}
