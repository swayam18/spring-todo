package com.orangeistehnewblack.controllers.api;

import com.orangeistehnewblack.models.Todo;
import com.orangeistehnewblack.repository.TodoRepository;
import com.orangeistehnewblack.services.TodoService;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.MediaType;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
@RequestMapping(value = "/api/todo", produces = MediaType.APPLICATION_JSON_VALUE)
public class ApiTodoController {
    @Autowired
    private TodoRepository repository;

    @RequestMapping(method = RequestMethod.GET)
    List<Todo> todos(@RequestParam(value = "filter", defaultValue = "all", required = false) String filterByCategory) {
        return repository.findAllByOrderByIdAsc();
    }
}
