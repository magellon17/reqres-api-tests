package tests.get.contract;

import org.junit.jupiter.api.Tag;
import org.junit.jupiter.api.Test;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import tests.ApiTest;


import static io.restassured.RestAssured.given;
import static io.restassured.module.jsv.JsonSchemaValidator.matchesJsonSchemaInClasspath;
import static utils.Specifications.requestSpec;
import static utils.Specifications.responseSpecOK200;

public class GetUsersAndValidateJsonSchemaTest extends ApiTest {

    private static final Logger log = LoggerFactory.getLogger(GetUsersAndValidateJsonSchemaTest.class);

    @Test
    @Tag("GET")
    public void checkAvatarContainsIdTest() {
        log.info("Получаем список пользователей и делаеи валидацию схемы");
        given()
                .spec(requestSpec(BASE_URL))
                .when()
                .get("/api/users?page=2")
                .then()
                .spec(responseSpecOK200())
                .log().all()
                .body(matchesJsonSchemaInClasspath("getUsersResponseSchema.json"));
    }
}
