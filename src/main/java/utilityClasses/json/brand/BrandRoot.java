package utilityClasses.json.brand;

import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.Getter;
import lombok.Setter;
import lombok.ToString;

@Getter
@Setter
@ToString
public class BrandRoot {
    @JsonProperty("modified_id")
    //@JsonSerialize(as="modified")
    private String id;
    private String name;
    private Brand brand;
}
