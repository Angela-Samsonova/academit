package ru.academits.shapes;

public class Square implements Shape {
    private static final int SIDES_COUNT = 4;
    private double side1;

    public Square(double side1) {
        this.side1 = side1;
    }

    @Override
    public double getWidth() {
        return side1;
    }

    @Override
    public double getHeight() {
        return side1;
    }

    @Override
    public double getArea() {
        return Math.pow(side1, 2.0);
    }

    @Override
    public double getPerimeter() {
        return SIDES_COUNT * side1;
    }

    @Override
    public String toString() {
        return "Square: width = height = " + side1 + ", area = " + getArea() + ", perimeter = "
                + getPerimeter();
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) {
            return true;
        }

        if (o == null || getClass() != o.getClass()) {
            return false;
        }

        Square square = (Square) o;

        return side1 == square.side1;
    }

    @Override
    public int hashCode() {
        final int prime = 37;
        int hash = 1;
        hash = prime * hash + Double.hashCode(side1);
        return hash;
    }
}