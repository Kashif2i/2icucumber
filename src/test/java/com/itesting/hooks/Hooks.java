package com.itesting.hooks;

import com.itesting.SharedDictionary;
import io.cucumber.java.After;
import io.cucumber.java.Before;
import io.github.bonigarcia.wdm.WebDriverManager;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;

public class Hooks {

    private final SharedDictionary sharedDict;
    private WebDriver driver;

    public Hooks(SharedDictionary sharedDict){
        this.sharedDict = sharedDict;
    }

    @Before("@UI")
    public void setup(){
        WebDriverManager.chromedriver().setup();
        driver = new ChromeDriver();
        //store the driver for classes to use
        sharedDict.addDict("driver", driver);
    }

    @After("@UI")
    public void teardown(){
        driver.quit();
    }

}
