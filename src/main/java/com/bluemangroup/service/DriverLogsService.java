package com.bluemangroup.service;

import com.bluemangroup.model.EmailRequestBody;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.*;
import org.springframework.stereotype.Service;
import org.springframework.util.LinkedMultiValueMap;
import org.springframework.util.MultiValueMap;
import org.springframework.web.client.RestTemplate;
import org.springframework.web.util.UriComponentsBuilder;

import java.util.Collections;

@Service
public class DriverLogsService {

    private RestTemplate restTemplate = new RestTemplate();

    private static final String MARK_EMAIL = "mark_oates@blueinktech.com";
    private static final String MARK_SHA512 = "565780f544c927da32a9440a92927d4da9ed2a15acaee0403371818a326040cebc95275bcb721a10086122056d8a654ad77c51ecf87afe61166f3d43823f7a01";

    @Autowired
    EmailService emailService;

    public void emailCsv(EmailRequestBody body) {
        HttpHeaders headers = new HttpHeaders();
        headers.setContentType(MediaType.APPLICATION_FORM_URLENCODED);
        headers.set("Cookie", login());

        MultiValueMap<String, String> map= new LinkedMultiValueMap<>();
        map.add("email", MARK_EMAIL);
        map.add("p", MARK_SHA512);
        map.add("sub_type", "http");
        map.add("time_start", "2017-10-15");
        map.add("time_end", "2017-11-17 23:59:59");

        HttpEntity<MultiValueMap<String, String>> entity = new HttpEntity<>(map, headers);

        ResponseEntity<String> response = restTemplate.postForEntity(
               "https://blueinktech.com/copilot/assets/php/generate_driver_report.php",
               entity,
               String.class
        );

        emailService.sendEmail(Collections.singletonList(body.getEmail()), "Driver Log Files", response.getBody());
        System.out.println(response);
    }

    private String login() {
        MultiValueMap<String, String> request = new LinkedMultiValueMap<>();
        request.put("source", Collections.singletonList("web"));
        request.put("email", Collections.singletonList(MARK_EMAIL));
        request.put("p", Collections.singletonList(MARK_SHA512));
        HttpHeaders headers = new HttpHeaders();
        headers.setContentType(MediaType.APPLICATION_FORM_URLENCODED);
        HttpEntity<MultiValueMap<String, String>> entity = new HttpEntity<>(request, headers);
        ResponseEntity<String> response = restTemplate.postForEntity("https://www.blueinktech.com/copilot/assets/php/process_login.php", entity, String.class);
        String cookies = response.getHeaders().get("set-cookie").get(1);
        System.out.println(cookies);
        return cookies;
    }

}
