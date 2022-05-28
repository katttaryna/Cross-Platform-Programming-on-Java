package com.example.lab1.calculation;

import lombok.Getter;
import org.springframework.stereotype.Component;

@Component
@Getter
public class Stat {
    public Stat() {
        totalRequests = 0;
        wrongRequests = 0;
        min = 0;
        max = 0;
        mostCommon = 0;
    }

    public Integer totalRequests;
    public Integer wrongRequests;
    public Integer min;
    public Integer max;
    public Integer mostCommon;
}