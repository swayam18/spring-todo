package com.orangeistehnewblack.features;

import com.orangeistehnewblack.TodoApplication;
import com.orangeistehnewblack.models.User;
import com.orangeistehnewblack.repository.TodoRepository;
import com.orangeistehnewblack.repository.UserRepository;
import org.fluentlenium.adapter.FluentTest;
import org.junit.runner.RunWith;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.phantomjs.PhantomJSDriver;
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
public abstract class BaseFeatureTest extends FluentTest{
    WebDriver webDriver = new PhantomJSDriver();

    @Override
    public WebDriver getDefaultDriver() {
        return webDriver;
    }

    @Autowired
    protected TodoRepository todoRepository;

    @Autowired
    protected UserRepository userRepository;

    @Value("${local.server.port}")
    protected int port;

    protected void clearDatabase() {
        todoRepository.deleteAll();
        userRepository.deleteAll();
    }

    protected User prepareUser(String userName, String userEmail, String userPassword) {
        User user = new User();
        user.setEmail(userEmail);
        user.setPassword(userPassword);
        user.setName(userName);
        return userRepository.save(user);
    }

    protected void login(String userEmail, String userPassword) {
        String url = "http://localhost:" + port + "/users/signIn";
        goTo(url);
        fill("#email").with(userEmail);
        fill("#password").with(userPassword);
        submit("#login");
    }
}
