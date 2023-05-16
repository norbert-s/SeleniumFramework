package devices;


import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.util.HashMap;
import java.util.Map;

@Getter
@Setter
@NoArgsConstructor
public class Product {
    Map<String,String> productDetails=new HashMap<>();
    String productName;

}

