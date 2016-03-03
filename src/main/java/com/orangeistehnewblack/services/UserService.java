package com.orangeistehnewblack.services;

import com.orangeistehnewblack.models.User;
import com.orangeistehnewblack.repository.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Optional;

@Service
public class UserService {

    @Autowired
    private UserRepository repository;
    private User defaultUser;

    public User findOrCreateDefaultUser() {
        if(defaultUser == null) {
            User user = new User();
            user.setEmail("f@l");
            user.setName("first last");
            user.setPassword("p");
            defaultUser = repository.save(user);
        }
        return defaultUser;
    }

    public User createUser(User newUser) {
        return repository.save(newUser);
    }

    public Optional<User> getUserByEmail(String email) {
        return repository.findOneByEmail(email);
    }
}
