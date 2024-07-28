package utils;

import io.restassured.builder.RequestSpecBuilder;
import io.restassured.http.ContentType;
import io.restassured.specification.RequestSpecification;
import org.testng.annotations.Test;

import java.io.File;

import static io.restassured.RestAssured.given;

public class FileUpload {

   public RequestSpecification requestSpecification() {
    return new RequestSpecBuilder()
            .setBaseUri("https://api.escuelajs.co/api/v1/files/upload")
            .setContentType(ContentType.MULTIPART)
            .build();
}

@Test
public void singleFileUpload() {
    File file = new File("src/test/java/utils/Information.json");
    given()
            .spec(requestSpecification())
            .multiPart("file", file)
            .when()
            .post()
            .then()
            .statusCode(201)
            .log().body();
}
}
