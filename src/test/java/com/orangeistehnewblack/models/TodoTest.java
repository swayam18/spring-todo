package com.orangeistehnewblack.models;

import org.junit.Before;
import org.junit.Test;

import static org.junit.Assert.*;
import static org.hamcrest.core.Is.is;

public class TodoTest {
    private Todo subject;

    @Before
    public void setUp() throws Exception {
        subject = new Todo();
    }

    @Test
    public void testConstructor() throws Exception {
        assertThat(subject.getDone(), is(false));
    }
}