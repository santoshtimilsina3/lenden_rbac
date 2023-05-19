package com.lenden.rbac.controller;

import java.util.HashMap;
import java.util.Map;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class MainController {
    
    
    @GetMapping(value = "/test")
    public Map<String,String> checkHealth(){
        Map<String,String> healthMap = new HashMap<>();
        healthMap.put("status", "I am healthy!!!");
        return healthMap;
    }

    
}
