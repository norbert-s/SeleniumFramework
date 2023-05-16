package devices;

import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
@Getter
@Setter
@NoArgsConstructor
public class ProductDetails{
    String processor;
    String capacity;
    String memorySize;
    String displaySize;
    String resolution;
    boolean dualSim;
    boolean memoryCardSlot;
}