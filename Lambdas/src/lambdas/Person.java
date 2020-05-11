package lambdas;

import java.util.Comparator;

public class Person {
    private String name;
    private int age;

    public Person(String name, int age) {
        this.name = name;
        this.age = age;
    }

    public String getName() {
        return name;
    }

    public int getAge() {
        return age;
    }

    @Override
    public String toString() {
        return "Person (" +
                "name='" + name + '\'' +
                ", age=" + age +
                ')';
    }

    public static class personComparatorByAge implements Comparator<Person> {
        @Override
        public int compare(Person p1, Person p2) {
            return p2.getAge() - p1.getAge();
        }
    }
}