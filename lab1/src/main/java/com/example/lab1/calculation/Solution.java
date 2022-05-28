package com.example.lab1.calculation;

import com.example.lab1.SpringConfig;
import com.example.lab1.cache.Cache;
import com.example.lab1.entities.Property;
import com.example.lab1.logger.ProgramLogger;
import org.apache.logging.log4j.Level;
import org.jetbrains.annotations.Nullable;
import org.springframework.context.annotation.AnnotationConfigApplicationContext;

import java.util.OptionalDouble;
import java.util.stream.IntStream;
import java.util.stream.Stream;
public class Solution {

    private final Cache cache;

    private Param parameters;

    private Property root;

    public Solution(Param params) {
        var context = new AnnotationConfigApplicationContext(SpringConfig.class);
        cache = context.getBean("cache", Cache.class);
        context.close();

        this.parameters = params;
    }

    public Solution() {
        var context = new AnnotationConfigApplicationContext(SpringConfig.class);
        cache = context.getBean("cache", Cache.class);
        context.close();
        parameters = null;
    }

    public void setParameters(Param params){
        this.parameters=params;
    }

    public int calculateRoot()
    {

        var temp = cache.find(parameters);
        if (temp != null) {
            ProgramLogger.log(Level.WARN, "Value found in cache!");
            setRoot(temp);

            return 0;
        }
        Double temp1;
       temp = new Property(parameters.getSecondValue()* parameters.getFirstValue(),
                parameters.getSecondValue()+ parameters.getFirstValue()+parameters.getSecondValue()+ parameters.getFirstValue());
        temp1 = parameters.getSecondValue()+ parameters.getFirstValue()+parameters.getSecondValue()+ parameters.getFirstValue();
        setRoot(temp);
        int value = temp1.intValue();
        cache.add(parameters, root);
        return value;
    }
    public OptionalDouble averageOfPositive(String[] arr){
        IntStream stream = Stream.of(arr).mapToInt(Integer::parseInt);
        return stream.filter(x->x>0).average();
    }

    public Property getRoot() {

        return root;
    }

    public void setRoot(@Nullable Property root) {
        this.root = root;
    }



}
