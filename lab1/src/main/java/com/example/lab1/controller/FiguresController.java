package com.example.lab1.controller;

import com.example.lab1.exception.Exception;
import com.example.lab1.calculation.Param;
import com.example.lab1.calculation.Solution;
import com.example.lab1.cache.Cache;
import org.springframework.http.ResponseEntity;
import org.springframework.http.HttpStatus;
import com.example.lab1.exception.InternalException;
import com.example.lab1.entities.Property;
import com.example.lab1.figures.Figures;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class FiguresController {
    private Counter counter = new Counter();
    @GetMapping("/figure")
    public Property getSquarePerimeter(@RequestParam(value = "length", defaultValue = "0") String length,
                                @RequestParam(value = "height", defaultValue = "0") String height)
    {
        counter.increment();
        Figures figures = new Figures();

        if(length.matches("((-|\\+)?[0-9]+(\\.[0-9]+)?)+")){
            figures.setLength(Double.parseDouble(length));
            if(figures.getLength() < 0){
                throw new Exception("The length value is less than 0...");
            }
        }
        else
            throw new Exception("The length value is not a number...");

        if(height.matches("((-|\\+)?[0-9]+(\\.[0-9]+)?)+")){
            figures.setHeight(Double.parseDouble(height));
            if(figures.getHeight() < 0){
                throw new Exception("The height value is less than 0...");
            }
        }
        else
            throw new Exception("The height value is not a number...");

        if(figures.getLength() == 2)
            throw new InternalException("Error 500 is called...");

        var solution = new Solution(new Param(figures.getHeight(), figures.getLength()));
        solution.calculateRoot();

       return new Property(solution.getRoot());

    }
    @GetMapping("/cache")
    public ResponseEntity<String> printCache() {
        return new ResponseEntity<>(Cache.getStaticStringCache(), HttpStatus.OK);
    }
}
