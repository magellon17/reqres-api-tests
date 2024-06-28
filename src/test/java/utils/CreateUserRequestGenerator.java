package utils;

import models.CreateUserRequest;
import net.bytebuddy.utility.RandomString;

public class CreateUserRequestGenerator {
    public static CreateUserRequest getCreateRandomUserRequest() {
        return CreateUserRequest.builder()
                .name(RandomString.make(6))
                .job(RandomString.make(5))
                .build();
    }
}
