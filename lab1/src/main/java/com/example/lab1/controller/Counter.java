package com.example.lab1.controller;

import com.example.lab1.logger.ProgramLogger;
import org.apache.logging.log4j.Level;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class Counter {
    static int counter = 0;

    synchronized public void increment() {
        counter++;
        ProgramLogger.log(Level.INFO, "Counter has been increased.");
    }

    @GetMapping("/counter")
    synchronized public String showCalls() {
        return "Number of calls : "+counter;
    }
}