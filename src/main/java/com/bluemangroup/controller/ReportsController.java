package com.bluemangroup.controller;

import com.bluemangroup.model.Violations;
import com.bluemangroup.service.EmailService;
import com.bluemangroup.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RestController;

import java.io.IOException;

@RestController()
public class ReportsController {

    @Autowired
    private UserService userService;

    @Autowired
    private EmailService emailService;


    @PostMapping("/reports/violations")
    public void generateReports() throws IOException {
        Violations violations = userService.getViolations();

        StringBuilder list = new StringBuilder();
        violations.getViolators().forEach(driver -> {
            list.append(String.format("\t - %s %s Violations: %s \n" +
                                      "\t\t * Break Violations: %s \n" +
                                      "\t\t * Max Drive Violations: %s \n" +
                                      "\t\t * Max Cycle Violations: %s \n " +
                                      "\t\t * Max Duty Violations: %s ",
                    driver.getFirstName(), driver.getLastName(), driver.getViolations(),
                    driver.getBreakViolations(), driver.getMaxDriveViolations(), driver.getCycleViolations(),
                    driver.getMaxDutyViolations()));
        });

        String body = String.format("Total Violations: %s \n \n" +
                "----------------------- \n %s", violations.getViolations(), list.toString() );

        emailService.sendEmail("Tony_Howell@unigroup.com", "HOS Violations", body);

    }
}
