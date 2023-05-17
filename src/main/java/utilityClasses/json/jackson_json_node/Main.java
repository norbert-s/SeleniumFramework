package utilityClasses.json.jackson_json_node;

import com.fasterxml.jackson.databind.ObjectMapper;
import utilityClasses.json.jackson_json_node.pojos.Root;
import utilityClasses.json.json_util.JsonUtil;
import java.io.IOException;

public class Main {
    public static void main(String[] args) throws IOException {
        // create ObjectMapper instance
        String str = JsonUtil.readJsonAsString("src/main/java/resources/jacksonJsonNodeExample.json");
        System.out.println(str);
        //JsonNode jsonNode = JsonUtil.getAnyValueAsNode(str);
        ObjectMapper mapper = new ObjectMapper();
        Root root = mapper.readValue(str, Root.class);
        System.out.println(root);
//        System.out.println(jsonNode);
        String finalStr = JsonUtil.convertObjectToString(root);
        JsonUtil.outputStreamWriter("src/main/java/resources/newFile.json",finalStr);
    }
}
