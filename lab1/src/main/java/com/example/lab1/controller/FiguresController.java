package com.example.lab1.controller;

import com.example.lab1.exception.Exception;
import com.example.lab1.calculation.Param;
import com.example.lab1.calculation.Solution;
import org.springframework.http.MediaType;
import com.example.lab1.cache.Cache;
import org.springframework.http.ResponseEntity;
import org.springframework.http.HttpStatus;
import com.example.lab1.exception.InternalException;
import com.example.lab1.entities.Property;
import com.example.lab1.figures.Figures;
import org.springframework.web.bind.annotation.*;
import com.example.lab1.controller.ShowStat;

@RestController
public class FiguresController {
    private Counter counter = new Counter();
    private Solution solution=new Solution();
    private ShowStat showStat=new ShowStat();
    @GetMapping("/figure")
    public Property getSquarePerimeter(@RequestParam(value = "length", defaultValue = "0") String length,
                                @RequestParam(value = "height", defaultValue = "0") String height)
    {
        boolean is_wrong = false;
        counter.increment();
        Figures figures = new Figures();

        if(length.matches("((-|\\+)?[0-9]+(\\.[0-9]+)?)+")){
            figures.setLength(Double.parseDouble(length));
            if(figures.getLength() < 0){
                is_wrong = true;
                showStat.statsProvider.addRoot(0, is_wrong);
                is_wrong = false;
                throw new Exception("The length value is less than 0...");
            }
        }
        else {
            is_wrong = true;
            showStat.statsProvider.addRoot(0, is_wrong);
            is_wrong = false;
            throw new Exception("The length value is not a number...");
        }

        if(height.matches("((-|\\+)?[0-9]+(\\.[0-9]+)?)+")){
            figures.setHeight(Double.parseDouble(height));
            if(figures.getHeight() < 0){
                is_wrong = true;
                showStat.statsProvider.addRoot(0, is_wrong);
                is_wrong = false;
                throw new Exception("The height value is less than 0...");
            }
        }
        else {
            is_wrong = true;
            showStat.statsProvider.addRoot(0, is_wrong);
            is_wrong = false;
            throw new Exception("The height value is not a number...");
        }
        if(figures.getLength() == 2) {
            is_wrong = true;
            showStat.statsProvider.addRoot(0, is_wrong);
            is_wrong = false;
            throw new InternalException("Error 500 is called...");
        }
        var solution = new Solution(new Param(figures.getHeight(), figures.getLength()));
        System.out.println(showStat.statsProvider.totalReq);
       showStat.statsProvider.addRoot(solution.calculateRoot(), is_wrong);
        System.out.println(showStat.statsProvider.totalReq);

       return new Property(solution.getRoot());

    }
    @GetMapping("/cache")
    public ResponseEntity<String> printCache() {
        return new ResponseEntity<>(Cache.getStaticStringCache(), HttpStatus.OK);
    }
    @PostMapping(value = "/calculation", produces = MediaType.APPLICATION_JSON_VALUE, consumes = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity alternativeCalculation(@RequestBody String[] array) {
        return new ResponseEntity<>(solution.averageOfPositive(array), HttpStatus.OK);
    }
}
