package tests.get;

import io.restassured.internal.http.Status;
import models.User;
import org.apache.http.HttpStatus;
import org.junit.jupiter.api.*;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import steps.UserSteps;
import tests.ApiTest;

import static io.restassured.RestAssured.given;
import static org.junit.jupiter.api.Assertions.assertEquals;
import static utils.Specifications.requestSpec;

public class GetNonExistentUserAndCheckUserNotFoundErrorTest extends ApiTest {

    private static final Logger log = LoggerFactory.getLogger(GetNonExistentUserAndCheckUserNotFoundErrorTest.class);

    private static final String USER_ID = "23";

    @BeforeEach
    public void cleanUp() {
        log.info("Удаляем пользователя с id {}", USER_ID);
        UserSteps.deleteUser(USER_ID);
    }

    @Test
    @Tag("GET")
    public void getNonExistentUserAndCheckUserNotFoundErrorTest() {
        log.info("Получаем пользователя с id {}", USER_ID);
        Integer statusCode = given()
                .when()
                .spec(requestSpec(BASE_URL))
                .get("/api/users/" + USER_ID)
                .then()
                .log().all()
                .extract().statusCode();
        log.info("Проверяем, что пользователь не был найден, т.е. статус код равен 404");
        assertEquals(HttpStatus.SC_NOT_FOUND, statusCode);
    }
}
