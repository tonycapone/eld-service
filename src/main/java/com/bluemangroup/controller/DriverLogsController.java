package com.bluemangroup.controller;

import com.bluemangroup.model.EmailRequestBody;
import com.bluemangroup.service.DriverLogsService;
import com.bluemangroup.service.EmailService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

@RestController
@CrossOrigin
public class DriverLogsController {

    @Autowired
    private DriverLogsService driverLogsService;

    @PostMapping("/driverlogs/email")
    public void sendEmail(@RequestBody EmailRequestBody body) {
        driverLogsService.emailCsv(body);
    }
}
