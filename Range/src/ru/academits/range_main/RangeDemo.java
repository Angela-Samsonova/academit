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

        Range initialRange = new Range(3.0, 5.0);
        Range range1 = new Range(1.0, 3.0);
        Range range2 = new Range(11.0, 13.0);
        Range range3 = new Range(1.0, 5.0);

        printResults(initialRange, range1);
        printResults(initialRange, range2);
        printResults(initialRange, range3);
    }

    public static void printResults(Range range1, Range range2) {
        System.out.println("For ranges " + range1 + " and " + range2 + ":");

        if (range1.getIntersection(range2) != null) {
            System.out.println("- intersection: " + range1.getIntersection(range2));
        } else {
            System.out.println("- no intersection");
        }

        System.out.print("- union: ");
        Range.printRanges(range1.getUnion(range2));

        System.out.print("- difference: ");
        Range.printRanges(range1.getDifference(range2));

        System.out.println();
    }
}