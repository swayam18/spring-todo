package com.orangeistehnewblack.models;


import javax.persistence.*;

@Entity
public class Todo {

    @Id
    @GeneratedValue(strategy= GenerationType.AUTO)
    private long id;
    private String task;
    private boolean done;

    @ManyToOne
    @JoinColumn(name = "USER_ID")
    private User user;

    public long getId() {
        return id;
    }

    public void setId(long id) {
        this.id = id;
    }

    public Todo(User user, String task) {
        this.user = user;
        this.task = task;
        this.done = false;
    }

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

    public User getUser() {
        return user;
    }

    public void setUser(User user) {
        this.user = user;
    }


}
