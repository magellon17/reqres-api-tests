package tests;

import io.restassured.http.ContentType;
import models.UserPojo;
import org.junit.jupiter.api.Test;

import java.util.List;

import static io.restassured.RestAssured.given;
import static org.junit.jupiter.api.Assertions.assertTrue;

public class GetListUsersTest extends APITest {

    @Test
    public void checkAvatarContainsIdTest() {
        List<UserPojo> users = given()
                .when()
                .contentType(ContentType.JSON)
                .get(BASE_URL + "api/users?page=2")
                .then()
                .extract().body().jsonPath().getList("data", UserPojo.class);
        assertTrue(users.stream().allMatch(user -> user.getAvatar().contains(user.getId().toString())));
    }
}
