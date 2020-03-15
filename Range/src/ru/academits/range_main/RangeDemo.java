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
        Range firstRange = new Range(3.0, 10.0);
        Range secondRange = new Range(4.0, 11.0);
        Range thirdRange = new Range(1.0, 8.0);
        Range fourthRange = new Range(1.0, 11.0);
        Range fifthRange = new Range(11.0, 13.0);

        Range[] ranges = {firstRange, secondRange, thirdRange, fourthRange, fifthRange};

        for (Range e : ranges) {
            System.out.println("Для " + initialRange.toString() + " и " + e.toString() + ":");

            if (initialRange.getIntersection(e) != null) {
                System.out.println("- пересечение: " + initialRange.getIntersection(e).toString());
            } else {
                System.out.println("- нет пересечения");
            }

            if (initialRange.getUnion(e)[1] != null) {
                System.out.println("- объединение: " + initialRange.getUnion(e)[0].toString() + ", " + initialRange.getUnion(e)[1].toString());
            } else {
                System.out.println("- объединение: " + initialRange.getUnion(e)[0].toString());
            }

            if (initialRange.getComplement(e) != null) {
                if (initialRange.getComplement(e)[1] != null) {
                    System.out.println("- разность: " + initialRange.getComplement(e)[0].toString() + ", " + initialRange.getComplement(e)[1].toString());
                } else {
                    System.out.println("- разность: " + initialRange.getComplement(e)[0].toString());
                }
            } else {
                System.out.println("- нет разности");
            }

            System.out.println();
        }

    }
}



