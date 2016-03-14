package com.orangeistehnewblack.controllers.api;

import com.jayway.restassured.authentication.FormAuthConfig;
import com.orangeistehnewblack.TodoApplication;
import com.orangeistehnewblack.models.Todo;
import com.orangeistehnewblack.models.User;
import com.orangeistehnewblack.repository.TodoRepository;
import com.orangeistehnewblack.repository.UserRepository;
import org.apache.http.HttpStatus;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.boot.test.IntegrationTest;
import org.springframework.boot.test.SpringApplicationConfiguration;
import org.springframework.test.context.ActiveProfiles;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;
import org.springframework.test.context.web.WebAppConfiguration;

import java.util.Arrays;

import static com.jayway.restassured.RestAssured.given;
import static org.hamcrest.Matchers.containsString;
import static org.hamcrest.Matchers.hasItems;

@RunWith(SpringJUnit4ClassRunner.class)
@SpringApplicationConfiguration(classes = TodoApplication.class)
@WebAppConfiguration
@IntegrationTest("server.port:0")
@ActiveProfiles(profiles = "test")
public class ApiToDoControllerTest {
    @Autowired
    TodoRepository repository;

    @Autowired
    UserRepository userRepository;

    @Value("${local.server.port}")
    private int port;

    private Todo task1;
    private Todo task2;

    @Before
    public void setUp() {
        repository.deleteAll();
        userRepository.deleteAll();

        User user = new User();
        user.setEmail("me@example.com");
        user.setPassword("123456");
        userRepository.save(user);

        task1 = new Todo("Buy milk");
        task1.setUser(user);
        task2 = new Todo("Drink carrot juice");
        task2.setUser(user);

        repository.save(Arrays.asList(task1, task2));
    }

    @Test
    public void redirectsToSignInIfNotAuthenticated() {
        given()
                .port(port).redirects().follow(false).
                when()
                .get("/api/todo").
                then()
                .statusCode(HttpStatus.SC_MOVED_TEMPORARILY)
                .header("Location", containsString("/users/signIn"));
    }

    @Test
    public void canFetchAllTodos() {
        String password = "123456";
        given()
            .port(port).auth().form("me@example.com", password, new FormAuthConfig("/users/signIn", "email", "password")).
        when().
            get("/api/todo").
        then().
            statusCode(HttpStatus.SC_OK).
            body("task", hasItems("Buy milk", "Drink carrot juice"));
    }
}
