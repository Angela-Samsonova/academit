package ru.academits.shapes;

import java.util.Objects;

public class Circle implements Shape {
    private final double PI = 3.1415926536;

    private double radius;

    public Circle(double radius) {
        this.radius = radius;
    }

    public double getWidth() {
        return 2*radius;
    }

    public double getHeight() {
        return 2*radius;
    }

    public double getArea() {
        return PI * Math.pow(radius, 2.0);
    }

    public double getPerimeter() {
        return 2 * PI * radius;
    }

    @Override
    public String toString() {
        return "Circle: radius =  " + radius + ", width = height = " + getWidth() + ", area = " + getArea() + ", perimeter = "
                + getPerimeter();
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) {
            return true;
        }

        if (o == null || this.getClass() != o.getClass()) {
            return false;
        }

        Circle circle = (Circle) o;

        return Double.compare(circle.radius, radius) == 0;
    }

    @Override
    public int hashCode() {
        final int prime = 37;
        int hash = 1;
        hash = prime * hash + Double.hashCode(radius);
        return hash;
    }
}