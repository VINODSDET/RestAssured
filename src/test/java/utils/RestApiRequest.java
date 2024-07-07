package utils;

import io.restassured.RestAssured;
import org.json.JSONObject;
import org.testng.annotations.Test;

import java.util.HashMap;
import java.util.Map;

import static io.restassured.RestAssured.given;

public class RestApiRequest {

    int id;

    @Test(priority = 1)
    public void getResponseRequest(){
        given()
                .contentType("application/json")
                .when()
                .get("https://reqres.in/api/users/2")
                .then()
                .statusCode(200)
                .log().all();
    }

    @Test(priority = 2)
    public void postRequest(){
        Map<String, String> map = Map.of("email", "vinodgore007@gmail.com",
                "first_name", "Vinod",
                "last_name", "Gore",
                "avatar", "https://reqres.in/img/faces/7-image.jpg");

        String response = RestAssured
                .given()
                .contentType("application/json")
                .body(map)
                .when()
                .post("https://reqres.in/api/users")
                .getBody().asPrettyString();
        System.out.println(response);
        JSONObject jsonObject = new JSONObject(response);
        id = Integer.parseInt(jsonObject.get("id").toString());
    }

    @Test(dependsOnMethods = "postRequest")
    public void updateRequest(){
        Map<String, String> map =  Map.of("email", "vinodgore007@gmail.com",
                "first_name", "Vinod",
                "last_name", "Gore",
                "avatar", "https://www.freepik.com/free-photos-vectors/vibrant");

        given()
                .contentType("application/json")
                .body(map)
                .when()
                .put("https://reqres.in/api/users/" + id)
                .then()
                .statusCode(200)
                .log().all();
    }

    @Test(dependsOnMethods = "updateRequest")
    public void deleteRequest(){

        RestAssured
                .when()
                .delete("https://reqres.in/api/users/" + id)
                .then()
                .statusCode(204)
                .log().all();
    }
}
