package com.example.lab1.cache;

import com.example.lab1.calculation.Param;
import com.example.lab1.entities.Property;
import org.apache.logging.log4j.Level;
import org.jetbrains.annotations.Contract;
import org.jetbrains.annotations.NotNull;
import org.jetbrains.annotations.Nullable;
import com.example.lab1.logger.ProgramLogger;

import java.util.HashMap;
import java.util.Map;

public class Cache {
    private static final Map<Param, Property> solutions = new HashMap<>();

    public void add(@NotNull Param params, @NotNull Property root) {
        if (!solutions.containsKey(params)) {
            solutions.put(params, root);
            ProgramLogger.log(Level.INFO, "Value " + params + "@" + root + " added to cache!");
        }
    }

    public @Nullable Property find(@NotNull Param params) {
        if (solutions.containsKey(params))
            return solutions.get(params);

        ProgramLogger.log(Level.WARN, "Value " + params + " was not found in cache!");
        return null;
    }

    @Contract(pure = true)
    public @NotNull String getStringCache() {
        return ("Cache:\n" + solutions);
    }

    @Contract(pure = true)
    public static @NotNull String getStaticStringCache() {
        return ("Cache:\n" + solutions);
    }

    @Contract(pure = true)
    public Map<Param, Property> getSolutions() {
        return solutions;
    }
}
