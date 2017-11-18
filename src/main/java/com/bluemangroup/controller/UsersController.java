package com.bluemangroup.controller;


import com.bluemangroup.model.User;
import com.bluemangroup.model.Violations;
import com.bluemangroup.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RestController;

import java.io.IOException;

@RestController("/users")
@CrossOrigin
public class UsersController {

    @Autowired
    private UserService userService;

    @PostMapping
    public void createUser() {
        userService.createUser(new User());

    }

    @GetMapping
    public String getUsers() {
        return userService.getUsers();
    }

    @GetMapping("/violations")
    public Violations getViolations() throws IOException {
        return userService.getViolations();
    }
}
