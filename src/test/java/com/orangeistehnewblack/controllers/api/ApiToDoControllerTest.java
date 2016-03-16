package com.orangeistehnewblack.controllers.api;

import com.jayway.restassured.authentication.FormAuthConfig;
import com.orangeistehnewblack.controllers.BaseController;
import com.orangeistehnewblack.models.Todo;
import com.orangeistehnewblack.models.User;
import org.apache.http.HttpStatus;
import org.junit.Before;
import org.junit.Test;

import java.util.Arrays;

import static com.jayway.restassured.RestAssured.given;
import static org.hamcrest.Matchers.containsString;
import static org.hamcrest.Matchers.hasItems;

public class ApiToDoControllerTest extends BaseController {
    private Todo task1;
    private Todo task2;
    private String userPassword;
    private String userEmail;
    private FormAuthConfig formAuthConfig;

    @Before
    public void setUp() {
        todoRepository.deleteAll();
        userRepository.deleteAll();
        userEmail = "me@example.com";
        userPassword = "123456";
        formAuthConfig = new FormAuthConfig("/users/signIn", "email", "password");

        User user = new User();
        user.setEmail(userEmail);
        user.setPassword(userPassword);
        userRepository.save(user);

        task1 = new Todo(user, "Buy milk");
        task2 = new Todo(user, "Drink carrot juice");

        todoRepository.save(Arrays.asList(task1, task2));
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
        given()
            .port(port).auth().form(userEmail, userPassword, formAuthConfig).
        when().
            get("/api/todo").
        then().
            statusCode(HttpStatus.SC_OK).
            body("task", hasItems("Buy milk", "Drink carrot juice"));
    }

    @Test
    public void addsNewTodo() {
        given()
            .param("task", "New Task")
            .port(port).auth().form(userEmail, userPassword, formAuthConfig).
        when()
            .post("/api/todo").
        then()
            .statusCode(HttpStatus.SC_OK)
            .body("task", containsString("New Task"));
    }

    @Test
    public void deletesTodo() {
        given()
            .port(port).auth().form(userEmail, userPassword, formAuthConfig).
        when()
            .delete("/api/todo/" + task1.getId()).
        then()
            .statusCode(HttpStatus.SC_OK)
            .body("result", containsString("deleted"));
    }
}
