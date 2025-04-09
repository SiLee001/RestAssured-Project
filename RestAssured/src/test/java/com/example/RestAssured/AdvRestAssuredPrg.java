package com.example.RestAssured;

import io.restassured.RestAssured;
import io.restassured.response.Response;
import io.restassured.specification.RequestSpecification;
import io.restassured.specification.ResponseSpecification;
import static io.restassured.RestAssured.*;
import static org.hamcrest.Matchers.*;
import java.io.File;
import org.testng.annotations.Test;
public class AdvRestAssuredPrg {
	 // Set up the base URI for GitHub API
private static final String GITHUB_BASE_URI = "https://api.github.com";
private static final String GITHUB_TOKEN = "YOUR_GITHUB_PERSONAL_ACCESS_TOKEN"; // Replace with your token
// RequestSpecification for reusable request config
@Test
public void testRequestSpecification() {
RestAssured.baseURI = GITHUB_BASE_URI;
// Define a request specification
RequestSpecification requestSpec = given()
.header("Authorization", "Bearer " + GITHUB_TOKEN)
.header("Content-Type", "application/json")
.log().all(); // Log request for debugging
// Define a response specification to check the response status code and content type
ResponseSpecification responseSpec = expect()
.statusCode(200)
.contentType("application/json");
// Use the request specification and response specification in the API call
Response response = requestSpec
.when()
.get("/user") // Get authenticated user's details from GitHub API
.then()
.spec(responseSpec) // Apply response validation
.extract().response();
// Print the response body
System.out.println("Response Body: " + response.getBody().asString());
}
// Dynamic Parameters: Using path params and query params
@Test
public void testWithDynamicParameters() {
RestAssured.baseURI = GITHUB_BASE_URI;
String username = "octocat"; // Example GitHub username
String repository = "Hello-World"; // Example repository
// GET request with dynamic parameters
given()
.pathParam("username", username)
.pathParam("repo", repository)
.header("Authorization", "Bearer " + GITHUB_TOKEN)
.when()
.get("/repos/{username}/{repo}")
.then()
.statusCode(200)
.body("name", equalTo(repository)) // Assert that the repository name matches
.body("owner.login", equalTo(username)); // Assert that the owner's username matches
}
// Handling File Upload and Form Data
@Test
public void testFileUpload() {
RestAssured.baseURI = "https://httpbin.org"; // Example file upload endpoint
// Uploading a file along with form data
File file = new File("path_to_file.txt"); // Replace with an actual file path
given()
.multiPart("file", file)
.formParam("description", "Test file upload")
.when()
.post("/post") // HTTP POST to the /post endpoint
.then()
.statusCode(200)
.body("files.file", containsString("path_to_file.txt"))
.body("form.description", equalTo("Test file upload"));
}
// Logging request and response details
@Test
public void testLogging() {
RestAssured.baseURI = GITHUB_BASE_URI;
// Log request and response details
given()
.header("Authorization", "Bearer " + GITHUB_TOKEN)
.header("Content-Type", "application/json")
.log().all() // Log all request details
.when()
.get("/user")
.then()
.log().body() // Log the response body
.statusCode(200);
}
}
