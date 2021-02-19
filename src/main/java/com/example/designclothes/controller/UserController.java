package com.example.designclothes.controller;

import com.example.designclothes.domain.User;
import com.example.designclothes.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;

@Controller
public class UserController {

    private final UserService userService;

    @Autowired
    public UserController(UserService userService){
        this.userService = userService;
    }
    @GetMapping("/register")
    public String registerForm(){
        return "register";
    }

    @PostMapping("/register")
    public String register(UserForm form){
        User user = new User();
        userService.join(user, form);

        return "redirect:/";
    }
}
