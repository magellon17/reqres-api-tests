package tests.get;

import models.User;
import org.junit.jupiter.api.Tag;
import org.junit.jupiter.api.Test;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.util.List;

import static io.restassured.RestAssured.given;
import static org.junit.jupiter.api.Assertions.assertTrue;

public class GetUsersAndCheckAvatarContainsIdTest extends BaseGetTest {

    private static final Logger log = LoggerFactory.getLogger(GetUsersAndCheckAvatarContainsIdTest.class);

    @Test
    @Tag("GET")
    public void getUsersAndCheckAvatarContainsIdTest() {
        log.info("Получаем список пользователей");
        List<User> users = given()
                .spec(REQ_SPEC)
                .basePath("/api/users")
                .param("page", "2")
                .when()
                .get()
                .then()
                .spec(RESP_SPEC)
                .extract()
                .jsonPath()
                .getList("data", User.class);
        log.info("Проверяем наличие id пользователя в ссылке на аватар");
        assertTrue(users.stream()
                .allMatch(user -> user.getAvatar().contains(user.getId().toString())));
    }
}
