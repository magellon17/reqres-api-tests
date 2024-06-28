package tests;

import io.restassured.builder.ResponseSpecBuilder;
import io.restassured.specification.ResponseSpecification;

import static org.hamcrest.Matchers.hasKey;
import static org.hamcrest.Matchers.lessThan;

public class BaseGetTest extends BaseApiTest {

    protected final ResponseSpecification RESP_SPEC = new ResponseSpecBuilder()
            .expectStatusCode(200)
            .expectResponseTime(lessThan(2000L))
            .expectBody("$", hasKey("data"))
            .build();
}
