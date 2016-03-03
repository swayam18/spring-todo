package com.orangeistehnewblack.controllers;

import com.orangeistehnewblack.models.User;
import com.orangeistehnewblack.services.UserService;
import com.orangeistehnewblack.viewmodels.UserSignInForm;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

@Controller
@RequestMapping("/users")
public class UsersController {

    @Autowired
    private UserService service;

    @RequestMapping("/new")
    public String newUser(Model model) {
        model.addAttribute("newUser", new User());
        return "users/new" ;
    }

    @RequestMapping(value = "/create", method = RequestMethod.POST)
    public String createUser(@ModelAttribute User newUser, Model model) {
        service.createUser(newUser);
        return "redirect:/";
    }

    @RequestMapping("/signIn")
    public String signIn(Model model) {
        model.addAttribute("form", new UserSignInForm());
        return "users/signIn";
    }
}
