package com.example.RestAssured;

import org.testng.annotations.Test;

import io.restassured.RestAssured;
import io.restassured.http.ContentType;

public class PostRequest {
    @Test
    public void testPostRequest() {
        String requestBody = "{\"title\": \"foo\", \"body\": \"bar\", \"userId\": 1}";

        RestAssured
            .given()
            .baseUri("https://jsonplaceholder.typicode.com")
            .contentType(ContentType.JSON)
            .body(requestBody)
            .when()
            .post("/posts")
            .then()
            .statusCode(201)  // Validating Status Code
            .log().all();     // Log the response
    }

}
