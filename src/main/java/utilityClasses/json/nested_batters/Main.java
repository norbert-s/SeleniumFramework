package utilityClasses.json.nested_batters;

import com.fasterxml.jackson.databind.ObjectMapper;
import utilityClasses.json.json_util.Generic;
import utilityClasses.json.json_util.JsonUtil;

import java.io.IOException;
import java.util.List;

public class Main {
    public static void main(String[] args) throws IOException {
        // create ObjectMapper instance
        String str = JsonUtil.readJsonAsString("src/main/java/resources/highly_nested_batters.json");
        System.out.println(str);
        ObjectMapper mapper = new ObjectMapper();
        List<BatterRoot> root = List.of(mapper.readValue(str, BatterRoot[].class));
        System.out.println(root);
        String finalStr = JsonUtil.convertObjectToString(root);
        Generic.outputStreamWriter("src/main/java/resources/newFile.json",finalStr);
    }
}
