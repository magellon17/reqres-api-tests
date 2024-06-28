package steps;

import io.restassured.internal.http.Status;
import models.CreateUserRequest;
import models.CreateUserResponse;
import models.UserPojo;
import org.apache.http.HttpStatus;

import java.util.List;

import static io.restassured.RestAssured.given;
import static org.hamcrest.Matchers.lessThan;

public class UserSteps extends BaseSteps {
    public static List<UserPojo> getUsers(){
        return given().spec(REQ_SPEC)
                .get("/users/?page=2")
                .then()
                .statusCode(200)
                .time(lessThan(5000L))
                .extract()
                .jsonPath()
                .getList("data", UserPojo.class);
    }

    public static CreateUserResponse createUser(CreateUserRequest request){
        CreateUserResponse response = given().spec(REQ_SPEC)
                .body(request)
                .post("/users")
                .then()
                .statusCode(201)
                .time(lessThan(5000L))
                .extract()
                .as(CreateUserResponse.class);
        return response;
    }

    public static void deleteUser(String user){
        given().spec(REQ_SPEC).delete("/users/" + user);
    }
}
