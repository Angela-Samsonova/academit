public class RangeClassDemo {
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
    }
}

//    Создать свой класс Range (числовой диапазон). В нём:
//        1. Объявить два вещественных поля from, to
//        2. Описать конструктор, при помощи которого
//        заполняются поля from, to
//        3. Геттеры и сеттеры для полей
//        4. Функция для получения длины
//        5. Создать метод isInside, который принимает
//        вещественное число и возвращает boolean – результат
//        проверки того, принадлежит ли число диапазону
//        • После этого написать небольшую программу с
//        использованием этого класса