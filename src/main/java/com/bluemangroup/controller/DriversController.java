package com.bluemangroup.controller;

import com.bluemangroup.model.Location;
import com.bluemangroup.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import java.io.IOException;

@RestController
public class DriversController {
    @Autowired
    private UserService userService;


    @GetMapping("/drivers/location/{driverId}")
    public Location getDriverLocation(@PathVariable("driverId") String driverId) throws IOException {
        return userService.getDriverLocation(driverId);
    }

}
