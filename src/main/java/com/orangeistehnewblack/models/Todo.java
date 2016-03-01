package com.orangeistehnewblack.models;

public class Todo {

    private String task;
    private boolean done;

    public Todo(String task) {
        this.task = task;
        this.done = false;
    }

    public Todo() {
        this("");
    }

    public String getTask() {
        return task;
    }

    public void setTask(String task) {
        this.task = task;
    }

    public boolean getDone() {
        return done;
    }

    public void setDone(boolean done) {
        this.done = done;
    }

}
