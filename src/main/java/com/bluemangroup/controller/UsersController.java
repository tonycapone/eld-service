package com.bluemangroup.controller;


import com.bluemangroup.model.User;
import com.bluemangroup.model.Violations;
import com.bluemangroup.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.io.IOException;
import java.util.List;

@CrossOrigin
@RestController()
public class UsersController {

    @Autowired
    private UserService userService;

    @PostMapping
    public void createUser(@RequestBody User user) throws IOException {
        userService.createUser(user);
    }

    @GetMapping("/users")
    public List<User> getUsers() throws IOException {
        return userService.getUsers();
    }

    @GetMapping("/users/violations")
    public Violations getViolations() throws IOException {
        return userService.getViolations();
    }

}
