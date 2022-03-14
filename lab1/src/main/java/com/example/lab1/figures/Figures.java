package com.example.lab1.figures;

public class Figures {
    private double length;
    private double height;

    public Figures() {
        this.length = 0.0f;
        this.height = 0.0f;
    }

    public Figures(double length, double height) {
        this.length = length;
        this.height = height;
    }

    public double getLength() {
        return length;
    }

    public void setLength(double length) {
        this.length = length;
    }

    public double getHeight() {
        return height;
    }

    public void setHeight(double height) {
        this.height = height;
    }

    public double calculationSquare(){
        return this.length*this.height;
    }

    public double calculationPerimeter(){
        return this.height+this.length+this.height+this.length;
    }
}
