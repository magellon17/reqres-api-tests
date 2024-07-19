package tests.put;

import models.CreateUserResponse;
import models.UpdateUserRequest;
import models.UpdateUserResponse;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Tag;
import org.junit.jupiter.api.Test;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import tests.ApiTest;
import utils.EndPoints;
import utils.UsersUtils;

import static io.restassured.RestAssured.given;
import static org.junit.jupiter.api.Assertions.assertEquals;
import static utils.Specifications.requestSpec;
import static utils.Specifications.responseSpecOK200;
import static utils.UserRequestGenerator.*;

public class UpdateUserJobAndCheckJobTest extends ApiTest {

    private static final Logger log = LoggerFactory.getLogger(UpdateUserJobAndCheckJobTest.class);

    private static final UpdateUserRequest updateUserJobRequest = getUpdateUserJobRequest(); ;

    private static CreateUserResponse createdUser;

    @BeforeEach
    public void setUp() {
        log.info("Создаем тестового пользователя");
        createdUser = UsersUtils.createUser(getCreateRandomUserRequest());
    }

    @Tag("PUT")
    @Test
    public void updateUserNameAndCheckNameTest() {
        log.info("Создаем пользователя {}", updateUserJobRequest.getJob());
        UpdateUserResponse updateUserJobResponse = given()
                .spec(requestSpec(BASE_URL))
                .body(updateUserJobRequest)
                .pathParam("id", createdUser.getId())
                .put(EndPoints.updateUsers)
                .then()
                .spec(responseSpecOK200())
                .log().all()
                .extract().as(UpdateUserResponse.class);
        assertEquals(updateUserJobRequest.getJob(), updateUserJobResponse.getJob(),
                "Не обновилось значение занятости пользователя");
    }

    @AfterEach
    public void cleanUp() {
        log.info("Удаляем созданного пользователя с занятостью {}", createdUser.getJob());
        UsersUtils.deleteUser(createdUser.getId());
    }
}
