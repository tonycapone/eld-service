package com.bluemangroup.service;

import com.bluemangroup.model.Location;
import com.bluemangroup.model.User;
import com.bluemangroup.model.Violations;
import com.bluemangroup.model.dao.DriverSafety;
import com.bluemangroup.model.dao.VCOMDriver;
import com.bluemangroup.repository.DriverSafetyRepository;
import com.bluemangroup.repository.VCOMDriverRepository;
import com.fasterxml.jackson.core.type.TypeReference;
import com.fasterxml.jackson.databind.ObjectMapper;
import lombok.AllArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.ParameterizedTypeReference;
import org.springframework.http.*;
import org.springframework.stereotype.Service;
import org.springframework.util.LinkedMultiValueMap;
import org.springframework.util.MultiValueMap;
import org.springframework.web.client.RestTemplate;

import javax.servlet.http.HttpServletRequest;
import java.io.IOException;
import java.sql.Driver;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.*;
import java.util.stream.Collectors;
import java.util.stream.Stream;

@Service
public class UserService {

    private RestTemplate restTemplate = new RestTemplate();

    private Map<String, DriverSafety.Id> driverIdToSafetyId = new HashMap<>();
    private Map<String, String> driverCurIdToDriverId = new HashMap<>();

    @Autowired
    VCOMDriverRepository driverRepository;

    @Autowired
    DriverSafetyRepository driverSafetyRepository;

    public String createUser(User user) throws IOException {
        HttpHeaders headers2 = new HttpHeaders();
        headers2.setContentType(MediaType.APPLICATION_FORM_URLENCODED);
        headers2.set("Cookie", login());
        MultiValueMap<String, String> map= new LinkedMultiValueMap<>();

        map.add("call", "carrier_invite_user");
        map.add("carrier_row", "884702");
        map.add("name_first", user.getFirstName());
        map.add("name_last", user.getLastName());
        map.add("user_role", user.getRoles());
        map.add("user_email", user.getEmail());

        HttpEntity<MultiValueMap<String, String>> request2 = new HttpEntity<>(map, headers2);

        ResponseEntity<String> response2 = restTemplate.postForEntity(
                "https://www.blueinktech.com/copilot/assets/php/db_calls.php", request2 , String.class);
        System.out.println(response2);
        Map<String, String> resp = new ObjectMapper().readValue(response2.getBody(), new TypeReference<Map<String, String>>() {
        });
        return resp.get("id");
    }

    public Location getDriverLocation(String driverId) throws IOException {
        HttpHeaders headers2 = new HttpHeaders();
        headers2.setContentType(MediaType.APPLICATION_FORM_URLENCODED);
        headers2.set("Cookie", login());
        MultiValueMap<String, String> map= new LinkedMultiValueMap<>();

//        1. call:
//        get_breadcrumb
//        2. user_row:
//        6042
//        3. carrier_row:
//        884702
//        4. time_start:
//        2017-11-11
//        5. time_end:
//        2017-11-19
        map.add("call", "get_breadcrumb");
        map.add("user_row", driverId);
        map.add("carrier_row", "884702");
        String endDate = LocalDateTime.now().plusDays(1).format(DateTimeFormatter.ofPattern("yyyy-MM-dd"));
        String startDate = LocalDateTime.now().minusDays(3).format(DateTimeFormatter.ofPattern("yyyy-MM-dd"));
        map.add("time_start", startDate);
        map.add("time_end", endDate);
        HttpEntity<MultiValueMap<String, String>> entity = new HttpEntity<>(map, headers2);

        ResponseEntity<String> response = restTemplate.postForEntity(
                "https://www.blueinktech.com/copilot/assets/php/db_calls.php",
                entity, String.class);
        System.out.println(response);

        List<Map<String, String>> locations = new ObjectMapper().readValue(response.getBody(), new TypeReference<List<Map<String, String>>> (){});
        Location location = new Location();
        if(locations.size() > 0) {
            location.setLatitude(locations.get(locations.size() - 1).get("lat"));
            location.setLongitude(locations.get(locations.size() - 1).get("lng"));
        }
        return location;
    }

