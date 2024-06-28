package tests;

import org.junit.jupiter.api.Tag;
import org.junit.jupiter.api.Test;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;


import static io.restassured.RestAssured.given;
import static io.restassured.module.jsv.JsonSchemaValidator.matchesJsonSchemaInClasspath;

public class ContractTest extends BaseGetTest {

    private static final Logger log = LoggerFactory.getLogger(ContractTest.class);

    @Test
    @Tag("GET")
    public void checkAvatarContainsIdTest() {
        log.info("Получаем список пользователей и делаеи валидацию схемы");
        given()
                .spec(REQ_SPEC)
                .basePath("/api/users")
                .param("page", "2")
                .when()
                .get()
                .then()
                .spec(RESP_SPEC)
                .log()
                .body()
                .body(matchesJsonSchemaInClasspath("getUsersResponseSchema.json"));
    }
}
