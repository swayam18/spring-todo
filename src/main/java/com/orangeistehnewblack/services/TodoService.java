package com.orangeistehnewblack.services;

import com.orangeistehnewblack.models.Todo;
import com.orangeistehnewblack.repository.TodoRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

@Service
public class TodoService {

    private List<Todo> todoList = new ArrayList<Todo>();
    private TodoRepository repository;

    @Autowired
    public TodoService(TodoRepository repository) {
        this.repository = repository;
    }

    public List<Todo> getTodoList() {
        return repository.findAllByOrderByIdAsc();
    }

    public void addTodo(Todo todo) {
        repository.save(todo);
    }

    public void setDone(long id, boolean done) {
        Todo todo = repository.findOne(id);
        todo.setDone(done);
        repository.save(todo);
    }
}
