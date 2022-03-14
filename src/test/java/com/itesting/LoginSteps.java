package com.itesting;

import com.itesting.hooks.Hooks;
import io.cucumber.java.After;
import io.cucumber.java.Before;
import io.cucumber.java.en.Given;
import io.cucumber.java.en.Then;
import io.cucumber.java.en.When;
import io.github.bonigarcia.wdm.WebDriverManager;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;

import java.util.List;
import java.util.Map;

import static org.hamcrest.Matchers.*;

import static org.hamcrest.MatcherAssert.assertThat;
import static com.itesting.hooks.Hooks.*;

public class LoginSteps {

    private final SharedDictionary sharedDict;

    private WebDriver driver;

    public LoginSteps(SharedDictionary sharedDict){
        this.sharedDict = sharedDict;
        this.driver = (WebDriver) sharedDict.readDict("driver");
    }

    @Given("I am on the login page")
    public void i_am_on_the_login_page() throws InterruptedException {
        WebDriverManager.chromedriver().setup();
        driver = new ChromeDriver();
        driver.get("https://www.edgewordstraining.co.uk/webdriver2/sdocs/auth.php");
        Thread.sleep(2000);
    }
    @When("I use valid username {string} and password {string}")
    public void i_use_valid_username_and_password(String username, String password) throws InterruptedException {
        driver.findElement(By.id("username")).sendKeys(username);
        driver.findElement(By.id("password")).sendKeys(password);
        driver.findElement(By.linkText("Submit")).click();

    }
    @Then("I am logged in")
    public void i_am_logged_in() throws InterruptedException {
        Thread.sleep(2000);
//        String bodyText = driver.findElement(By)
        assertThat(driver.findElement(By.linkText("Log Out")).isDisplayed(), is(true));
        driver.close();
    }

    @When("I use invalid username {string} and password {string}")
    public void iUseInvalidUsernameAndPassword(String username, String password) throws InterruptedException {
        driver.findElement(By.id("username")).sendKeys(username);
        driver.findElement(By.id("password")).sendKeys(password);
        driver.findElement(By.linkText("Submit")).click();
        Thread.sleep(2000);
    }

    @Then("I am not logged in and see an error message\"")
    public void iAmNotLoggedInAndSeeAnErrorMessage() throws Throwable {
        assertThat(driver.switchTo().alert().getText(), is(equalToIgnoringCase("Error: Username must contain at least six characters!")));
        driver.switchTo().alert().accept();
        driver.close();
    }

    @When("I use username {string} and password {string}")
    public void iUseUsernameAndPassword(String username, String password) throws InterruptedException {

        driver.findElement(By.id("username")).sendKeys(username);
        driver.findElement(By.id("password")).sendKeys(password);
        driver.findElement(By.linkText("Submit")).click();
        Thread.sleep(2000);
    }

    @When("I use the valid login credentials")
    public void i_use_the_valid_login_credentials(io.cucumber.datatable.DataTable dataTable) {

        List<Map<String,String>> logins = dataTable.asMaps(String.class, String.class);

        for (var login: logins){
            driver.findElement(By.id("username")).sendKeys(login.get("username"));
            driver.findElement(By.id("password")).sendKeys(login.get("password"));
            driver.findElement(By.linkText("Submit")).click();
        }
    }
}
