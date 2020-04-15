package ru.academits.shapes;

public class Rectangle extends Square {
    private double side2;

    public Rectangle(double side1, double side2) {
        super(side1);
        this.side2 = side2;
    }

    public double getHeight() {
        return side1;
    }

    @Override
    public double getWidth() {
        return side2;
    }

    @Override
    public double getArea() {
        return side1 * side2;
    }

    @Override
    public double getPerimeter() {
        return 2 * (side1 + side2);
    }

    @Override
    public String toString() {
        return "Rectangle: height = " + getHeight() + ", width = " + getWidth() + ", area = " + getArea() + ", perimeter = "
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

        Rectangle rectangle = (Rectangle) o;

        return side1 == rectangle.side1 && side2 == rectangle.side2;
    }

    @Override
    public int hashCode() {
        final int prime = 37;
        int hash = 1;
        hash = prime * hash + Double.hashCode(side1);
        hash = prime * hash + Double.hashCode(side2);
        return hash;
    }
}