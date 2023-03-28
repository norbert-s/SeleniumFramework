package pageObjectClasses.pojo;

import lombok.*;
import org.openqa.selenium.WebElement;

@Getter
@Setter
@ToString
@NoArgsConstructor
@AllArgsConstructor
public class DevicePojo {
    WebElement element;
    private String nameInBald;
    private double ratings;

    private boolean isItDiscounted;

    private double discountPercent;

    private String Price;

    private String originalPrice;

    String greatPrice;
}
