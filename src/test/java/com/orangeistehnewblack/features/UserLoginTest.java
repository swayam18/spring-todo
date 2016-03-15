package com.orangeistehnewblack.features;

import com.orangeistehnewblack.models.User;
import org.junit.Before;
import org.junit.Test;

import static org.hamcrest.MatcherAssert.assertThat;
import static org.hamcrest.Matchers.containsString;

public class UserLoginTest extends BaseFeatureTest {
    private String userName;
    private String userPassword;
    private String userEmail;

    @Before
    public void setUp() {
        todoRepository.deleteAll();
        userRepository.deleteAll();
        userName = "Frank Z";
        userEmail = "me@example.com";
        userPassword = "123456";

        User user = new User();
        user.setEmail(userEmail);
        user.setPassword(userPassword);
        user.setName(userName);
        userRepository.save(user);
    }

    @Test
    public void userCanLogin() {
        String url = String.format("http://localhost:%s/users/signIn", port);
        goTo(url);
        fill("#email").with(userEmail);
        fill("#password").with(userPassword);
        submit("#login");

        assertThat(find(".username").getText(), containsString(userName));
    }
}
