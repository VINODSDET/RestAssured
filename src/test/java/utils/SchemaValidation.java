package utils;

import io.restassured.matcher.RestAssuredMatchers;
import io.restassured.module.jsv.JsonSchemaValidator;
import org.testng.annotations.Test;

import java.io.File;

import static io.restassured.RestAssured.given;

public class SchemaValidation {


    @Test
    public void testSchemaValidation() {
        given()
                .pathParam("path", "user")
                .contentType("application/json")
                .when()
                .get("https://reqres.in/api/{path}")
                .then()
                .assertThat().body(JsonSchemaValidator.matchesJsonSchema(new File("src/test/java/utils/UsersSchema.json")))
                .log().all();
    }

    @Test
    public void xmlSchemaValidation() {
       // Setup Request:
        given() // : Starts building the request.
                .pathParam("path", "authors.xml")//: Sets a path parameter named path with the value authors.xml.
                .contentType("application/xml")//: Specifies that the request content type is application/xml.
                //Send Request:
                .when()//: Specifies the action to perform.
                .get("https://thetestrequest.com/{path}")//: Sends a GET request to the URL https://thetestrequest.com/authors.xml (the {path} placeholder is replaced by the value of the path parameter).
        //Validate Response:
                .then()//: Starts the response validation.
                .assertThat().body(RestAssuredMatchers.matchesXsd(new File("src/test/java/utils/output.xsd")))
                //: Asserts that the response body matches the XML schema defined in the output.xsd file.
        .log().body();
        //: Logs the response body for inspection.
    }

}
