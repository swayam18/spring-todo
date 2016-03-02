package com.orangeistehnewblack.controllers;

import com.orangeistehnewblack.models.Todo;
import com.orangeistehnewblack.services.TodoService;
import com.orangeistehnewblack.viewmodels.TodoListViewModel;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

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
        setDefaultAttributes(model);
        return "index";
    }

    @RequestMapping(method = RequestMethod.POST)
    public String newTodo(@ModelAttribute Todo newTodo, Model model) {
        service.addTodo(newTodo);

        return "redirect:/";
    }

    @RequestMapping(value = "update", method = RequestMethod.POST)
    public String updateTodo(@ModelAttribute TodoListViewModel todoListViewModel, Model model) {
        List<Todo> updatedTodos = todoListViewModel.getTodos();

        for(Todo todo: updatedTodos) {
            service.setDone(todo.getId(), todo.getDone());
        }

        return "redirect:/";
    }

    @RequestMapping("delete/{todoId}")
    public String deleteTodo(@PathVariable Long todoId) {
        service.deleteTodo(todoId);

        return "redirect:/";
    }

    private void setDefaultAttributes(Model model) {
        List<Todo> todoList = service.getTodoList();
        TodoListViewModel todoListViewModel = new TodoListViewModel(todoList);
        model.addAttribute("todoListViewModel", todoListViewModel);
        model.addAttribute("newTodo", new Todo());
    }
}
