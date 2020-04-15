package ru.academits.shapes;

public class Triangle implements Shape{
    private double x1;
    private double x2;
    private double x3;
    private double y1;
    private double y2;
    private double y3;

    public Triangle(double x1, double y1, double x2, double y2, double x3, double y3) {
        this.x1 = x1;
        this.y1 = y1;
        this.x2 = x2;
        this.y2 = y2;
        this.x3 = x3;
        this.y3 = y3;
    }

    private static double getMaxNumber(double a1, double a2, double a3) {
        double[] array = new double[]{a1, a2, a3};
        double maxNumber = array[0];

        for (double e : array) {
            if (e > maxNumber) {
                maxNumber = e;
            }
        }

        return maxNumber;
    }

    private static double getMinNumber(double a1, double a2, double a3) {
        double[] array = new double[]{a1, a2, a3};
        double minNumber = array[0];

        for (double e : array) {
            if (e < minNumber) {
                minNumber = e;
            }
        }

        return minNumber;
    }

    public double getWidth() {
        return getMaxNumber(x1, x2, x3) - getMinNumber(x1, x2, x3);
    }

    public double getHeight() {
        return getMaxNumber(y1, y2, y3) - getMinNumber(y1, y2, y3);
    }

    public double getArea() {
        return 0.5 * Math.abs((x2 - x1) * (y3 - y1) - (x3 - x1) * (y2 - y1));
    }

    public double getPerimeter() {
        double side1 = Math.sqrt(Math.pow((x2 - x1), 2.0) + Math.pow((y2 - y1), 2));
        double side2 = Math.sqrt(Math.pow((x3 - x2), 2.0) + Math.pow((y3 - y2), 2));
        double side3 = Math.sqrt(Math.pow((x3 - x1), 2.0) + Math.pow((y3 - y1), 2));

        return side1 + side2 + side3;
    }

    @Override
    public String toString() {
        return "Triangle: x1 = " + x1 + ", y1 = " + y1 + ", x2 = " + x2 + ", y2 = " + y2 + ", x3 = " + x3
                + "y3 = " + y3 + ", width = " + getWidth() + ", height = " + getHeight() +
                ", area = " + getArea() + ", perimeter = " + getPerimeter();
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) {
            return true;
        }

        if (o == null || this.getClass() != o.getClass()) {
            return false;
        }

        Triangle triangle = (Triangle) o;

        return Double.compare(triangle.x1, x1) == 0 && Double.compare(triangle.x2, x2) == 0 &&
                Double.compare(triangle.x3, x3) == 0 && Double.compare(triangle.y1, y1) == 0 &&
                Double.compare(triangle.y2, y2) == 0 && Double.compare(triangle.y3, y3) == 0;
    }

    @Override
    public int hashCode() {
        final int prime = 37;
        int hash = 1;
        hash = prime * hash + Double.hashCode(x1);
        hash = prime * hash + Double.hashCode(y1);
        hash = prime * hash + Double.hashCode(x2);
        hash = prime * hash + Double.hashCode(y2);
        hash = prime * hash + Double.hashCode(x3);
        hash = prime * hash + Double.hashCode(y3);
        return hash;
    }
}