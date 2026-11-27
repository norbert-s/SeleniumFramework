package testdata;


import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import lombok.ToString;
import testSetup.context.TestExecutionIdentity;

@Getter
@Setter
@ToString
@NoArgsConstructor
public class PassengersAsData implements TestExecutionIdentity {
    private String testId;
    private String datasetId;
    private int adults;
    private int child;
    private int infants;
    private int expectedAdults;
    private int expectedChildren;
    private int expectedInfants;
    private String expectedPassengerText;
}
