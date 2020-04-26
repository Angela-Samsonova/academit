package ru.academits.shapes_main;

import ru.academits.shapes.*;

import java.util.Arrays;

public class ShapesDemo {
    public static void main(String[] args) {
        Shape[] shapes = {new Circle(3.0), new Square(3.5), new Rectangle(3.0, 2.0),
                new Triangle(1.0, 2.0, 3.0, 4.0, 5.0, 2.0), new Rectangle(1.0, 6.0),
                new Square(5.5), new Circle(5.5)};

        System.out.println("The shape with the largest area is " + getLargestShape(shapes).toString());

        System.out.println("The shape with the second longest perimeter is " + getShapeWithSecondLongestPerimeter(shapes).toString());
    }

    public static Shape getLargestShape(Shape[] shapes) {
        Arrays.sort(shapes, new AreaComparator());
        return shapes[shapes.length - 1];
    }

    public static Shape getShapeWithSecondLongestPerimeter(Shape[] shapes) {
        Arrays.sort(shapes, new PerimeterComparator());
        return shapes[shapes.length - 2];
    }
}