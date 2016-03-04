package com.orangeistehnewblack.services;

import com.orangeistehnewblack.models.CurrentUser;
import com.orangeistehnewblack.models.User;
import org.junit.Before;
import org.junit.Rule;
import org.junit.Test;
import org.junit.rules.ExpectedException;
import org.junit.runner.RunWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.runners.MockitoJUnitRunner;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UsernameNotFoundException;

import java.util.Optional;

import static org.junit.Assert.*;
import static org.hamcrest.core.Is.is;
import static org.mockito.Mockito.*;

@RunWith(MockitoJUnitRunner.class)
public class CurrentUserServiceTest {
    @Mock private UserService mockUserService;
    @InjectMocks private CurrentUserService subject = new CurrentUserService();

    @Rule public ExpectedException thrown = ExpectedException.none();

    User newUser;
    String existingUserEmail = "user@example.com";

    @Before
    public void setUp() throws Exception {
        newUser = new User();
        newUser.setEmail(existingUserEmail);
        newUser.setPassword("password");
    }

    @Test
    public void testLoadUserByUsername() throws Exception {
        UserDetails currentUser = new CurrentUser(newUser);
        when(mockUserService.getUserByEmail(existingUserEmail)).thenReturn(Optional.of(newUser));

        assertThat(subject.loadUserByUsername(existingUserEmail), is(currentUser));

    }

    @Test
    public void testLoadUserByUsernameWhenUserIsNotFound() throws Exception {
        String nonExistingUsersEmail = "nope@example.com";
        when(mockUserService.getUserByEmail(nonExistingUsersEmail)).thenReturn(Optional.empty());

        thrown.expect(UsernameNotFoundException.class);
        subject.loadUserByUsername(nonExistingUsersEmail);
    }
}