package com.orangeistehnewblack.controllers.api;

import com.orangeistehnewblack.TodoApplication;
import com.orangeistehnewblack.models.Todo;
import com.orangeistehnewblack.models.User;
import com.orangeistehnewblack.repository.TodoRepository;
import org.apache.http.HttpStatus;
import org.hamcrest.Matchers;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.boot.test.IntegrationTest;
import org.springframework.boot.test.SpringApplicationConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;
import org.springframework.test.context.web.WebAppConfiguration;

import java.util.Arrays;

import static com.jayway.restassured.RestAssured.*;

@RunWith(SpringJUnit4ClassRunner.class)
@SpringApplicationConfiguration(classes = TodoApplication.class)
@WebAppConfiguration
@IntegrationTest("server.port:0")
public class ToDoControllerTest {
    @Autowired
    TodoRepository repository;

    @Value("${local.server.port}")
    int port;

    private Todo task1;
    private Todo task2;

    @Before
    public void setUp() {
        task1 = new Todo("Buy milk");
        task2 = new Todo("Drink carrot juice");

        repository.deleteAll();
        repository.save(Arrays.asList(task1, task2));
    }

    @Test
    public void canFetchAllTodos() {
        when().
            get("/api/todo").
        then().
            statusCode(HttpStatus.SC_OK).
            body("task", Matchers.hasItems("Buy milk", "Drink carrot juice"));
    }
}
