package com.orangeistehnewblack.models;

import org.junit.Test;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;

import static org.junit.Assert.*;
import static org.hamcrest.core.Is.*;
import static com.orangeistehnewblack.matchers.IsBcryptEncodedFrom.bcryptEncodedFrom;

public class UserTest {

    @Test
    public void testSetPassword() throws Exception {
        String testPassword = "test password";

        User testUser = new User();
        testUser.setPassword(testPassword);

        assertThat(testUser.getPassword(), is(bcryptEncodedFrom(testPassword)));
    }
}

