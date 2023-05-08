package ru.kata.spring.boot_security.demo.controller;


import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;
import ru.kata.spring.boot_security.demo.model.User;
import ru.kata.spring.boot_security.demo.service.UserDetailService;

import java.security.Principal;


@Controller
@RequestMapping("/users")
public class UserContoller {
    private final UserDetailService userService;

    @Autowired
    public UserContoller(UserDetailService userService) {
        this.userService = userService;
    }

    @GetMapping()
    public String getPageForUser(Model model, Principal principal) {
        User user = userService.findByUsername(principal.getName());
        model.addAttribute("user", user);
        return "user";
    }
}
