package utilityClasses.json.nested_batters;

import lombok.Getter;
import lombok.Setter;
import lombok.ToString;

import java.util.List;
@ToString
@Getter
@Setter
public class BatterRoot {
    private String id;
    private String type;
    private String name;
    private String ppu;
    private Batters batters;
    private List<Topping> topping;
}
