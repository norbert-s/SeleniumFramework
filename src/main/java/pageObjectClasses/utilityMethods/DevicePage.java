package pageObjectClasses.utilityMethods;


import org.openqa.selenium.WebDriver;
import pageObjectClasses.utilityMethods.abstracts.PageObjectGenericMethods;
import pageObjectClasses.utilityMethods.pojo.DevicePojo;

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