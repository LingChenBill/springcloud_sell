package com.lc.eureka.controller;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.Arrays;
import java.util.List;

/**
 * Keycloak controller.
 * @description:
 * @author: lingchen
 * @date: 2020/12/23
 */
@RestController
@RequestMapping("/keycloak")
public class KeycloakController {

    /**
     * keycloak访问接口.
     * @return
     */
    @GetMapping("/index")
    public List<String> GetKeycloak() {
        return Arrays.asList("Keycloak","lc-myrealm","lc-myuser");
    }
}
