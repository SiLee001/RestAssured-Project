package com.example.RestAssured;

import org.testng.annotations.Test;

import io.restassured.RestAssured;
import io.restassured.response.Response;
public class GetRequest {
  
    @Test
    public void testGetRequest() {
        RestAssured
            .given()
            .baseUri("https://jsonplaceholder.typicode.com")
            .when()
            .get("/posts/1")
            .then()
            .statusCode(200)  // Validating Status Code
            .log().all();     // Log the response
    }

}
