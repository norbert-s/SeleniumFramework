package pageObjectClasses.testclasses;



import org.openqa.selenium.WebDriver;
import pageObjectClasses.pojo.DevicePojo;
import pageObjectClasses.abstracts.PageObjectGenericMethods;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

public class DevicePage extends PageObjectGenericMethods {

    public DevicePage(WebDriver driver) throws IOException {
        super(driver);
    }

    private List<DevicePojo> allPojos = new ArrayList<>();

    public void setPojos(List<DevicePojo> allPojos){
        this.allPojos=allPojos;
    }


    public void visitDevicePage(){

    }
}