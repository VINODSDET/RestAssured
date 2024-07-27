package utils;

import io.restassured.RestAssured;
import io.restassured.response.Response;
import io.restassured.response.ResponseBody;
import org.testng.annotations.Test;

public class Information {

    @Test
    public void getInformation(){

        String response = RestAssured
                .given()
                .contentType("application/json")
                .when()
                .get("http://localhost:3000/information")
                .getBody().prettyPeek().asPrettyString();
        System.out.println(response);
    }

        public static void main(String[] args) {

        String str[] = {"asbcr89", "6hds5","9ls4g"};
            int result = 0; //89 //7123/
            for (int i = 0; i < str.length; i++) {
                System.out.println(str[i].replaceAll("[^\\d.]", ""));
                result = result + Integer.parseInt(str[i].replaceAll("[^\\d.]", ""));
                System.out.println(result);
            }

            System.out.println("sum of three numbers " + result);

        }


}
