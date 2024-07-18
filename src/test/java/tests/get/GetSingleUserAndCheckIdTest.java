package tests.get;

import models.User;
import org.junit.jupiter.api.Tag;
import org.junit.jupiter.api.Test;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import tests.ApiTest;

import static io.restassured.RestAssured.given;
import static org.junit.jupiter.api.Assertions.assertEquals;
import static utils.Specifications.requestSpec;
import static utils.Specifications.responseSpecOK200;

public class GetSingleUserAndCheckIdTest extends ApiTest {

    private static final Logger log = LoggerFactory.getLogger(GetSingleUserAndCheckIdTest.class);

    private static final String USER_ID = "2";

    @Test
    @Tag("GET")
    public void getSingleUserAndCheckIdTest() {
        log.info("Получаем пользователя");
        User user = given()
                .when()
                .spec(requestSpec(BASE_URL))
                .get("/api/users/" + USER_ID)
                .then()
                .spec(responseSpecOK200())
                .log().all()
                .extract().jsonPath().getObject("data", User.class);

        log.info("Сверяем полученный id пользователя с ожидаемым");
        assertEquals(USER_ID, user.getId());
    }
}
