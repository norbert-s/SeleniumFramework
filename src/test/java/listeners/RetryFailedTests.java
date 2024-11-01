package listeners;

import lombok.extern.slf4j.Slf4j;
import org.testng.IRetryAnalyzer;
import org.testng.ITestResult;
import testSetup.setters.EnvironmentVariables;

@Slf4j
public class RetryFailedTests implements IRetryAnalyzer {
    private int count = 0;
    private static int maxTry;


    @Override
    public synchronized boolean retry(ITestResult iTestResult) {
        if (EnvironmentVariables.setRetryNumberOfTimes() < 0) {
            maxTry = 0;
        }
        if (EnvironmentVariables.setRetryNumberOfTimes() > 3) {
            log.warn("Max retry number is 3. Setting it to 3 even though it was " + EnvironmentVariables.setRetryNumberOfTimes());
            maxTry = 3;
        } else {
            maxTry = EnvironmentVariables.setRetryNumberOfTimes();
        }
        if (!iTestResult.isSuccess()) {
            //Check if test not succeed
            if (count < maxTry) {
                //Check if maxtry count is reached
                count++;
                log.warn("retrying test case the " + count + "th time");
                iTestResult.setStatus(ITestResult.FAILURE);
                //Mark test as failed
                return true;
            } else {
                iTestResult.setStatus(ITestResult.FAILURE);
            }
        } else {
            iTestResult.setStatus(ITestResult.SUCCESS);
        }
        return false;
    }
}


