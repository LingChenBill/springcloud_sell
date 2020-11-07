package com.lc.order.controller;

import com.lc.order.config.RefreshConfig;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

/**
 * @description:
 * @author: lingchen
 * @date: 2020/11/7
 */
@RestController
@RequestMapping("/refresh")
public class RefreshController {

    @Autowired
    private RefreshConfig refreshConfig;

    @GetMapping("/print")
    public String print() {
        return "Refresh Name: " + refreshConfig.getName() + " age: " + refreshConfig.getAge();
    }
}
