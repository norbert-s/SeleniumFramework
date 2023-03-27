package utilityClasses.json.json_util;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.JsonNode;
import com.fasterxml.jackson.databind.ObjectMapper;

import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;
import java.util.stream.Collectors;

public class JsonUtil {
    static public String readJsonAsString(String filePath) throws JsonProcessingException {
        String json = "";
        try (BufferedReader bufferedReader = new BufferedReader(new FileReader(filePath))) {
            json = bufferedReader.lines().collect(Collectors.joining());
        } catch (IOException e) {

        }
        return json;
    }

    public static JsonNode getAnyValueAsNode(String str) throws JsonProcessingException {
        ObjectMapper mapper = new ObjectMapper();
        return mapper.readValue(str,JsonNode.class);
    }

//    static JsonNode getExampleRoot() throws IOException {
//        InputStream exampleInput =
//                ExampleStructure.class.getClassLoader()
//                        .getResourceAsStream("example.json");
//
//        JsonNode rootNode = mapper.readTree(exampleInput);
//        return rootNode;
//    }

    static public <T> String convertObjectToString(T t){
        ObjectMapper mapper = new ObjectMapper();
        String jsonString=null;
        try {
            jsonString = mapper
                .writerWithDefaultPrettyPrinter()
                .writeValueAsString(t);
        }
        catch (IOException e) {
            e.printStackTrace();
        }
        return jsonString;
    }
}

