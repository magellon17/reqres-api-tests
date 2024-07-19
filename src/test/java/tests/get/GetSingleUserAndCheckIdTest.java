package tests.get;

import models.CreateUserResponse;
import models.User;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Tag;
import org.junit.jupiter.api.Test;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import utils.UsersUtils;
import tests.ApiTest;
import utils.EndPoints;

import static io.restassured.RestAssured.given;
import static org.junit.jupiter.api.Assertions.assertEquals;
import static utils.UserRequestGenerator.getCreateRussianUserRequest;
import static utils.Specifications.requestSpec;
import static utils.Specifications.responseSpecOK200;

public class GetSingleUserAndCheckIdTest extends ApiTest {

    private static final Logger log = LoggerFactory.getLogger(GetSingleUserAndCheckIdTest.class);

    private static CreateUserResponse createUserResponse;

    @BeforeEach
    public void setUp() {
        log.info("Создаем тестового пользователя");
        createUserResponse = UsersUtils.createUser(getCreateRussianUserRequest());
    }

    @Test
    @Tag("GET")
    public void getSingleUserAndCheckIdTest() {
        log.info("Получаем пользователя");
        User user = given()
                .spec(requestSpec(BASE_URL))
                .pathParam("id", createUserResponse.getId())
                .get(EndPoints.getUser)
                .then()
                .spec(responseSpecOK200())
                .log().all()
                .extract().jsonPath().getObject("data", User.class);
        log.info("Сверяем полученный id пользователя с ожидаемым");
        assertEquals(createUserResponse.getId(), user.getId(),
                "Id полученного пользователя не совпал с ожидаемым");
    }

    @BeforeEach
    public void cleanUp() {
        log.info("Удаляем тестового пользователя");
        UsersUtils.deleteUser(createUserResponse.getId());
    }
}
