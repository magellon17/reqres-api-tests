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
import utils.UsersUtils;
import tests.ApiTest;
import utils.EndPoints;

import static io.restassured.RestAssured.given;
import static org.junit.jupiter.api.Assertions.assertEquals;
import static utils.Specifications.*;
import static utils.UserRequestGenerator.*;

public class UpdateUserNameAndCheckNameTest extends ApiTest {

    private static final Logger log = LoggerFactory.getLogger(UpdateUserNameAndCheckNameTest.class);

    private static final UpdateUserRequest updateUserNameRequest = getUpdateUserNameRequest(); ;

    private static CreateUserResponse createdUser;

    @BeforeEach
    public void setUp() {
        log.info("Создаем тестового пользователя");
        createdUser = UsersUtils.createUser(getCreateRandomUserRequest());
    }

    @Tag("PUT")
    @Test
    public void updateUserNameAndCheckNameTest() {
        log.info("Создаем пользователя {}", updateUserNameRequest.getName());
        UpdateUserResponse updateUserNameResponse = given()
                .spec(requestSpec(BASE_URL))
                .body(updateUserNameRequest)
                .pathParam("id", createdUser.getId())
                .put(EndPoints.updateUsers)
                .then()
                .spec(responseSpecOK200())
                .log().all()
                .extract().as(UpdateUserResponse.class);
        assertEquals(updateUserNameRequest.getName(), updateUserNameResponse.getName(),
                "Не обновилось значение имени пользователя");
    }

    @AfterEach
    public void cleanUp() {
        log.info("Удаляем созданного пользователя с именем {}", createdUser.getName());
        UsersUtils.deleteUser(createdUser.getId());
    }
}
