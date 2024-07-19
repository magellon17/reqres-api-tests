package tests.post;

import models.CreateUserRequest;
import models.CreateUserResponse;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.Tag;
import org.junit.jupiter.api.extension.ExtensionContext;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.Arguments;
import org.junit.jupiter.params.provider.ArgumentsProvider;
import org.junit.jupiter.params.provider.ArgumentsSource;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import utils.UsersUtils;
import tests.ApiTest;
import utils.EndPoints;

import java.util.stream.Stream;

import static io.restassured.RestAssured.given;
import static org.junit.jupiter.api.Assertions.assertEquals;
import static utils.UserRequestGenerator.getCreateRandomUserRequest;
import static utils.UserRequestGenerator.getCreateRussianUserRequest;
import static utils.Specifications.requestSpec;
import static utils.Specifications.responseSpecOK201;

public class CreateUserAndCheckJobTest extends ApiTest {

    private static final Logger log = LoggerFactory.getLogger(CreateUserAndCheckJobTest.class);

    private CreateUserResponse createUserResponse;

    @Tag("POST")
    @ParameterizedTest(name = "Пользователь: {0}")
    @ArgumentsSource(CreateUserRequests.class)
    public void createUserAndCheckJobTest(CreateUserRequest createUserRequest) {
        log.info("Создаем пользователя {}", createUserRequest.getName());
        createUserResponse = given()
                .spec(requestSpec(BASE_URL))
                .body(createUserRequest)
                .post(EndPoints.postUser)
                .then()
                .spec(responseSpecOK201())
                .log().all()
                .extract().as(CreateUserResponse.class);
        assertEquals(createUserRequest.getJob(), createUserResponse.getJob(),
                "Занятость созданного пользователя не совпала с ожидаемой");
    }

    @AfterEach
    public void cleanUp() {
        log.info("Удаляем созданного пользователя {}", createUserResponse.getName());
        UsersUtils.deleteUser(createUserResponse.getId());
    }

    static class CreateUserRequests implements ArgumentsProvider {
        @Override
        public Stream<? extends Arguments> provideArguments(ExtensionContext extensionContext) {
            return Stream.of(
                    Arguments.of(getCreateRandomUserRequest()),
                    Arguments.of(getCreateRussianUserRequest())
            );
        }
    }
}
