package steps;

import models.CreateUserRequest;
import models.CreateUserResponse;

import static io.restassured.RestAssured.given;

public class UserSteps {

    public static CreateUserResponse createUser(CreateUserRequest req) {
        return given()
                .baseUri("https://reqres.in/api")
                .body(req)
                .when()
                .post("/users")
                .then()
                .statusCode(201)
                .extract()
                .as(CreateUserResponse.class);
    }

    public static void deleteUser(String userId) {
        given()
                .baseUri("https://reqres.in")
                .when()
                .delete("/api/users/" + userId)
                .then()
                .statusCode(204);
    }
}
