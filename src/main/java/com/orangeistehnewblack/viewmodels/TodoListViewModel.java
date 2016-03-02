package com.orangeistehnewblack.viewmodels;

import com.orangeistehnewblack.models.Todo;

import java.util.ArrayList;
import java.util.List;

public class TodoListViewModel {
    List<Todo> todos;

    public TodoListViewModel() {
        this.todos = new ArrayList<Todo>();
    }

    public TodoListViewModel(List<Todo> todos) {
        this.todos = todos;
    }

    public List<Todo> getTodos() {
        return todos;
    }

    public void setTodos(List<Todo> todos) {
        this.todos = todos;
    }
}
