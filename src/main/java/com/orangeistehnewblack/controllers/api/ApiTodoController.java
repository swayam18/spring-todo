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
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
@RequestMapping("/api/todo")
@PreAuthorize("hasAuthority('USER')")
public class ApiTodoController {
    @Autowired
    private TodoRepository repository;

    @Autowired
    private TodoService service;

    @RequestMapping(method = RequestMethod.GET, produces = MediaType.APPLICATION_JSON_VALUE)
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

    private User getUser(Authentication authentication) {
        return ((CurrentUser) authentication.getPrincipal()).getUser();
    }
}
