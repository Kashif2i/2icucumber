package com.itesting;

import io.cucumber.java.en.Given;
import io.cucumber.java.en.Then;
import io.cucumber.java.en.When;

import org.junit.jupiter.api.Assertions.*;
import org.openqa.selenium.WebDriver;

public class StepDefinitions {

    private final SharedDictionary sharedDict;

    private WebDriver driver;

    public StepDefinitions(SharedDictionary sharedDict){
        this.sharedDict = sharedDict;
        this.driver = (WebDriver) sharedDict.readDict("driver");
    }

}
