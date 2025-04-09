package com.example.RestAssured;

import org.testng.annotations.Test;

import io.restassured.RestAssured;

public class AuthAPITest {
    @Test
    public void testBasicAuth() {
        RestAssured
            .given()
            .auth()
            .basic("username", "password")
            .when()
            .get("https://api.example.com/secure-endpoint")
            .then()
            .statusCode(200);
    }

}
