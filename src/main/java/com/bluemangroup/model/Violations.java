package com.bluemangroup.model;

import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.Data;

import java.util.List;

@Data
public class Violations {
    private int violations;
    private List<User> violators;
}
