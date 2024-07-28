package utils;


import io.restassured.RestAssured;
import org.json.JSONObject;
import org.json.JSONTokener;
import org.openqa.selenium.support.PageFactory;
import org.json.simple.parser.*;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.util.Map;

import static io.restassured.RestAssured.given;

public class PostRequestWithDiffPayload {

    public String url = "https://reqres.in/api/users";

    Pojo pojo;

    public PostRequestWithDiffPayload(){
//        super();
    }



    //post request by using map payload
    public void postRequestWithMap(Map<String,String> map) {

        given()
                .contentType("application/json")
                .body(map)
                .when()
                .post("https://reqres.in/api/users")
                .then()
                .statusCode(201)
                .log().body();
    }

    //post request using org.json
    public void postRequestUsingOrgJson() {

        JSONObject jsonObject = new JSONObject();
        jsonObject.put("email", "vodhgoGoh123@gmail.com");
        jsonObject.put("first_name", "Ovi");
        jsonObject.put("last_name", "RK");
        jsonObject.put("avatar", "https://reqres.in/img/faces/7-image.jpg");
        RestAssured.given()
                .contentType("application/json")
                .body(jsonObject.toString())
                .when()
                .post("https://reqres.in/api/users")
                .then()
                .statusCode(201)
                .log().body();
    }

    public void postRequestUsingPojoClass() {
        pojo = new Pojo();
        pojo.setFirstName("Chong");
        pojo.setLastName("Jong");
        pojo.setMail("kinmJong@gmail.com");
        pojo.setAvatar("https://reqres.in/img/faces/7-image.jpg");
        pojo.setMobileNumber(9740737947L);
        pojo.setAddress("123, ching woing lane,China");
        pojo.setPinCode("7234153");

        given()
                .contentType("application/json")
                .body(pojo)
                .when()
                .post(url)
                .then()
                .statusCode(201)
                .log().all();
    }

    public void postRequestUsingJsonFile(String path) throws FileNotFoundException {
        File file = new File(path);
        FileReader fileReader = new FileReader(file);

        JSONObject jsonObject = new JSONObject(new JSONTokener(fileReader));
        given()
                .contentType("application/json")
                .body(jsonObject.toString())
                .when()
                .post(url)
                .then()
                .statusCode(201)
                .log().body();
    }
    public static void main(String[] args) throws FileNotFoundException {
        PostRequestWithDiffPayload post = new PostRequestWithDiffPayload();
        Map<String, String> map = Map.of("email", "vinodgore007@gmail.com",
                "first_name", "Vinod",
                "last_name", "Gore",
                "avatar", "https://reqres.in/img/faces/7-image.jpg");

        post.postRequestWithMap(map);
        post.postRequestUsingOrgJson();
        post.postRequestUsingPojoClass();
        post.postRequestUsingJsonFile("src/test/java/utils/payload.json");
    }

}
