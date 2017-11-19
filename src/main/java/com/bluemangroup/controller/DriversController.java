package com.bluemangroup.controller;

import com.bluemangroup.model.Location;
import com.bluemangroup.model.dao.DriverSafety;
import com.bluemangroup.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.io.IOException;

@CrossOrigin
@RestController
public class DriversController {

    @Autowired
    private UserService userService;

    @GetMapping("/drivers/location/{driverId}")
    public Location getDriverLocation(@PathVariable("driverId") String driverId) throws IOException {
        return userService.getDriverLocation(driverId);
    }

    @PostMapping("/drivers/create")
    public void createDrivers() throws IOException {
        userService.createDrivers();
    }

    @GetMapping("/drivers/rating/{driverId}")
    public DriverSafety getDriverRating(@PathVariable String driverId) {
        return userService.getDriverRating(driverId);
    }

}
