package utilityClasses.json.user;


import com.fasterxml.jackson.databind.ObjectMapper;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

public class Main {
    public static void main(String[] args) throws IOException {
        // create ObjectMapper instance
        ObjectMapper objectMapper = new ObjectMapper();

        // create a list of users
        List<User> users = new ArrayList<>();
        users.add(new User("John", "Doe", 25, "johndoe@example.com"));
        users.add(new User("Jane", "Doe", 30, "janedoe@example.com"));
        users.add(new User("Bob", "Smith", 35, "bobsmith@example.com"));

        // convert list of users to JSON string
        String json = objectMapper.writeValueAsString(users);

        System.out.println(json);
    }
}
