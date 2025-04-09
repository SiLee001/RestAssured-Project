package com.example.RestAssured;
import org.testng.annotations.Test;
import static org.hamcrest.Matchers.equalTo;
import io.restassured.RestAssured;
public class XMLResponse {
	@Test
    public void testXmlResponse() {
        RestAssured
            .given()
            .baseUri("https://www.w3schools.com/xml/note.xml")
            .when()
            .get()
            .then()
            .statusCode(200)
            .body("note.to", equalTo("Tove"));
    }

}
