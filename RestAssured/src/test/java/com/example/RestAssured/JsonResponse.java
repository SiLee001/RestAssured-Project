package com.example.RestAssured;

import org.testng.annotations.Test;
import static org.hamcrest.Matchers.equalTo;
import io.restassured.RestAssured;

public class JsonResponse {
	@Test
    public void testJsonResponse() {
        RestAssured
            .given()
            .baseUri("https://jsonplaceholder.typicode.com")
            .when()
            .get("/posts/1")
            .then()
            .statusCode(200)
            .body("title", equalTo("sunt aut facere repellat provident occaecati excepturi optio reprehenderit"));
    }

}
