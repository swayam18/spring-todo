package com.orangeistehnewblack.controllers;

import com.orangeistehnewblack.models.CurrentUser;
import com.orangeistehnewblack.models.Todo;
import com.orangeistehnewblack.models.User;
import com.orangeistehnewblack.services.TodoService;
import com.orangeistehnewblack.viewmodels.TodoListViewModel;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.security.core.Authentication;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.TreeMap;

@Controller
@PreAuthorize("hasAuthority('USER')")
@RequestMapping("/")
public class TodoController {

    @Autowired
    private TodoService service;

    @RequestMapping(method = RequestMethod.GET)
    public String get(@RequestParam(value = "filter", defaultValue = "all", required = false) String filterByCategory, Authentication authentication, Model model) {
        User currentUser = ((CurrentUser) authentication.getPrincipal()).getUser();

        List<Todo> todoList = service.getTodoList(currentUser, filterByCategory);
        TodoListViewModel todoListViewModel = new TodoListViewModel(todoList);

        model.addAttribute("currentUser", currentUser);
        model.addAttribute("todoListViewModel", todoListViewModel);
        model.addAttribute("newTodo", new Todo());

        return "index";
    }

    @RequestMapping(method = RequestMethod.POST)
    public String newTodo(@ModelAttribute Todo newTodo, Authentication authentication, Model model) {
        User currentUser = ((CurrentUser) authentication.getPrincipal()).getUser();
        newTodo.setUser(currentUser);
        service.addTodo(newTodo);

        return "redirect:/";
    }

    @RequestMapping(value = "update", method = RequestMethod.POST)
    public String updateTodo(@ModelAttribute TodoListViewModel todoListViewModel, Model model) {
        TreeMap<Long, Todo> updatedTodos = todoListViewModel.getTodosMap();

        for(Long id: updatedTodos.keySet()) {
            Todo todo = updatedTodos.get(id);
            service.setDone(todo.getId(), todo.getDone());
        }

        return "redirect:/";
    }

    @RequestMapping("delete/completed")
    public String deleteCompletedTodos() {
        service.deleteCompleted();

        return "redirect:/";
    }

    @RequestMapping("delete/{todoId}")
    public String deleteTodo(@PathVariable Long todoId) {
        service.deleteTodo(todoId);

        return "redirect:/";
    }
}
