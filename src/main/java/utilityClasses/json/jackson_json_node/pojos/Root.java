package utilityClasses.json.jackson_json_node.pojos;

import lombok.Getter;
import lombok.Setter;
import lombok.ToString;

import java.util.List;
@Getter
@Setter
@ToString
public class Root {
    private Name name;
    private String title;
    private String company;
    List<Pets> pets;
}
