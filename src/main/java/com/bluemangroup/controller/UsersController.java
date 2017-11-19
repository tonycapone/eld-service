package com.bluemangroup.controller;


import com.bluemangroup.model.User;
import com.bluemangroup.model.Violations;
import com.bluemangroup.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.io.IOException;

@CrossOrigin
@RestController("/users")
public class UsersController {

    @Autowired
    private UserService userService;

    @PostMapping
    public void createUser(@RequestBody User user) {
        userService.createUser(user);

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
