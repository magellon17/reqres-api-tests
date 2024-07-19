package utils;

import models.CreateUserRequest;
import models.CreateUserResponse;

import static io.restassured.RestAssured.given;

public class UsersUtils {
    public static CreateUserResponse createUser(CreateUserRequest req) {
        return given()
                .baseUri("https://reqres.in")
                .body(req)
                .post("/api/users")
                .then()
                .statusCode(201)
                .extract()
                .as(CreateUserResponse.class);
    }

    public static void deleteUser(String userId) {
        given()
                .baseUri("https://reqres.in")
                .delete("/api/users/" + userId)
                .then()
                .statusCode(204);
    }
}
