package com.example.lab1.calculation;

import com.example.lab1.loggerErrors.MyLogger;
import org.jetbrains.annotations.NotNull;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.apache.logging.log4j.Level;
import java.util.ArrayList;
import java.util.Collections;
import java.util.Comparator;
import java.util.List;
import java.util.function.BinaryOperator;
import java.util.stream.Collectors;
import com.example.lab1.calculation.Stat;

@Service
public class Provide {
    private static List<Integer> roots = new ArrayList<>();
    public Stat stats = new Stat();
    private static boolean shouldBeRecalculated = true;
    public static int totalReq = 0;
    public static int wrongReq = 0;
    public void setStats(Stat stats) {
        this.stats = stats;
    }
    public Stat getStats() {
        return stats;
    }

    public void calculate(int totalReq, int wrongReq) {
        MyLogger.log(Level.INFO, "Collecting stats...");
        stats.totalRequests = totalReq;
        stats.wrongRequests = wrongReq;
        if (!shouldBeRecalculated) {

            MyLogger.log(Level.WARN, "Stats need not to be recollected!");
            return;
        }

        try {

            stats.mostCommon = roots
                    .stream()
                    .reduce(
                            BinaryOperator.maxBy(Comparator.comparingInt(o -> Collections.frequency(roots, o)))
                    ).orElse(0);

            roots = roots
                    .stream()
                    .distinct()
                    .sorted()
                    .collect(Collectors.toList());

            stats.min = roots
                    .stream()
                    .min(Comparator.comparing(Long::valueOf))
                    .orElse(0);

            stats.max = roots
                    .stream()
                    .max(Comparator.comparing(Long::valueOf))
                    .orElse(0);

            MyLogger.log(Level.WARN, "Stats recollected!");

            shouldBeRecalculated = false;
        } catch (NullPointerException exception) {
            MyLogger.log(Level.ERROR, "Error while collecting stats!");
            throw new RuntimeException(exception);
        }
    }
    public void addRoot(@NotNull Integer root, boolean isWrong) {
        totalReq++;
        if (isWrong) {
            wrongReq++;
            shouldBeRecalculated = true;
        }
        else {
            roots.add(root);
            shouldBeRecalculated = true;
        }
    }
}