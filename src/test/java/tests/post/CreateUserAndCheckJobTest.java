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

public class CreateUserAndCheckJobTest extends ApiTest {

    private static final Logger log = LoggerFactory.getLogger(CreateUserAndCheckJobTest.class);

    private final CreateUserRequest req = CreateUserRequestGenerator.getCreateRandomUserRequest();
    private CreateUserResponse resp;

    @Test
    @Tag("POST")
    public void createUserAndCheckJobTest() {
        log.info("Создаем пользователя {}", req.getName());
        resp = given()
                .body(req)
                .when()
                .spec(requestSpec(BASE_URL))
                .post("/api/users")
                .then()
                .spec(responseSpecOK201())
                .log().all()
                .extract().as(CreateUserResponse.class);
        assertEquals(req.getJob(), resp.getJob(),
                "Занятость созданного пользователя не совпала с ожидаемой");
    }

    @AfterEach
    public void cleanUp() {
        log.info("Удаляем созданного пользователя {}", req.getName());
        UserSteps.deleteUser(resp.getId());
    }
}
