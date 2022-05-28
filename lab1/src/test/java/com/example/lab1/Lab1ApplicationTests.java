package com.example.lab1;

import com.example.lab1.controller.Counter;
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

import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.concurrent.TimeUnit;
import java.util.stream.IntStream;


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
    @Test
    void testIncrement() {
        ExecutorService executorService = Executors.newFixedThreadPool(10);
        Counter counterService = new Counter();
        IntStream.range(0, 10000).forEach(count -> executorService.execute(counterService::increment));

        executorService.shutdown();
        try {
            if (!executorService.awaitTermination(800, TimeUnit.MILLISECONDS)) {
                executorService.shutdownNow();
            }
        } catch (InterruptedException e) {
            executorService.shutdownNow();
        }

        assertEquals(10000, counterService.retInt(), "Synchronization check");
    }
}

