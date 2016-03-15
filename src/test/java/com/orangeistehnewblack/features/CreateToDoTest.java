package com.orangeistehnewblack.features;

import org.junit.Before;
import org.junit.Test;

import static org.fluentlenium.core.filter.FilterConstructor.*;
import static org.hamcrest.MatcherAssert.assertThat;
import static org.hamcrest.Matchers.containsString;


public class CreateToDoTest extends BaseFeatureTest {
    String userName = "Frank Z";
    String userEmail = "me@example.com";
    String userPassword = "123456";

    @Before
    public void setUp() {
        clearDatabase();
        prepareUser(userName, userEmail, userPassword);
    }

    @Test
    public void addNewTodo() {
        login(userEmail, userPassword);
        String task = "Random Task To Be Done";
        fill("input", withName("task")).with(task);
        submit("#add-task");

        assertThat(find("li").getText(), containsString(task));
    }

}
