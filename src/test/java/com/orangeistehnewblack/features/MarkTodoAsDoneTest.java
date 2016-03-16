package com.orangeistehnewblack.features;

import com.orangeistehnewblack.models.Todo;
import com.orangeistehnewblack.models.User;
import org.fluentlenium.core.domain.FluentWebElement;
import org.junit.Before;
import org.junit.Test;

import java.util.Arrays;
import java.util.concurrent.TimeUnit;

import static org.fluentlenium.core.filter.FilterConstructor.withText;
import static org.hamcrest.MatcherAssert.assertThat;
import static org.hamcrest.Matchers.*;
import static org.hamcrest.core.Is.is;

public class MarkTodoAsDoneTest extends BaseFeatureTest{

    String userName = "Frank Z";
    String userEmail = "me@example.com";
    String userPassword = "123456";
    Todo task1; Todo task2; Todo task3;

    @Before
    public void setUp() {
        clearDatabase();
        User user = prepareUser(userName, userEmail, userPassword);
        task1 = new Todo(user, "Help me");
        task2 = new Todo(user, "Get milk");
        task3 = new Todo(user, "Cure Swayam's paranoia");
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

    @Test
    public void userCanDeleteTodos() throws Exception {
        login(userEmail, userPassword);

        assertThat(find("#todo-list li span", withText(task1.getTask())), is(not(empty())));
        todoDeleteLink(0).click();
        await().until("#todo-list li").hasSize(2);
        assertThat(find("#todo-list li span", withText(task1.getTask())), is(empty()));
    }

    private FluentWebElement todoCheckbox(int index) {
        return find("#todo-list li input[type=checkbox]", index);
    }

    private FluentWebElement todoDeleteLink(int index) {
        return find("#todo-list li a", index);

    }
}
