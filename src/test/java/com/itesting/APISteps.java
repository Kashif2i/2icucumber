package com.itesting;

import io.cucumber.java.en.Given;
import io.cucumber.java.en.Then;
import io.restassured.response.Response;

import static io.restassured.RestAssured.*;

public class APISteps {

    private final SharedDictionary sharedDictionary;

    public APISteps(SharedDictionary sharedDictionary) {
        this.sharedDictionary = sharedDictionary;
    }

    @Given("I request all products")
    public void i_request_all_products() {
        Response response = given().baseUri("http://localhost:2002").when().get("/api/products");
        sharedDictionary.addDict("response", response);
    }
    @Then("I get a status code of {int}")
    public void i_get_a_status_code_of(Integer code) {
        Response response = (Response) sharedDictionary.readDict("response");
        response.then().statusCode(code);
    }
}
