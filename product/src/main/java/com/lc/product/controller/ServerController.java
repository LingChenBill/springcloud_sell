package com.lc.product.controller;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

/**
 * @description:
 * @author: lingchen
 * @date: 2020/11/2
 */
@RestController
public class ServerController {

    @GetMapping("/msg")
    public String msg() {
        return "this is product's msg 2!";
    }
}
