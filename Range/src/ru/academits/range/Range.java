package ru.academits.range;

import java.util.Arrays;

public class Range {
    private double from;
    private double to;

    public Range(double from, double to) {
        this.from = from;
        this.to = to;
    }

    @Override
    public String toString() {
        return "(" + from + "; " + to + ")";
    }

    public double getFrom() {
        return from;
    }

    public double getTo() {
        return to;
    }

    public void setFrom(double from) {
        this.from = from;
    }

    public void setTo(double to) {
        this.to = to;
    }

    public double getLength() {
        return to - from;
    }

    public boolean isInside(double number) {
        return number >= from && number <= to;
    }

    public void printRanges(Range[] rangesArray) {
        System.out.println(Arrays.toString(rangesArray));
    }

    public void printResults(Range[] ranges) {
        for (Range e : ranges) {
            System.out.println("For ranges " + this + " and " + e + ":");

            if (this.getIntersection(e) != null) {
                System.out.println("- intersection: " + this.getIntersection(e));
            } else {
                System.out.println("- no intersection");
            }

            System.out.print("- union: ");
            this.printRanges(this.getUnion(e));

            System.out.print("- difference: ");
            this.printRanges(this.getDifference(e));

            System.out.println();
        }
    }

    public Range getIntersection(Range range) {
        if (range.to < from || range.from > to || to == range.from || from == range.to) {
            return null;
        }

        return new Range(Math.max(from, range.from), Math.min(to, range.to));
    }

    public Range[] getUnion(Range range) {
        if (range.to < from || range.from > to) {
            return new Range[]{new Range(from, to), new Range(range.from, range.to)};
        }

        return new Range[]{new Range(Math.min(from, range.from), Math.max(to, range.to))};
    }

    public Range[] getDifference(Range range) {
        if (this.equals(range) || to == range.from || from == range.to || (range.from < from && range.to > to)) {
            return new Range[]{};
        } else if (this.getIntersection(range) == null) {
            return new Range[]{new Range(from, to)};
        } else if (from > range.from && to < range.to) {
            return new Range[]{new Range(from, range.from), new Range(range.to, to)};
        } else if (from < range.from && to >= range.to) {
            return new Range[]{new Range(from, range.from)};
        }

        return new Range[]{new Range(range.to, to)};
    }
}