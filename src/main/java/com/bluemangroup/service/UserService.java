package com.bluemangroup.service;

import com.bluemangroup.model.User;
import com.bluemangroup.model.Violations;
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
import java.util.Collections;
import java.util.List;
import java.util.stream.Collectors;
import java.util.stream.Stream;

@Service
public class UserService {

    private RestTemplate restTemplate = new RestTemplate();

    public void createUser(User user){
        HttpHeaders headers2 = new HttpHeaders();
        headers2.setContentType(MediaType.APPLICATION_FORM_URLENCODED);
        headers2.set("Cookie", login());
        MultiValueMap<String, String> map= new LinkedMultiValueMap<>();

        map.add("call", "carrier_invite_user");
        map.add("carrier_row", "884702");
        map.add("name_first", "dane2");
        map.add("name_last", "therockjohson2");
        map.add("user_role", "driver");
        map.add("user_email", "dane_johnson2@unigroupinc.com");

        HttpEntity<MultiValueMap<String, String>> request2 = new HttpEntity<>(map, headers2);

        ResponseEntity<String> response2 = restTemplate.postForEntity(
                "https://www.blueinktech.com/copilot/assets/php/db_calls.php", request2 , String.class);
        System.out.println(response2);
    }

    public String getUsers() {

        HttpHeaders headers2 = new HttpHeaders();
        headers2.setContentType(MediaType.APPLICATION_FORM_URLENCODED);
        headers2.set("Cookie", login());
        MultiValueMap<String, String> map= new LinkedMultiValueMap<>();
        map.add("call", "get_user");
        map.add("carrier_row", "884702");

        HttpEntity<MultiValueMap<String, String>> request2 = new HttpEntity<>(map, headers2);

        ResponseEntity<String> response2 = restTemplate.postForEntity(
                "https://www.blueinktech.com/copilot/assets/php/db_calls.php", request2 , String.class);
        System.out.println(response2);
        return response2.getBody();

    }

    private String login() {
        MultiValueMap<String, String> request = new LinkedMultiValueMap<>();
        request.put("source", Collections.singletonList("web"));
        request.put("email", Collections.singletonList("jim_mcbride@blueinktech.com"));
        request.put("p", Collections.singletonList("80abafe5fb0611e9cb2acb4f26b61d673e7228b1c2a9f62def6a21c8cbefa5ffd131291635fa2b77781966e4b6642d8ccfaed96d58c95c23a9534b94258819d2"));
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
}
