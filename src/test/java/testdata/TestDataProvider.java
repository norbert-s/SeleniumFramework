package testdata;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.databind.type.CollectionType;

import java.io.File;
import java.io.IOException;
import java.util.List;

public abstract class TestDataProvider {
    public synchronized Object[][] testDataProvider(List<?> testDataList) throws IOException {
        Object[][] testDataArray = new Object[testDataList.size()][1];
        for (int i = 0; i < testDataList.size(); i++) {
            testDataArray[i][0] = testDataList.get(i);
        }
        return testDataArray;
    }

    synchronized List<?> loadTestData(String testDataPath, Class<?> testDataClass) throws IOException {
        ObjectMapper objectMapper = new ObjectMapper();
        File file = new File(testDataPath);
        CollectionType collectionType = objectMapper.getTypeFactory().constructCollectionType(List.class, testDataClass);
        return objectMapper.readValue(file, collectionType);
    }
}
