package utilityClasses.json.place_order;

import com.fasterxml.jackson.databind.ObjectMapper;
import utilityClasses.json.json_util.JsonUtil;

import java.io.IOException;

public class Main {
    public static void main(String[] args) throws IOException {
        // create ObjectMapper instance
        JsonUtil jsonUtil = new JsonUtil();
        String n = jsonUtil.readJsonAsString("src/main/java/resources/placeOrder.json");
        System.out.println(n);
        ObjectMapper mapper = new ObjectMapper();
        PlaceOrder placeOrder = mapper.readValue(n, PlaceOrder.class);
        System.out.println(placeOrder.getTests());

    }
}
