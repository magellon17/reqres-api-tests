package tests;

import io.restassured.builder.RequestSpecBuilder;
import io.restassured.http.ContentType;
import io.restassured.specification.RequestSpecification;

public abstract class BaseApiTest {

    protected final String BASE_URL = "https://reqres.in";

    protected final RequestSpecification REQ_SPEC = new RequestSpecBuilder()
            .setBaseUri(BASE_URL)
            .setContentType(ContentType.JSON)
            .build();
}
