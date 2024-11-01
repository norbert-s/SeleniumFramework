package testdata;


import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import lombok.ToString;

@Getter
@Setter
@ToString
@NoArgsConstructor
public class PassengersAsData {
    private int adults;
    private int children;
    private int infants;
    private int expectedAdults;
    private int expectedChildren;
    private int expectedInfants;
    private String expectedPassengerText;
}
