package com.bluemangroup.service;

import com.bluemangroup.model.User;
import lombok.AllArgsConstructor;
import org.springframework.http.HttpEntity;
import org.springframework.http.HttpHeaders;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import org.springframework.util.LinkedMultiValueMap;
import org.springframework.util.MultiValueMap;
import org.springframework.web.client.RestTemplate;
@Service
@AllArgsConstructor
public class UserService {

    private RestTemplate restTemplate = new RestTemplate();

    public void createUser(User user){
        HttpHeaders headers = new HttpHeaders();
        headers.setContentType(MediaType.APPLICATION_FORM_URLENCODED);

        MultiValueMap<String, String> map= new LinkedMultiValueMap<>();
        // login_email=jim_mcbride@blueinktech.com&login_password=565780f544c927da32a9440a92927d4da9ed2a15acaee0403371818a326040cebc95275bcb721a10086122056d8a654ad77c51ecf87afe61166f3d43823f7a01
        map.add("login_email", "jim_mcbride@blueinktech.com");
        map.add("login_password", "565780f544c927da32a9440a92927d4da9ed2a15acaee0403371818a326040cebc95275bcb721a10086122056d8a654ad77c51ecf87afe61166f3d43823f7a01");

//        1. carrier_row:
//        884702
//        2. name_first:
//        dane
//        3. name_last:
//        nottherockjohnson
//        4. user_role:
//        driver
//        5. user_email:
//        dane_johnson@unigroupinc.com

        map.add("carrier_row", "884702");
        map.add("name_first", "dane");
        map.add("name_last", "therockjohson");
        map.add("email", "dane_johnson@unigroupinc.com");

        HttpEntity<MultiValueMap<String, String>> request = new HttpEntity<>(map, headers);

        ResponseEntity<String> response = restTemplate.postForEntity(
                "https://www.blueinktech.com/copilot/assets/php/db_calls.php", request , String.class);
        System.out.println(response);
    }
}
