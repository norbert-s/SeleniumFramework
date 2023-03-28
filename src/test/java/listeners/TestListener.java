package listeners;

import lombok.extern.slf4j.Slf4j;
import org.apache.commons.io.FileUtils;
import org.openqa.selenium.OutputType;
import org.openqa.selenium.TakesScreenshot;
import org.openqa.selenium.TimeoutException;
import org.openqa.selenium.WebDriver;
import org.testng.ITestContext;
import org.testng.ITestListener;
import org.testng.ITestResult;
import utilityClasses.date.DateTimeStampGetter;

import java.io.File;

@Slf4j
public class TestListener implements ITestListener  {

    public WebDriver d;

    String location = "logFiles/screenshots/";
    @Override
    public void onStart(ITestContext context) {
        //logger.debug("onStart method started "+context.getName());
    }
    @Override
    public void onFinish(ITestContext context) {

    }
    @Override
    public void onTestStart(ITestResult result) {
        ITestContext context = result.getTestContext();
        String suite = context.getSuite().getName();
        log.debug(suite+" "+result.getName()+"------New Test Started------");
    }
    @Override
    public void onTestSuccess(ITestResult result) {
        ITestContext context = result.getTestContext();
        String suite = context.getSuite().getName();
        try {
            d=(WebDriver)result.getTestClass().getRealClass().getDeclaredField("d").get(result.getInstance());
        } catch (IllegalArgumentException | IllegalAccessException | NoSuchFieldException | SecurityException e) {
            // TODO Auto-generated catch block
            e.printStackTrace();
        }
        try {
            File screenshots = ((TakesScreenshot) d).getScreenshotAs(OutputType.FILE);
            log.info(suite+" "+result.getName()+"------Test finished with Success------");
            FileUtils.copyFile(screenshots,new File(location +"passed"+ DateTimeStampGetter.getDateTime()+suite+"_"+  result.getName()+ ".png"));
        } catch (Throwable e) {
            log.info(suite+" "+result.getName()+"taking a screenshot did not succeed");
        }
    }
    @Override
    public void onTestFailure(ITestResult result) throws TimeoutException {
       ITestContext context = result.getTestContext();
       String suite = context.getSuite().getName();
       try {
        d=(WebDriver)result.getTestClass().getRealClass().getDeclaredField("d").get(result.getInstance());
    } catch (IllegalArgumentException | IllegalAccessException | NoSuchFieldException | SecurityException e) {
        // TODO Auto-generated catch block
        e.printStackTrace();
    }
       try {
           File screenshots = ((TakesScreenshot) d).getScreenshotAs(OutputType.FILE);
           log.error(suite+" "+result.getName()+"------Test finished with FAILURE------");
           FileUtils.copyFile(screenshots,new File(location +"failed"+ DateTimeStampGetter.getDateTime()+suite+"_"+  result.getName()+ ".png"));

       } catch (Throwable e) {
           log.error(suite+" "+result.getName()+"taking a screenshot did not succeed because page did not load up");

       }
    }
    @Override
    public void onTestSkipped(ITestResult result) {
        try{
            ITestContext context = result.getTestContext();
            String suite = context.getSuite().getName();
            log.debug(suite+" "+result.getName()+" test has been SKIPPED " );
        }catch(Exception e){

        }
    }

}
