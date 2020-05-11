package lambdas_main;

import lambdas.Person;

import java.util.Arrays;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

public class LambdasDemo {
    public static void main(String[] args) {
        List<Person> personsList = Arrays.asList(new Person("Николай", 22), new Person("Иван", 40),
                new Person("Евгений", 16), new Person("Сергей", 50), new Person("Иван", 18),
                new Person("Николай", 15));

        String allNamesString = personsList.stream().map(Person::getName).distinct().collect(Collectors.joining(", "));

        System.out.println("Уникальные имена: " + allNamesString + System.lineSeparator());

        //здесь я не могу понять, как и куда добавить метод isPresent(), который требуют в ворнинге
        List<Person> filteredByAge = personsList.stream().filter(p -> p.getAge() < 18).collect(Collectors.toList());
        double averageAge = personsList.stream().filter(p -> p.getAge() < 18).
                mapToInt(Person::getAge).average().getAsDouble();

        System.out.println("Люди с возрастом < 18: " + filteredByAge.toString() + System.lineSeparator() +
                "Средний возраст: " + averageAge  + System.lineSeparator());

        Map<String, Double> personsByNames = personsList.stream().collect(Collectors.groupingBy(Person::getName,
                        Collectors.averagingInt(Person::getAge)));

        System.out.println("Группировка по имени + средний возраст = " + personsByNames + System.lineSeparator());

        List<Person> filteredByAgeRange = personsList.stream().filter((x) -> x.getAge() >= 20 && x.getAge() <= 45).
                sorted((p1, p2) -> p2.getAge() - p1.getAge()).collect(Collectors.toList());
        String names = filteredByAgeRange.stream().map(Person::getName).collect(Collectors.joining(", "));

        //здесь сделала два варианта вывода, не знаю как лучше
        System.out.println("Люди с возрастом от 20 до 45 по убыванию возраста: " + filteredByAgeRange.toString());
        System.out.println("Люди с возрастом от 20 до 45 по убыванию возраста: " + names);
    }
}