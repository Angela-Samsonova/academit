package ru.academits.range;

public class Range {
    private double from;
    private double to;

    public Range(double from, double to) {
        this.from = from;
        this.to = to;
    }

    @Override
    public String toString() {
        return "(" + this.from + ", " + this.to + ")";
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

    public Range getIntersection(Range range) {
        if (range.to < this.from || range.from > this.to) {
            return null;
        } else if (range.from < this.from && range.to > this.to) {
            return this;
        } else if (this.isInside(range.from) && this.isInside(range.to)) {
            return range;
        } else if (range.from < this.from && this.isInside(range.to)) {
            return new Range(this.from, range.to);
        } else {
            return new Range(range.from, this.to);
        }
    }

    public Range[] getUnion(Range range) {
        Range[] unions = new Range[2];

        if (this.getIntersection(range) == null) {
            unions[0] = this;
            unions[1] = range;
            return unions;
        } else if (this.isInside(range.from) && this.isInside(range.to)) {
            unions[0] = this;
            return unions;
        } else if (range.isInside(this.from) && range.isInside(this.to)) {
            unions[0] = range;
            return unions;
        } else if (range.from < this.from && this.isInside(range.to)) {
            unions[0] = new Range(range.from, this.to);
            return unions;
        } else {
            unions[0] = new Range(this.from, range.to);
            return unions;
        }
    }

    public Range[] getComplement(Range range) {
        Range[] complement = new Range[2];

        if (this.getIntersection(range) == null || (range.from < this.from && range.to > this.to)) {
            return null;
        } else if ((this.isInside(range.from) && range.from > this.from) && (this.isInside(range.to) && range.to < this.to)) {
            Range firstSegment = new Range(this.from, range.from);
            Range secondSegment = new Range(range.to, this.to);
            complement[0] = firstSegment;
            complement[1] = secondSegment;
            return complement;
        } else if (range.from <= this.from && this.isInside(range.to)) {
            complement[0] = new Range(range.to, this.to);
            return complement;
        } else {
            complement[0] = new Range(this.from, range.from);
            return complement;
        }
    }
}

//    Доработать класс ru.academits.range.Range (числовой диапазон).
//        • Написать методы:
//        • Получение интервала-пересечения двух интервалов.
//        Если пересечения нет, выдать null. Если есть, то выдать
//        новый диапазон с соответствующими концами
//        • Получение объединения двух интервалов.
//        Может получиться 1 или 2 отдельных куска
//        • Получение разности двух интервалов.
//        Может получиться 1 или 2 отдельных куска
//        В операциях где может получиться 2 куска выдавайте
//        массив объектов ru.academits.range.Range
//        • Операции пересечения, объединения и разности – по
//        смыслу такие же как для множеств, см. литературу по
//        множествам
//        • Разность нужна несимметричная – из первого интервала
//        вычитаем второй


