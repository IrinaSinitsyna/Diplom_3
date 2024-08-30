package api;

import io.restassured.RestAssured;
import io.restassured.http.Header;
import io.restassured.http.Headers;
import io.restassured.response.Response;
import model.CreateUserRequest;
import model.CreateUserResponse;
import model.User;

import static io.restassured.RestAssured.given;
import static org.apache.http.HttpStatus.SC_ACCEPTED;
import static org.apache.http.HttpStatus.SC_OK;
import static org.junit.jupiter.api.Assertions.*;
import static util.Utils.MAIN_PAGE;

public class ApiClient {

    private static final String BASE_URL = MAIN_PAGE + "/api";
    private static final String USER_CREATION_PATH = "/auth/register";
    private static final String USER_PATH = "/auth/user";
    private static final String LOGIN_PATH = "/auth/login";

    static {
        RestAssured.baseURI = BASE_URL;
    }

    public static CreateUserResponse registerUser(String email, String password, String name) {
        return createUserSuccessfully(email, password, name);
    }

    public static void deleteUser(String token) {
        given()
                .headers(
                        new Headers(
                                new Header("Content-type", "application/json"),
                                new Header("Authorization", token)
                        )
                )
                .and()
                .when()
                .delete(USER_PATH)
                .then().statusCode(SC_ACCEPTED);
    }

    public static CreateUserResponse login(String email, String password) {
        CreateUserRequest createUserRequest = new CreateUserRequest(email, password, null);
        return post(LOGIN_PATH, createUserRequest).as(CreateUserResponse.class);
    }

    private static CreateUserResponse createUserSuccessfully(String email, String password, String name) {
        CreateUserRequest createUserRequest = new CreateUserRequest(
                email,
                password,
                name
        );

        Response response = post(USER_CREATION_PATH, createUserRequest);

        return validateSuccessfulUserCreation(email, name, response);
    }

    private static <T> Response post(String path, T body) {
        return given()
                .header("Content-type", "application/json")
                .and()
                .body(body)
                .when()
                .post(path);
    }

    private static CreateUserResponse validateSuccessfulUserCreation(String email,
                                                                     String name,
                                                                     Response response) {
        response.then().assertThat().statusCode(SC_OK);
        CreateUserResponse createUserResponse = response.as(CreateUserResponse.class);
        User user = createUserResponse.getUser();
        assertTrue(createUserResponse.getSuccess());
        assertFalse(createUserResponse.getAccessToken().isBlank());
        assertFalse(createUserResponse.getRefreshToken().isBlank());
        assertNull(createUserResponse.getMessage());
        assertEquals(user.getEmail(), email);
        assertEquals(user.getName(), name);
        return createUserResponse;
    }
}


