package testdata;

import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import lombok.ToString;

@Getter
@Setter
@ToString
@NoArgsConstructor
public class MediaMarktAsData {

    private String testID;
    private String url;
    private String device;
    private String productNameToLookFor;

}

