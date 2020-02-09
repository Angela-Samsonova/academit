package ru.academits.range;

public class Range {
    private double from;
    private double to;

    public Range(double from, double to) {
        this.from = from;
        this.to = to;
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
}

//    Доработать класс ru.academits.range.Range (числовой диапазон).
//        • Написать методы:
//        • Вычисление длины интервала
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


