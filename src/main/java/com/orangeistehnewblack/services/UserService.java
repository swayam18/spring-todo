package com.orangeistehnewblack.services;

import com.orangeistehnewblack.models.User;
import com.orangeistehnewblack.repository.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class UserService {

    @Autowired
    private UserRepository repository;
    private User defaultUser;

    public User findOrCreateDefaultUser() {
        if(defaultUser == null) {
            defaultUser = repository.save(new User());
        }
        return defaultUser;
    }

    public User createUser(User newUser) {
        return repository.save(newUser);
    }
}
