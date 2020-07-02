package extra_methods;

import java.util.ArrayList;
import java.util.Comparator;

import  list.SinglyLinkedList;
import person.Person;

public class Extra {
    //даление дубликатов из ArrayList
    public static void removeDuplicates(ArrayList<Integer> list) {
        if (list == null) {
            throw new IllegalArgumentException("list must be not null");
        }

        int size = list.size() - 1;

        for (int i = 0; i < size; i++) {
            Integer element = list.get(i);

            while (list.indexOf(element) != list.lastIndexOf(element)) {
                list.remove(list.lastIndexOf(element));
                size--;
                i--;
            }
        }

        System.out.println("Список целых чисел без дубликатов = " + list);
    }

    //получение текущего размера списка
    //public int getActualSize() {
    //    int count = 0;

    //    if (head != null) {
    //       count++;

    //        while (head.getNext() != null) {
    //            count++;
    //             head = head.getNext();
    //        }
    //    } else {
    //        return 0;
    //    }

    //    return count;
    // }

    //поиск max и min 3 чисел
    private static double getMaxNumber(double a1, double a2, double a3) {
        if (a2 <= a1 && a3 <= a1) {
            return a1;
        } else if (a1 <= a2 && a3 <= a2) {
            return a2;
        } else {
            return a3;
        }
    }

    private static double getMinNumber(double a1, double a2, double a3) {
        if (a1 <= a2 && a1 <= a3) {
            return a1;
        } else if (a2 <= a1 && a2 <= a3) {
            return a2;
        } else {
            return a3;
        }
    }

//удаление четных чисел из списка
public static void removeEvenNumbers(ArrayList<Integer> list) {
    if (list == null) {
        throw new IllegalArgumentException("list must be not null");
    }

    for (int i = 0; i < list.size(); i++) {
        if (list.get(i) % 2 == 0) {
            list.remove(list.get(i));
            i--;
        }
    }

    System.out.println("Список после удаления четных чисел = " + list + System.lineSeparator());
}

    public static class personComparatorByAge implements Comparator<Person> {
        @Override
        public int compare(Person p1, Person p2) {
            return p2.getAge() - p1.getAge();
        }
    }



 //удаление первого вхождения списка
//        if (indexOf(o) != items.length - 1) {
//            int i = indexOf(o);
//            System.arraycopy(items, i + 1, items, i, size - (i + 1));
//        }

//        --size;
//        ++modCount;

}
