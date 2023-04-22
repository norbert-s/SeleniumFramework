package tests;

import com.fasterxml.jackson.core.type.TypeReference;
import com.fasterxml.jackson.databind.ObjectMapper;
import lombok.extern.slf4j.Slf4j;
import org.testng.annotations.DataProvider;
import org.testng.annotations.Listeners;
import org.testng.annotations.Test;
import pageObjectClasses.pageobjects.SpiceJetPageLocators;
import pageObjectClasses.testclasses.SpiceJetTest;
import testSetup.deviceSetup.base.DriverBaseClass;

import testSetup.setters.GlobalSettingsGetterMethods;
import testdata.PassengersAsData;
import utilityClasses.mapkeys.Maps;

import java.io.File;
import java.io.IOException;
import java.util.List;

import static org.testng.AssertJUnit.assertTrue;

@Slf4j
@Listeners(DriverBaseClass.TestListener.class)
public class Parallel extends DriverBaseClass implements GlobalSettingsGetterMethods, SpiceJetPageLocators {

    @Test(groups = {"smoke"}, dataProvider = "testData")
    public void testSpice(PassengersAsData testData) throws Exception {
        SpiceJetTest spiceJetTest = new SpiceJetTest(getDriver());

        int adult = testData.getAdults();
        int children = testData.getChildren();
        int infants = testData.getInfants();
        spiceJetTest.goToWebpage()
            .clickOnPassangers()
            .clickOnAdultsNumberOfTimes(adult)
            //.clickOnAdultsMinusNumberOfTimes(1)
            .clickOnChildrenNumberOfTimes(children)
            //.clickOnChildrenMinusNumberOfTimes(1)
            .clickOnInfantsNumberOfTimes(infants);

        assertTrue(spiceJetTest.getNumberOfAdultsSelected()==(testData.getExpectedAdults()));
        assertTrue(spiceJetTest.getNumberOfChildrenSelected()==(testData.getChildren()));
        assertTrue(spiceJetTest.getNumberOfInfantsSelected()==(testData.getInfants()));
        log.info(adult+" "+children+" "+infants+" "+Thread.currentThread().getId());

        spiceJetTest.refreshPage();

        spiceJetTest.scrollToBottom();
        spiceJetTest.scrollToTop();
        spiceJetTest.clickElementUsingJavascript(spiceJetTest.convertByToWebElement(passengers));


    }
    private List<PassengersAsData> readTestData(String pathName) throws IOException {
        ObjectMapper objectMapper = new ObjectMapper();
        File file = new File(pathName);
        return objectMapper.readValue(file, new TypeReference<List<PassengersAsData>>() {});
    }
    @DataProvider(name = "testData")
    public Object[][] testData() throws IOException {
        List<PassengersAsData> testDataList = readTestData(Maps.basicSpiceTestPath());
        Object[][] testDataArray = new Object[testDataList.size()][1];
        for (int i = 0; i < testDataList.size(); i++) {
            testDataArray[i][0] = testDataList.get(i);
        }
        return testDataArray;
    }

//    @Test(groups = {"smoke"})
//    public void testSpice2() throws Exception {
//        SpiceJetTest spiceJetTest = new SpiceJetTest(getDriver());
//
//        int adult = 2;
//        int children = 2;
//        int infants = 2;
//        spiceJetTest.goToWebpage()
//                .clickOnPassangers()
//                .clickOnAdultsNumberOfTimes(adult)
//                //.clickOnAdultsMinusNumberOfTimes(1)
//                .clickOnChildrenNumberOfTimes(children)
//                //.clickOnChildrenMinusNumberOfTimes(1)
//                .clickOnInfantsNumberOfTimes(infants);
//
//        assertTrue(spiceJetTest.getNumberOfAdultsSelected()==(adult+1));
//        assertTrue(spiceJetTest.getNumberOfChildrenSelected()==(children));
//        assertTrue(spiceJetTest.getNumberOfInfantsSelected()==(infants));
//        log.info(adult+" "+children+" "+infants+" "+Thread.currentThread().getId());
//
//    }
//    @Test(groups = {"smoke"})
//    public void testSpice3() throws Exception {
//        SpiceJetTest spiceJetTest = new SpiceJetTest(getDriver());
//
//        int adult = 1;
//        int children = 1;
//        int infants = 2;
//        spiceJetTest.goToWebpage()
//                .clickOnPassangers()
//                .clickOnAdultsNumberOfTimes(adult)
//                //.clickOnAdultsMinusNumberOfTimes(1)
//                .clickOnChildrenNumberOfTimes(children)
//                //.clickOnChildrenMinusNumberOfTimes(1)
//                .clickOnInfantsNumberOfTimes(infants);
//
//        assertTrue(spiceJetTest.getNumberOfAdultsSelected()==(adult+1));
//        assertTrue(spiceJetTest.getNumberOfChildrenSelected()==(children));
//        assertTrue(spiceJetTest.getNumberOfInfantsSelected()==(infants));
//        log.info(adult+" "+children+" "+infants+" "+Thread.currentThread().getId());
//    }
}
