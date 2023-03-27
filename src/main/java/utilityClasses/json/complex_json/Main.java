//package utilityClasses.json.complex_json;
//
//import com.fasterxml.jackson.databind.ObjectMapper;
//import utilityClasses.json.ai_tests.Tests;
//import utilityClasses.json.json_util.JsonUtil;
//import utilityClasses.json.place_order.PlaceOrder;
//
//
//import java.io.IOException;
//
//public class Main {
//    public static void main(String[] args) throws IOException {
//        // create ObjectMapper instance
//        String n = JsonUtil.readJsonAsString("src/main/java/resources/placeOrder.json");
//        System.out.println(n);
//        ObjectMapper mapper = new ObjectMapper();
//        Tests tests = mapper.readValue(n, Tests.class);
//        tests.getTests().stream().forEach(System.out::println);
//        PlaceOrder placeOrder = mapper.readValue(n, PlaceOrder.class);
//        String newJson = JsonUtil.convertObjectToString(placeOrder);
//        System.out.println(newJson);
//
//    }
//}
