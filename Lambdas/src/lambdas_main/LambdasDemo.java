package lambdas_main;

import person.Person;

import java.util.Arrays;
import java.util.List;
import java.util.Map;
import java.util.OptionalDouble;
import java.util.stream.Collectors;

public class LambdasDemo {
    public static void main(String[] args) {
        List<Person> personsList = Arrays.asList(
                new Person("Николай", 22),
                new Person("Иван", 40),
                new Person("Евгений", 16),
                new Person("Сергей", 50),
                new Person("Иван", 18),
                new Person("Николай", 15));

        List<String> uniqueNamesList = personsList.stream()
                .map(Person::getName)
                .distinct()
                .collect(Collectors.toList());

        String uniqueNamesString = String.join(", ", uniqueNamesList);

        System.out.println("Имена: " + uniqueNamesString);
        System.out.println();

        List<Person> personsFilteredByAge = personsList.stream()
                .filter(p -> p.getAge() < 18)
                .collect(Collectors.toList());

        OptionalDouble averageAge = personsFilteredByAge.stream()
                .mapToDouble(Person::getAge)
                .average();

        if (averageAge.isEmpty()) {
            System.out.println("Нет людей с таким возрастом");
        } else {
            double averageAgeResult = averageAge.getAsDouble();

            System.out.println("Люди с возрастом < 18: " + personsFilteredByAge);
            System.out.println("Средний возраст: " + averageAgeResult);
            System.out.println();
        }

        Map<String, Double> personsGroupedByName = personsList.stream()
                .collect(Collectors.groupingBy(Person::getName, Collectors.averagingInt(Person::getAge)));

        System.out.println("Группировка по имени + средний возраст = " + personsGroupedByName);
        System.out.println();

        List<Person> personsFilteredByAgeRange = personsList.stream()
                .filter(x -> x.getAge() >= 20 && x.getAge() <= 45)
                .sorted((p1, p2) -> p2.getAge() - p1.getAge())
                .collect(Collectors.toList());

        String names = personsFilteredByAgeRange.stream()
                .map(Person::getName)
                .collect(Collectors.joining(", "));

        System.out.println("Люди с возрастом от 20 до 45 по убыванию возраста: " + names);
    }
}