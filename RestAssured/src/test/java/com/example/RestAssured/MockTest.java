package com.example.RestAssured;
import static io.restassured.RestAssured.*;
import org.testng.annotations.AfterClass;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.Test;

import static com.github.tomakehurst.wiremock.client.WireMock.*;
import com.github.tomakehurst.wiremock.core.WireMockConfiguration;
import com.github.tomakehurst.wiremock.junit.WireMockRule;
import com.github.tomakehurst.wiremock.WireMockServer;
import com.github.tomakehurst.wiremock.client.WireMock;
import com.github.tomakehurst.wiremock.matching.UrlPattern;
import com.github.tomakehurst.wiremock.http.ResponseDefinition;

public class MockTest {
	private WireMockServer wireMockServer; // Declare a WireMockServer instance
	@BeforeClass // This will run before any tests in the class
	public void setUp() throws Exception {
	try {
	// Initialize the WireMockServer on port 8089
	wireMockServer = new WireMockServer(WireMockConfiguration.wireMockConfig().port(8089)); // Initialize WireMock server
	wireMockServer.start(); // Start the WireMock server
	System.out.println("WireMock server started on port 8089");
	// Configure WireMock for localhost
	WireMock.configureFor("localhost", 8089); // Setup WireMock client for interaction
	} catch (Exception e) {
	System.err.println("Error occurred while starting the WireMock server: " + e.getMessage());
	e.printStackTrace();
	throw e; // Re-throw the exception to fail the test if setup fails
	}
	}
	@AfterClass // This will run after all tests are executed
	public void tearDown() {
	try {
	// Stop the WireMock server after the tests
	if (wireMockServer != null) {
	wireMockServer.stop();
	System.out.println("WireMock server stopped");
	}
	} catch (Exception e) {
	System.err.println("Error occurred while stopping the WireMock server: " + e.getMessage());
	e.printStackTrace();
	}
	}
	@Test
	public void testMockingWithWireMock() {
	try {
	// Stubbing a GET request for a user endpoint
	stubFor(get(urlEqualTo("/users/123"))
	.willReturn(aResponse()
	.withStatus(200)
	.withHeader("Content-Type", "application/json")
	.withBody("{\"id\": \"123\", \"name\": \"John Doe\"}")));
	// Use RestAssured to make the request to the mocked server
	String response = given()
	.baseUri("http://localhost:8089") // Ensure the port matches the WireMock server
	.when()
	.get("/users/123")
	.then()
	.statusCode(200)
	.extract().body().asString();
	// Print the response to the console
	System.out.println("Response: " + response);
	} catch (Exception e) {
	System.err.println("Error during test execution: " + e.getMessage());
	e.printStackTrace();
	}
	}

}
