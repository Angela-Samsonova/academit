package ru.academits.shapes;

public class Square implements Shape {
    private static final int SIDES_COUNT = 4;
    private double side;

    public Square(double side) {
        this.side = side;
    }

    @Override
    public double getWidth() {
        return side;
    }

    @Override
    public double getHeight() {
        return side;
    }

    @Override
    public double getArea() {
        return Math.pow(side, 2.0);
    }

    @Override
    public double getPerimeter() {
        return SIDES_COUNT * side;
    }

    @Override
    public String toString() {
        return "Square: width = height = " + side + ", area = " + getArea() + ", perimeter = "
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

        return side == square.side;
    }

    @Override
    public int hashCode() {
        final int prime = 37;
        int hash = 1;
        hash = prime * hash + Double.hashCode(side);
        return hash;
    }
}