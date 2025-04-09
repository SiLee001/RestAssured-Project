package com.example.RestAssured;

import org.testng.annotations.Test;
import static org.hamcrest.Matchers.equalTo;
import io.restassured.RestAssured;

public class ResponseValidation {
    @Test
    public void validateResponse() {
        RestAssured
            .given()
            .baseUri("https://jsonplaceholder.typicode.com")
            .when()
            .get("/posts/1")
            .then()
            .statusCode(200)
            .body("userId", equalTo(1))
            .body("id", equalTo(1));
    }

}
