package com.example.lab1.calculation;

import org.jetbrains.annotations.Contract;
import org.jetbrains.annotations.NotNull;
import org.springframework.lang.Nullable;
import java.util.Objects;

public class Param {
    @Override
    public boolean equals(Object obj) {
        if (obj == this)
            return true;

        if (obj == null || obj.getClass() != this.getClass())
            return false;

        Param params = (Param) obj;

        return  Objects.equals(firstValue,  params.firstValue)  &&
                Objects.equals(secondValue, params.secondValue);
    }

    @Override
    public int hashCode() {
        return Objects.hash(firstValue, secondValue);
    }

    private @Nullable Double firstValue;

    private @Nullable Double secondValue;


    public Param(
            @Nullable Double firstValue,
            @Nullable Double secondValue
    ) {
        if (firstValue == null && secondValue == null)
            throw new IllegalArgumentException("No first and second values!");

        if (firstValue == null)
            throw new IllegalArgumentException("No first value!");

        if (secondValue == null)
            throw new IllegalArgumentException("No second value!");

        this.firstValue  = firstValue;
        this.secondValue = secondValue;
    }

    @NotNull
    @Contract(pure = true)
    public Double getFirstValue() {
        assert firstValue != null;
        return firstValue;
    }

    @NotNull
    @Contract(pure = true)
    public Double getSecondValue() {
        assert secondValue != null;
        return secondValue;
    }

    public void setFirstValue(@Nullable Double firstValue) {
        this.firstValue = firstValue;
    }

    public void setSecondValue(@Nullable Double secondValue) {
        this.secondValue = secondValue;
    }


}
