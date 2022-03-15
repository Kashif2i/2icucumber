package com.itesting;

import io.restassured.http.ContentType;
import io.restassured.response.Response;
import io.restassured.specification.RequestSpecification;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.Test;
import static io.restassured.RestAssured.*;
import static org.hamcrest.Matchers.equalTo;

public class RestTests {

    @BeforeAll
    public static void setupDefaultRequestSpec(){
        RequestSpecification spec = given();
        spec.baseUri("http://localhost");
        spec.port(2002);
        spec.contentType(ContentType.JSON);

        requestSpecification =  spec;
    }

    @Test
    void bddGetStaticWithSpec(){
        when().get("/api/products/2")
                .then().statusCode(200)
                .statusLine("HTTP/1.1 200 OK")
                .body("name", equalTo("iPhone X"))
                .log().headers();
    }

//    @Test
//    void bddStatic(){
//        given().baseUri("http://localhost:2002")
//                .when().get("/api/products")
//                .then().statusCode(200);
//
//        RequestSpecification reqSpec = given().baseUri("http://localhost:2002");
//        Response res = reqSpec.when().get("/api/products");
//        res.then().statusCode(200);
//
//    }
}
