package com.example.RestAssured;

import org.testng.annotations.Test;

import io.restassured.RestAssured;

public class BearerToken {
	@Test
    public void testBearerToken() {
        String bearerToken = "your_bearer_token_here";  // Replace with your valid Bearer token

        RestAssured
            .given()
            .auth()
            .oauth2(bearerToken)  // Use Bearer token for authentication
            .when()
            .get("https://api.spotify.com/v1/me")  // Spotify API for user profile
            .then()
            .statusCode(200);  // Assert status code is 200 OK
    }

}
