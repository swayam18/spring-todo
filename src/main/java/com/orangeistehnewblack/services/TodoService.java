package com.orangeistehnewblack.services;

import com.orangeistehnewblack.models.Todo;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

@Service
public class TodoService {

    private List<Todo> todoList = new ArrayList<Todo>();

    private Todo defaultTask = new Todo("Default TODO!!!");

    public List<Todo> getTodoList() {
        todoList.add(defaultTask);
        return todoList;
    }

    public void addTodo(String task) {
        Todo todo = new Todo(task);
        todoList.add(todo);
    }
}
