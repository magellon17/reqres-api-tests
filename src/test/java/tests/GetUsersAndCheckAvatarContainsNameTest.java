package tests;

import models.UserPojo;
import org.junit.jupiter.api.Test;
import steps.UserSteps;

import java.util.List;

import static org.junit.jupiter.api.Assertions.assertTrue;

public class GetUsersAndCheckAvatarContainsNameTest {

    @Test
    public void checkAvatarContainsIdTest() {
        List<UserPojo> users = UserSteps.getUsers();
        assertTrue(users.stream()
                .allMatch(user -> user.getAvatar()
                        .contains(user.getId().toString())));
    }
}
