package com.bluemangroup.model;


import lombok.Data;

@Data
public class User {

    private String carrierId;
    private String firstName;
    private String lastName;
    private String role;
    private String email;
}
