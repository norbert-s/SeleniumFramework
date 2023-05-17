package utilityClasses.json.project_ref;

import com.fasterxml.jackson.databind.JsonNode;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.databind.node.ObjectNode;

import utilityClasses.json.json_util.JsonUtil;

import java.io.IOException;

public class Main {
//    public static void main(String[] args) throws IOException {
//        // create ObjectMapper instance
//        JsonUtil jsonUtil = new JsonUtil();
//        String n = jsonUtil.readJsonAsString("src/main/java/resources/projectRef.json");
//        System.out.println(n);
//        ObjectMapper mapper = new ObjectMapper();
//        ProjectRef it = mapper.readValue(n, ProjectRef.class);
//
//    }
    public static void main(String[] args) throws IOException {
        // create ObjectMapper instance
        JsonUtil jsonUtil = new JsonUtil();
        String n = jsonUtil.readJsonAsString("src/main/java/resources/projectRef.json");
        System.out.println(n);
        ObjectMapper mapper = new ObjectMapper();
        ObjectNode node = mapper.readValue(n, ObjectNode.class);
        JsonNode jsonNode = mapper.createObjectNode();
        System.out.println(node);
        String str = JsonUtil.convertObjectToString(node);
        JsonUtil.outputStreamWriter("src/main/java/resources/newFile.json",str);
    }
}
