package utils;

import models.CreateUserRequest;
import models.UpdateUserRequest;
import net.bytebuddy.utility.RandomString;

public class UserRequestGenerator {
    public static CreateUserRequest getCreateRandomUserRequest() {
        return CreateUserRequest.builder()
                .name(RandomString.make(6))
                .job(RandomString.make(5))
                .build();
    }

    public static CreateUserRequest getCreateRussianUserRequest() {
        return CreateUserRequest.builder()
                .name("Боб")
                .job("Строитель")
                .build();
    }

    public static UpdateUserRequest getUpdateUserNameRequest() {
        return UpdateUserRequest.builder()
                .name(RandomString.make(8))
                .build();
    }

    public static UpdateUserRequest getUpdateUserJobRequest() {
        return UpdateUserRequest.builder()
                .job(RandomString.make(8))
                .build();
    }
}
