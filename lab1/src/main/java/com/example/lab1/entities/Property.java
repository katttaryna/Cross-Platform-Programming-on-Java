package com.example.lab1.entities;

public class Property {
    private double square;
    private double perimeter;

    public Property(double square, double perimeter) {
        this.square = square;
        this.perimeter = perimeter;
    }
    public Property(Property a) {
        this.square = a.square;
        this.perimeter = a.perimeter;
    }

    public double getSquare() {
        return square;
    }

    public void setSquare(double square) {
        this.square = square;
    }

    public double getPerimeter() {
        return perimeter;
    }

    public void setPerimeter(double perimeter) {
        this.perimeter = perimeter;
    }
    @Override
    public boolean equals(Object obj) {
        Property test = (Property) obj;
        if (square == test.square && perimeter == test.perimeter) {
            return true;
        }
        else return false;
    }
}
