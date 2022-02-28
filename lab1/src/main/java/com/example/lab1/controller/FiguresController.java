package com.example.lab1.controller;

import com.example.lab1.entities.Property;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;

@RestController
public class FiguresController {
    @GetMapping("/figure")
    Property getSquarePerimeter(@RequestParam(value = "length", defaultValue = "0") double length,
                                @RequestParam(value = "height", defaultValue = "0") double height)
    {
    if(length < 0 ){
        length = 0;
    }
    if (height < 0){
        height = 0;
    }
    double square = length * height;
    double perimeter = height + height + length + length;

    return new Property(square, perimeter);
    }
}
