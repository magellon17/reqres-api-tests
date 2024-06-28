package steps;

import io.restassured.builder.RequestSpecBuilder;
import io.restassured.http.ContentType;
import io.restassured.specification.RequestSpecification;

public class BaseSteps {
    protected static final String BASE_URL = "https://reqres.in/api";
    protected static final RequestSpecification REQ_SPEC = new RequestSpecBuilder()
            .setBaseUri(BASE_URL)
            .setContentType(ContentType.JSON)
            .build();
}
