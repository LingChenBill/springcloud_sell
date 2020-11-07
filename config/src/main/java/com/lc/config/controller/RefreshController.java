package com.lc.config.controller;

import org.springframework.http.HttpEntity;
import org.springframework.http.HttpHeaders;
import org.springframework.http.ResponseEntity;
import org.springframework.util.MultiValueMap;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.client.RestTemplate;

/**
 * Github的webhook转发类.
 *
 * @description:
 * @author: lingchen
 * @date: 2020/11/7
 */
@RestController
@RequestMapping("/config")
public class RefreshController {

    @PostMapping("/refresh")
    public String refresh() {

        RestTemplate restTemplate = new RestTemplate();
        HttpHeaders httpHeaders = new HttpHeaders();
        httpHeaders.add(HttpHeaders.CONTENT_TYPE, "application/json");
        HttpEntity<MultiValueMap<String, String>> request = new HttpEntity<>(null, httpHeaders);
        ResponseEntity<String> stringResponseEntity = restTemplate.postForEntity("http://localhost/actuator/bus-refresh", request, String.class);

        return "bus-refresh";
    }
}