    public List<User> getUsers() throws IOException {

        HttpHeaders headers2 = new HttpHeaders();
        headers2.setContentType(MediaType.APPLICATION_FORM_URLENCODED);
        headers2.set("Cookie", login());
        MultiValueMap<String, String> map= new LinkedMultiValueMap<>();
        map.add("call", "get_user");
        map.add("carrier_row", "884702");

        HttpEntity<MultiValueMap<String, String>> request2 = new HttpEntity<>(map, headers2);

        ResponseEntity<String> response2 = restTemplate.postForEntity(
                "https://www.blueinktech.com/copilot/assets/php/db_calls.php", request2 , String.class);

        List<User> users = new ObjectMapper().readValue(response2.getBody(), new TypeReference<List<User>>() {});
        users.forEach(user -> {
            user.setVcomDriverId(driverCurIdToDriverId.get(user.getCurIds()));
        });

        System.out.println(response2);
        return users;

    }

    private String login() {
        MultiValueMap<String, String> request = new LinkedMultiValueMap<>();
        request.put("source", Collections.singletonList("web"));
        request.put("email", Collections.singletonList("andrew_dillon@maryville.com"));
        request.put("p", Collections.singletonList("d404559f602eab6fd602ac7680dacbfaadd13630335e951f097af3900e9de176b6db28512f2e000b9d04fba5133e8b1c6e8df59db3a8ab9d60be4b97cc9e81db"));
        HttpHeaders headers = new HttpHeaders();
        headers.setContentType(MediaType.APPLICATION_FORM_URLENCODED);
        HttpEntity<MultiValueMap<String, String>> entity = new HttpEntity<>(request, headers);
        ResponseEntity<String> response = restTemplate.postForEntity("https://www.blueinktech.com/copilot/assets/php/process_login.php", entity, String.class);
        String cookies = response.getHeaders().get("set-cookie").get(1);
        System.out.println(cookies);
        return cookies;
    }

    public Violations getViolations() throws IOException {
        HttpHeaders headers2 = new HttpHeaders();
        headers2.setContentType(MediaType.APPLICATION_FORM_URLENCODED);
        headers2.set("Cookie", login());
        MultiValueMap<String, String> map= new LinkedMultiValueMap<>();
        map.add("call", "get_user");
        map.add("carrier_row", "884702");

        HttpEntity<MultiValueMap<String, String>> entity = new HttpEntity<>(map, headers2);

        ResponseEntity<String> response = restTemplate.postForEntity(
                "https://www.blueinktech.com/copilot/assets/php/db_calls.php", entity , String.class);

        List<User> users = new ObjectMapper().readValue(response.getBody(), new TypeReference<List<User>>() {});

        List<User> violators = users.stream().filter(usr -> usr.getViolations() > 0).collect(Collectors.toList());

        Violations violations = new Violations();
        violations.setViolators(violators);
        violations.setViolations(violators.stream().mapToInt(v -> v.getViolations()).sum());
        return violations;
    }

    public void createDrivers() throws IOException {
        List<VCOMDriver> drivers = driverRepository.findAll();
        drivers.stream().filter(d -> d.getEmailAddress().trim().length() > 0).limit(1).forEach(vcomDriver -> {
            User user = User.builder()
                .firstName(vcomDriver.getFirstName().trim())
                .lastName(vcomDriver.getLastName().trim())
                .email(vcomDriver.getEmailAddress().trim())
                .vcomDriverId(vcomDriver.getDriverId().trim())
                .roles("driver")
                .build();
            String curIds = null;
            try {
                curIds = createUser(user);
            } catch (IOException e) {
                e.printStackTrace();
            }

            DriverSafety.Id driverSafetyId = DriverSafety.Id.builder()
                .driverCompanyCode(vcomDriver.getId().getDriverCompanyCode())
                .driverIdentity(vcomDriver.getId().getDriverIdentity())
                .build();

            driverIdToSafetyId.put(vcomDriver.getDriverId().trim(), driverSafetyId);
            driverCurIdToDriverId.put(curIds, vcomDriver.getDriverId().trim());

            System.out.println(user);
        });
        System.out.println(drivers.size());
    }

    public DriverSafety getDriverRating(String driverId) {
        return driverSafetyRepository.findFirstById(driverIdToSafetyId.get(driverId));
    }

}
