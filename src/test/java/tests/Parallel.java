package tests;


import lombok.extern.slf4j.Slf4j;
import org.testng.annotations.Test;
import pageObjectClasses.utilityMethods.testclasses.SpiceJetTest;
import utilityClasses.testSetup.deviceSetup.base.BaseClass;

@Slf4j
public class Parallel extends BaseClass {
    public static void main(String[] args) {
        System.out.println("Hello World!");
    }
    @Test
    public void testDropDown() throws Exception {
        SpiceJetTest spiceJetTest = new SpiceJetTest(getDriver());
        log.info("we are in testDropDown");
        spiceJetTest.baseTest();
    }
}
