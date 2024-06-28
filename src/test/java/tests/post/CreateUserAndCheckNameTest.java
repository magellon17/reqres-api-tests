package tests.post;

import models.CreateUserRequest;
import models.CreateUserResponse;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.Tag;
import org.junit.jupiter.api.Test;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import steps.UserSteps;
import utils.CreateUserRequestGenerator;

import static io.restassured.RestAssured.given;
import static org.junit.jupiter.api.Assertions.assertEquals;

public class CreateUserAndCheckNameTest extends BasePostTest {

    private static final Logger log = LoggerFactory.getLogger(CreateUserAndCheckNameTest.class);

    private final CreateUserRequest req = CreateUserRequestGenerator.getCreateRandomUserRequest();

    @Test
    @Tag("POST")
    public void createUser() {
        log.info("Создаем пользователя {}", req.getName());
        CreateUserResponse response = given()
                .spec(REQ_SPEC)
                .basePath("/api/users")
                .body(req)
                .when()
                .post()
                .then()
                .spec(RESP_SPEC)
                .extract()
                .as(CreateUserResponse.class);;
        assertEquals(response.getName(), req.getName(),
                "Имя созданного пользователя не совпало с ожидаемым");
    }

    @AfterEach
    public void cleanUp() {
        log.info("Удаляем созданного пользователя {}", req.getName());
        UserSteps.deleteUser(req.getName());
    }

}
