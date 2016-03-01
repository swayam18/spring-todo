package com.orangeistehnewblack.controllers;

import com.orangeistehnewblack.models.Todo;
import com.orangeistehnewblack.services.TodoService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;

import java.util.List;

@Controller
@RequestMapping("/")
public class TodoController {

    private TodoService service;

    @Autowired
    public TodoController(TodoService service) {
        this.service = service;
    }

    @RequestMapping(method = RequestMethod.GET)
    public String get(Model model) {
        List<Todo> todoList = service.getTodoList();
        model.addAttribute("todos", todoList);
        model.addAttribute("newTodo", new Todo());
        return "index";
    }

    @RequestMapping(method = RequestMethod.POST)
    public String newTodo( @RequestParam(value="task") String task ) {
        service.addTodo(task);
        return "";
    }
}
