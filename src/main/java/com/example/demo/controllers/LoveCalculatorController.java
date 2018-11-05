package com.example.demo.controllers;

import com.example.demo.api.LoveResult;
import com.example.demo.services.LoveCalculatorService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class LoveCalculatorController {

    @Autowired
    private LoveCalculatorService loveCalculatorService;

    @GetMapping("test-api")
    public LoveResult testApi(
            @RequestParam String firstname,
            @RequestParam String secondname
    ) {
        return loveCalculatorService.testApi(firstname, secondname);
    }



}
