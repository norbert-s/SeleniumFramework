package testdata;

import org.testng.annotations.DataProvider;
import utilityClasses.mapkeys.Maps;

import java.io.IOException;
import java.util.List;

public class DataProviderClass extends TestDataProvider {

    @DataProvider(name = "spiceBasicTestData", parallel = true)
    public synchronized Object[][] spiceBasicTestData() throws IOException {
        List<PassengersAsData> testDataList = (List<PassengersAsData>) loadTestData(Maps.basicSpiceTestPath(), PassengersAsData.class);
        return testDataProvider(testDataList);
    }

    @DataProvider(name = "spiceBasicTestData2", parallel = true)
    public synchronized Object[][] spiceBasicTestData2() throws IOException {
        List<PassengersAsData> testDataList = (List<PassengersAsData>) loadTestData(Maps.basicSpiceTestPath2(), PassengersAsData.class);
        return testDataProvider(testDataList);
    }
}
