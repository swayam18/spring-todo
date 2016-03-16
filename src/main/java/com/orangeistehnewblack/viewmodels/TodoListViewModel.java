package com.orangeistehnewblack.viewmodels;

import com.orangeistehnewblack.models.Todo;

import java.util.ArrayList;
import java.util.List;
import java.util.TreeMap;

public class TodoListViewModel {
    TreeMap<Long, Todo> todosMap = new TreeMap<Long, Todo>();

    public TodoListViewModel() { }

    public TodoListViewModel(List<Todo> todos) {
        for(Todo todo: todos) {
            todosMap.put(todo.getId(), todo);
        }
    }

    public TreeMap<Long, Todo> getTodosMap() {
        return todosMap;
    }

    public void setTodosMap(TreeMap<Long, Todo> todos) {
        this.todosMap = todos;
    }
}
