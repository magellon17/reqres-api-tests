package tests;

import models.CreateUserRequest;
import models.CreateUserResponse;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import steps.UserSteps;
import utils.CreateUserRequestGenerator;

public class CreateUserAndCheckNameTest {

    private static final Logger log = LoggerFactory.getLogger(CreateUserAndCheckNameTest.class);

    private final CreateUserRequest req = CreateUserRequestGenerator.getCreateRandomUserRequest();

    @Test
    public void createUser() {
        log.info("Создаем пользователя {}", req.getName());
        CreateUserResponse response = UserSteps.createUser(req);
        Assertions.assertEquals(response.getName(),
                req.getName(),
                "Имя созданного пользователя не совпало с ожидаемым");
    }

    @AfterEach
    public void cleanUp() {
        log.info("Удаляем созданного пользователя {}", req.getName());
        UserSteps.deleteUser(req.getName());
    }

}
