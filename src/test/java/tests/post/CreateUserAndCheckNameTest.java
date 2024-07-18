package tests.post;

import models.CreateUserRequest;
import models.CreateUserResponse;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.Tag;
import org.junit.jupiter.api.Test;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import steps.UserSteps;
import tests.ApiTest;
import utils.CreateUserRequestGenerator;

import static io.restassured.RestAssured.given;
import static org.junit.jupiter.api.Assertions.assertEquals;
import static utils.Specifications.requestSpec;
import static utils.Specifications.responseSpecOK201;

public class CreateUserAndCheckNameTest extends ApiTest {

    private static final Logger log = LoggerFactory.getLogger(CreateUserAndCheckNameTest.class);

    private final CreateUserRequest createUserRequest = CreateUserRequestGenerator.getCreateRandomUserRequest();
    private CreateUserResponse createUserResponse;

    @Test
    @Tag("POST")
    public void createUserAndCheckNameTest() {
        log.info("Создаем пользователя {}", createUserRequest.getName());
        createUserResponse = given()
                .spec(requestSpec(BASE_URL))
                .body(createUserRequest)
                .when()
                .post("/api/users")
                .then()
                .spec(responseSpecOK201())
                .log().body()
                .extract().as(CreateUserResponse.class);
        assertEquals(createUserRequest.getName(), createUserResponse.getName(),
                "Имя созданного пользователя не совпало с ожидаемым");
    }

    @AfterEach
    public void cleanUp() {
        log.info("Удаляем созданного пользователя {}", createUserRequest.getName());
        UserSteps.deleteUser(createUserResponse.getId());
    }
}
