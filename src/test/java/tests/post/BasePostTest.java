package tests.post;

import io.restassured.builder.ResponseSpecBuilder;
import io.restassured.specification.ResponseSpecification;
import tests.BaseApiTest;

import static org.hamcrest.Matchers.hasKey;
import static org.hamcrest.Matchers.lessThan;

public abstract class BasePostTest extends BaseApiTest {

    protected final ResponseSpecification RESP_SPEC = new ResponseSpecBuilder()
            .expectStatusCode(201)
            .expectResponseTime(lessThan(2000L))
            .build();

}
