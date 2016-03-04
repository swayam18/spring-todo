package com.orangeistehnewblack.models;

import org.junit.Before;
import org.junit.Test;

import static org.junit.Assert.*;
import static org.hamcrest.core.Is.*;
import static com.orangeistehnewblack.matchers.IsBcryptEncodedFrom.bcryptEncodedFrom;

public class UserTest {
    private User subject;

    @Before
    public void setUp() throws Exception {
        subject = new User();
    }

    @Test
    public void testSetPassword() throws Exception {
        String testPassword = "test password";
        subject.setPassword(testPassword);

        assertThat(subject.getPassword(), is(bcryptEncodedFrom(testPassword)));
    }
}

