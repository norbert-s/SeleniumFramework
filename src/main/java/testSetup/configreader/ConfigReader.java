package testSetup.configreader;

import lombok.extern.slf4j.Slf4j;

import java.io.IOException;
import java.io.InputStream;
import java.util.Properties;

@Slf4j
public class ConfigReader {

    private static Properties properties;

//    public ConfigReader() throws IOException {
//        System.out.println("constructor config reader");
//        properties = new Properties();
//
//        try (InputStream inputStream =  getClass().getClassLoader().getResourceAsStream("data.properties")) {
//            if (inputStream != null) {
//                properties.load(inputStream);
//            } else {
//                throw new IOException("data.properties not found");
//            }
//        } catch (IOException e) {
//            e.printStackTrace();
//        }
//    }

    static {
        log.info("Static initializer block in ConfigReader");
        properties = new Properties();

        try (InputStream inputStream = ConfigReader.class.getClassLoader().getResourceAsStream("data.properties")) {
            if (inputStream != null) {
                properties.load(inputStream);
            } else {
                throw new IOException("data.properties not found");
            }
        } catch (IOException e) {
            e.printStackTrace();
        }
    }


    public synchronized static Properties getProps() {
        return properties;
    }

    public String getPropertyValue(String key) {
        return properties.getProperty(key);
    }
}