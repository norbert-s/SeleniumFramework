package utilityClasses.json.brand;

import lombok.Getter;
import lombok.Setter;
import lombok.ToString;

@Getter
@Setter
@ToString
public class Brand {
    private String id;
    private String name;
    private Owner owner;
}
