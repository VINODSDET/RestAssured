package utils;

import com.fasterxml.jackson.core.PrettyPrinter;

import static io.restassured.RestAssured.given;

//day 3
public class PathAndQueryParameter {

    //this will allow you to pass path and query as parameter
    public void testQueryAndPathParameter() {
        //https://reqres.in/api/user?page=2&id=7

        given()
                .pathParams("path","user")
                .queryParam("page",2)
                .queryParam("id",7)
                .when()
                .get("https://reqres.in/api/{path}")
                .then()
                .statusCode(200)
                .log().body();
    }



    public static void main(String[] args) {
        PathAndQueryParameter pathAndQueryParameter = new PathAndQueryParameter();
        pathAndQueryParameter.testQueryAndPathParameter();
    }
}
