package com.orangeistehnewblack.models;

public class Todo {

    private String task;
    private boolean isDone;

    public Todo(String task) {
        this.task = task;
        this.isDone = false;
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

    public boolean isDone() {
        return isDone;
    }

    public void setDone(boolean done) {
        isDone = done;
    }

}
