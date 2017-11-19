package com.bluemangroup.service;

import com.bluemangroup.model.Location;
import com.bluemangroup.model.User;
import com.bluemangroup.model.Violations;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

import java.io.IOException;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.List;

@RunWith(SpringRunner.class)
@SpringBootTest
public class ServiceApplicationTests {

	@Autowired
	UserService userService;

	@Test
	public void contextLoads() {
	}

	@Test
	public void createUser(){
		userService.createUser(new User());
	}

	@Test
	public void getViolations() throws IOException {
		Violations violations = userService.getViolations();
		System.out.println(violations);
	}

	@Test
	public void testDate() throws IOException {
		Location driverLocation = userService.getDriverLocation("6103");
		System.out.println(driverLocation);
	}

}
