package com.orangeistehnewblack.controllers;

import com.orangeistehnewblack.TodoApplication;
import com.orangeistehnewblack.repository.TodoRepository;
import com.orangeistehnewblack.repository.UserRepository;
import org.junit.Ignore;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.boot.test.IntegrationTest;
import org.springframework.boot.test.SpringApplicationConfiguration;
import org.springframework.test.context.ActiveProfiles;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;
import org.springframework.test.context.web.WebAppConfiguration;

@RunWith(SpringJUnit4ClassRunner.class)
@SpringApplicationConfiguration(classes = TodoApplication.class)
@WebAppConfiguration
@IntegrationTest("server.port:0")
@ActiveProfiles(profiles = "test")
public abstract class BaseController {
    @Autowired
    protected TodoRepository todoRepository;

    @Autowired
    protected UserRepository userRepository;

    @Value("${local.server.port}")
    protected int port;
}
