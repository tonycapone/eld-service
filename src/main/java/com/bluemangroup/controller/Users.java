package com.bluemangroup.controller;


import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController("/users")
public class Users {

    @PostMapping
    public void createUser() {

    }
}
