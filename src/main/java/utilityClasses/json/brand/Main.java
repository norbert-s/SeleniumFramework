package utilityClasses.json.brand;

import com.fasterxml.jackson.databind.ObjectMapper;
import utilityClasses.json.json_util.Generic;
import utilityClasses.json.json_util.JsonUtil;

import java.io.IOException;

public class Main {
    public static void main(String[] args) throws IOException {
        // create ObjectMapper instance
        String str = JsonUtil.readJsonAsString("src/main/java/resources/brand.json");
        System.out.println(str);
        ObjectMapper mapper = new ObjectMapper();
        BrandRoot root = mapper.readValue(str, BrandRoot.class);
        System.out.println(root);
        String finalStr = JsonUtil.convertObjectToString(root);
        Generic.outputStreamWriter("src/main/java/resources/newFile.json",finalStr);
    }
}
