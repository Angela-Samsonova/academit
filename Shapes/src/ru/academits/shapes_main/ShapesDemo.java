package ru.academits.shapes_main;

import ru.academits.shapes.*;

import java.util.Arrays;
import java.util.Comparator;

public class ShapesDemo {
    public static void main(String[] args) {
        Shape[] shapes = new Shape[7];
        shapes[0] = new Circle(3.0);
        shapes[1] = new Square(3.5);
        shapes[2] = new Rectangle(3.0, 2.0);
        shapes[3] = new Triangle(1.0, 2.0, 3.0, 4.0, 5.0, 2.0);
        shapes[4] = new Rectangle(1.0, 6.0);
        shapes[5] = new Square(5.5);
        shapes[6] = new Circle(5.5);

        System.out.println("The shape with the largest area is " +  getLargestShape(shapes).toString());

        System.out.println("The shape with the second longest perimeter is " + getShapeWithSecondLongestPerimeter(shapes).toString());
    }

    static class SortByArea implements Comparator<Shape> {
        public int compare(Shape a, Shape b) {
            return Double.compare(a.getArea(), b.getArea());
        }
    }

    static class SortByPerimeter implements Comparator<Shape> {
        public int compare(Shape a, Shape b) {
            return Double.compare(a.getPerimeter(), b.getPerimeter());
        }
    }

    public static Shape getLargestShape(Shape[] shapes) {
        Arrays.sort(shapes, new SortByArea());
        return shapes[shapes.length - 1];
    }

    public static Shape getShapeWithSecondLongestPerimeter(Shape[] shapes) {
        Arrays.sort(shapes, new SortByPerimeter());
        return shapes[shapes.length - 2];
    }
}