package com.orangeistehnewblack.services;

import com.orangeistehnewblack.models.Todo;
import com.orangeistehnewblack.repository.TodoRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import java.util.List;

@Service
public class TodoService {

    @Autowired
    private TodoRepository repository;

    public List<Todo> getTodoList(String category) {
        switch(category) {
            case "pending":
                return repository.findAllByDoneOrderByIdAsc(false);
            case "completed":
                return repository.findAllByDoneOrderByIdAsc(true);
            default:
                return repository.findAllByOrderByIdAsc();
        }
    }

    public void addTodo(Todo todo) {
        repository.save(todo);
    }

    public void setDone(long id, boolean done) {
        Todo todo = repository.findOne(id);
        todo.setDone(done);
        repository.save(todo);
    }

    public void deleteTodo(Long todoId) {
        repository.delete(todoId);
    }

    public void deleteCompleted() {
        List<Todo> completedTodos = repository.findAllByDone(true);
        repository.deleteInBatch(completedTodos);
    }
}
