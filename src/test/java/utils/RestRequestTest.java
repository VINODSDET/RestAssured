package utils;

import io.restassured.RestAssured;
import io.restassured.builder.RequestSpecBuilder;
import io.restassured.response.Response;
import io.restassured.specification.RequestSpecification;

import java.util.HashMap;
import java.util.Map;

import static io.restassured.RestAssured.given;
import static org.hamcrest.Matchers.equalTo;

public class RestRequestTest {

    String uri = "https://reqres.in/api";

    public RequestSpecification requestSpecification(String uri){
        return new RequestSpecBuilder().setBaseUri(uri).setContentType("application/json").build();
    }

    public Response response(Map<String,String> map){
        return  given().spec(requestSpecification(uri)).body(map).when().post("/users/").prettyPeek();
    }

    public static void main(String[] args) {
        RestRequestTest restRequestTest = new RestRequestTest();
        Response responceData = restRequestTest.response(Map.of("em ail", "KevinPeterson@gmail.com",
                "first_name", "Kevin",
                "last_name", "Peterson",
                "avatar", "https://reqres.in/img/faces/7-image.jpg"));
        responceData.then().statusCode(201).body("first_name",equalTo("Kevin"));
    }
}
