package com.example.lab1;

import com.example.lab1.response.Response;
import org.junit.jupiter.api.Test;
import org.springframework.boot.test.context.SpringBootTest;
import com.example.lab1.controller.FiguresController;
import com.example.lab1.exception.Exception;
import com.example.lab1.exception.InternalException;
import org.junit.jupiter.api.Test;
import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertTrue;
import com.example.lab1.entities.Property;
import org.springframework.http.ResponseEntity;


@SpringBootTest
class Lab1ApplicationTests {

    private final FiguresController figuresController = new FiguresController();
    @Test
    void testSquarePerimeter_1() throws Exception {
        Property result = figuresController.getSquarePerimeter("1", "0");
        Property expected=new Property(0,2);
        assertEquals(expected, result);
    }

    @Test
    void testSquarePerimeter_2() throws InternalException {
        Property result = figuresController.getSquarePerimeter("1.1", "2.3");
        Property expected=new Property(2.53,6.799999999999999);
        assertEquals(expected, result);
    }
}
