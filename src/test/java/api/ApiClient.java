package api;

import io.restassured.RestAssured;
import io.restassured.response.Response;

import static util.Utils.MAIN_PAGE;

public class ApiClient {

    private static final String BASE_URL = MAIN_PAGE + "/api";

    public static Response registerUser(String email, String password, String name) {
        String requestBody = String.format(
                "{\"email\": \"%s\", \"password\": \"%s\", \"name\": \"%s\"}",
                email,
                password,
                name
        );

        return RestAssured.given()
                .baseUri(BASE_URL)
                .header("Content-Type", "application/json")
                .body(requestBody)
                .post("/auth/register");
    }
}


