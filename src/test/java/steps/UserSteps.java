package steps;

import models.CreateUserRequest;
import models.CreateUserResponse;

import static io.restassured.RestAssured.given;

public class UserSteps {

    public static CreateUserResponse createUser(CreateUserRequest req){
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

    public static int deleteUser(String user){
        return given()
                .baseUri("https://reqres.in")
                .basePath("/api/users/")
                .when()
                .delete(user)
                .then()
                .statusCode(204)
                .extract()
                .statusCode();
    }
}
