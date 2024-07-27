package utils;

import io.restassured.response.Response;

import java.util.Map;

import static io.restassured.RestAssured.given;

public class GetCookiesInfo {

    public void getCookies() {

        Response response = given()
                .contentType("application/json")
                .when()
                .get("https://www.facebook.com/");

        Map<String, String> cookies = response.getCookies();
        System.out.println(cookies);
        String jsonResponce = response.getBody().asPrettyString().toString();
        System.out.println(jsonResponce);
    }

    public static void main(String[] args) {
        GetCookiesInfo cookiesInfo = new GetCookiesInfo();
        cookiesInfo.getCookies();
    }
}
