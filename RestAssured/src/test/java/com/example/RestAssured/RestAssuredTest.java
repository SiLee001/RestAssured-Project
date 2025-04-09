package com.example.RestAssured;

import org.testng.Assert;
import org.testng.annotations.Test;
import io.restassured.RestAssured;
import io.restassured.response.Response;

public class RestAssuredTest {
	 @Test
	 public void testGetRequest() {
	 // Base URL of the API
	 RestAssured.baseURI = "https://jsonplaceholder.typicode.com";
	 // Sending a GET request to the API
	 Response response = RestAssured.given()
	 .when()
	 .get("/posts/1") // endpoint for posts
	 .then()
	 .extract()
	 .response();
	 // Print the response
	 System.out.println("Response: " + response.asString());
	 // Assert the response status code
	 Assert.assertEquals(response.getStatusCode(), 200);
	 }

}
