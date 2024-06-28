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

public class CreateUserAndCheckJobTest {

    private static final Logger log = LoggerFactory.getLogger(CreateUserAndCheckJobTest.class);

    private final CreateUserRequest req = CreateUserRequestGenerator.getCreateRandomUserRequest();

    @Test
    public void createUser() {
        log.info("Создаем пользователя {}", req.getName());
        CreateUserResponse resp = UserSteps.createUser(req);
        Assertions.assertEquals(resp.getJob(),
                req.getJob(),
                "Занятость созданного пользователя не совпала с ожидаемой");
    }

    @AfterEach
    public void cleanUp() {
        log.info("Удаляем созданного пользователя {}", req.getName());
        UserSteps.deleteUser(req.getName());
    }
}
