package com.example.lab1.controller;

import com.example.lab1.exception.Exception;
import com.example.lab1.exception.InternalException;
//import com.example.lab1.response.Response;
import com.example.lab1.entities.Property;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class FiguresController {
    @GetMapping("/figure")
    public Property getSquarePerimeter(@RequestParam(value = "length", defaultValue = "0") String length,
                                @RequestParam(value = "height", defaultValue = "0") String height)
    {
        double ln , hg;
        if (length.matches("((-|\\+)?[0-9]+(\\.[0-9]+)?)+") &&
                height.matches("((-|\\+)?[0-9]+(\\.[0-9]+)?)+")) {
            ln = Double.parseDouble(length);
            hg = Double.parseDouble(height);
            if (ln < 0 || hg < 0) {
                throw new Exception("Wrong value...");
            }
        }
        else
            throw new Exception("Wrong value...");
        if(ln == 2)
            throw new InternalException("Failed...");

    double square = ln * hg;
    double perimeter = hg + hg + ln + ln;

    return new Property(square, perimeter);
    }
}
