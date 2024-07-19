package tests.get;

import models.User;
import org.junit.jupiter.api.Tag;
import org.junit.jupiter.api.Test;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import tests.ApiTest;

import java.util.List;

import static io.restassured.RestAssured.given;
import static org.junit.jupiter.api.Assertions.assertTrue;
import static utils.Specifications.requestSpec;
import static utils.Specifications.responseSpecOK200;

public class GetUsersAndCheckAvatarContainsIdTest extends ApiTest {

    private static final Logger log = LoggerFactory.getLogger(GetUsersAndCheckAvatarContainsIdTest.class);

    @Test
    @Tag("GET")
    public void getUsersAndCheckAvatarContainsIdTest() {
        log.info("Получаем список пользователей");
        List<User> users = given()
                .spec(requestSpec(BASE_URL))
                .get("/api/users?page=2")
                .then()
                .spec(responseSpecOK200())
                .log().all()
                .extract().jsonPath().getList("data", User.class);
        log.info("Проверяем наличие id пользователя в ссылке на аватар");
        assertTrue(users.stream().allMatch(user -> user.getAvatar().contains(user.getId())),
                "Id пользователя не совпал с id в ссылке на его аватар");
    }
}
