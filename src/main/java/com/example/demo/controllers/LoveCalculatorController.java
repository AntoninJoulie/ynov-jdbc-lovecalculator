package com.example.demo.controllers;

import com.example.demo.api.LoveResult;
import com.example.demo.dto.LoveHistory;
import com.example.demo.services.LoveCalculatorService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import java.sql.SQLException;
import java.util.List;
import java.util.Optional;

@RestController
public class LoveCalculatorController {

    @Autowired
    private LoveCalculatorService loveCalculatorService;

    @GetMapping("ping")
    public String ping() {
        return "pong";
    }

    @GetMapping("test")
    public LoveResult testApi(
            @RequestParam String firstname,
            @RequestParam String secondname
    ) {
        return loveCalculatorService.testApi(firstname, secondname);
    }

    @GetMapping("names")
    public List<String> getAllNames() throws SQLException {
        return loveCalculatorService.getAllNames();
    }

    @GetMapping("history")
    public List<LoveHistory> getHistory(@RequestParam Optional<String> name) throws SQLException {
        return loveCalculatorService.getHistory(name);
    }

    @GetMapping("love")
    public LoveHistory love(
            @RequestParam String firstname,
            @RequestParam String secondname
    ) throws SQLException {
        return loveCalculatorService.love(firstname, secondname);
    }

}
