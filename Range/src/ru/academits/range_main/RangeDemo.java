package ru.academits.range_main;

import ru.academits.range.Range;

public class RangeDemo {
    public static void main(String[] args) {
        Range range = new Range(2, 15);

        System.out.println("Start point is " + range.getFrom());
        System.out.println("End point is " + range.getTo());
        System.out.println("Length of the given range is " + range.getLength());

        double number = 14;

        if (range.isInside(number)) {
            System.out.println("Number " + number + " is inside the given range");
        } else {
            System.out.println("Number " + number + " is not inside the given range");
        }

        range.setFrom(4);
        range.setTo(12);

        System.out.println("Start point is " + range.getFrom());
        System.out.println("End point is " + range.getTo());
        System.out.println("Length of the given range is " + range.getLength());
        System.out.println("Number " + number + " is inside the given range - " + range.isInside(number));

        System.out.println();

        Range initialRange = new Range(2.0, 10.0);
        Range range1 = new Range(3.0, 10.0);
        Range range2 = new Range(11.0, 13.0);
        Range range3 = new Range(1.0, 5.0);

        Range[] ranges = {range1, range2, range3};

        initialRange.printResults(ranges);
    }
}

