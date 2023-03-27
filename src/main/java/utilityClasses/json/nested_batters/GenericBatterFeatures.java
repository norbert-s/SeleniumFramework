package utilityClasses.json.nested_batters;

import lombok.AllArgsConstructor;
import lombok.Setter;
import lombok.ToString;
import lombok.experimental.SuperBuilder;

@ToString
@Setter
@AllArgsConstructor
@SuperBuilder
public abstract class GenericBatterFeatures {
    private String id;
    private String type;
}
