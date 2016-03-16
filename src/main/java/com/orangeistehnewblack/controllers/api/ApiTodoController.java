package com.orangeistehnewblack.controllers.api;

import com.orangeistehnewblack.models.CurrentUser;
import com.orangeistehnewblack.models.Todo;
import com.orangeistehnewblack.models.User;
import com.orangeistehnewblack.repository.TodoRepository;
import com.orangeistehnewblack.services.TodoService;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.MediaType;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.security.core.Authentication;
import org.springframework.web.bind.annotation.*;

import java.util.Collections;
import java.util.List;
import java.util.Map;

@RestController
@RequestMapping(value = "/api/todo", produces = MediaType.APPLICATION_JSON_VALUE)
@PreAuthorize("hasAuthority('USER')")
public class ApiTodoController {
    @Autowired
    private TodoRepository repository;

    @Autowired
    private TodoService service;

    @RequestMapping(method = RequestMethod.GET)
    List<Todo> todos(@RequestParam(value = "filter", defaultValue = "all", required = false) String filterByCategory, Authentication authentication) {
        User currentUser = getUser(authentication);

        return service.getTodoList(currentUser, filterByCategory);
    }

    @RequestMapping(method = RequestMethod.POST)
    public Todo newTodo(@RequestParam("task") String task, Authentication authentication) {
        User currentUser = getUser(authentication);

        Todo newTodo = new Todo(currentUser, task);
        service.addTodo(newTodo);

        return newTodo;
    }

    @RequestMapping(value = "/{todoId}", method = RequestMethod.DELETE)
    public Map<String, String> deleteTodo(@PathVariable Long todoId) {
        service.deleteTodo(todoId);

        return Collections.singletonMap("result", "deleted");
    }

    private User getUser(Authentication authentication) {
        return ((CurrentUser) authentication.getPrincipal()).getUser();
    }
}
