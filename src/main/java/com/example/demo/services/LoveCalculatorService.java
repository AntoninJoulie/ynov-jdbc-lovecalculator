package com.example.demo.services;

import com.example.demo.api.LoveCalculatorApi;
import com.example.demo.api.LoveResult;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

@Component
public class LoveCalculatorService {

    @Autowired
    private LoveCalculatorApi loveCalculatorApi;

    public LoveResult testApi(String firstName, String secondName) {
        return this.loveCalculatorApi.bringMeLove(firstName, secondName);
    }

}
