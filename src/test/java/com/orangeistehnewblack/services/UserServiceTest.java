package com.orangeistehnewblack.services;

import com.orangeistehnewblack.models.User;
import com.orangeistehnewblack.repository.UserRepository;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.runners.MockitoJUnitRunner;

import static org.junit.Assert.*;
import static org.mockito.Mockito.*;
import static org.hamcrest.core.Is.is;

@RunWith(MockitoJUnitRunner.class)
public class UserServiceTest {
    @Mock private UserRepository mockRepository;
    @InjectMocks private UserService userService = new UserService();

    private User newUser;

    @Before
    public void setUp() throws Exception {
        newUser = new User();
        when(mockRepository.save(newUser)).thenReturn(newUser);
    }

    @Test
    public void testCreateUser() throws Exception {
        assertThat(userService.createUser(newUser), is(newUser));
        verify(mockRepository).save(newUser);
    }
}
