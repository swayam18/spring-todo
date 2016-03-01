package com.orangeistehnewblack.services;

import com.orangeistehnewblack.models.Todo;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

@Service
public class TodoService {

    private List<Todo> todoList = new ArrayList<Todo>();

    public List<Todo> getTodoList() {
        return todoList;
    }

    public void addTodo(Todo todo) {
        todoList.add(todo);
    }
}
