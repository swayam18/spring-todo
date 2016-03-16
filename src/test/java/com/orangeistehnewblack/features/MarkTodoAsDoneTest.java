package com.orangeistehnewblack.features;

import com.orangeistehnewblack.models.Todo;
import com.orangeistehnewblack.models.User;
import org.fluentlenium.core.domain.FluentWebElement;
import org.junit.Before;
import org.junit.Test;

import java.util.Arrays;

import static org.hamcrest.MatcherAssert.assertThat;
import static org.hamcrest.Matchers.notNullValue;
import static org.hamcrest.Matchers.nullValue;
import static org.hamcrest.core.Is.is;

public class MarkTodoAsDoneTest extends BaseFeatureTest{

    String userName = "Frank Z";
    String userEmail = "me@example.com";
    String userPassword = "123456";

    @Before
    public void setUp() {
        clearDatabase();
        User user = prepareUser(userName, userEmail, userPassword);
        Todo task1 = new Todo(user, "Help me");
        Todo task2 = new Todo(user, "Get milk");
        Todo task3 = new Todo(user, "Cure Swayam's paranoia");
        todoRepository.save(Arrays.asList(task1, task2, task3));
    }

    @Test
    public void userCanUpdatedTodos() {
        login(userEmail, userPassword);
        todoCheckbox(0).click();
        todoCheckbox(1).click();
        submit("#updateListButton");

        assertThat(todoCheckbox(0).getAttribute("checked"), is(notNullValue()));
        assertThat(todoCheckbox(1).getAttribute("checked"), is(notNullValue()));
        assertThat(todoCheckbox(2).getAttribute("checked"), is(nullValue()));
    }

    private FluentWebElement todoCheckbox(int index) {
        return find("#todo-list li input[type=checkbox]", index);
    }
}
