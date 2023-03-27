package utilityClasses.testSetup.configreader;

import java.io.FileInputStream;
import java.io.IOException;
import java.io.InputStream;
import java.util.Properties;

public class ConfigReader {

    private static Properties properties;

    public ConfigReader() throws IOException {
        System.out.println("constructor config reader");
        properties = new Properties();

        try (InputStream inputStream = new FileInputStream("G:\\OneDrive\\javaprojects\\add_to_basket\\src\\main\\resources\\data.properties")) {
            if (inputStream != null) {
                properties.load(inputStream);
            } else {
                throw new IOException("data.properties not found");
            }
        } catch (IOException e) {
            e.printStackTrace();
        }

    }
    public static Properties getProps(){
        return properties;
    }

    public String getPropertyValue(String key) {
        return properties.getProperty(key);
    }
}