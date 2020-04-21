package array_list_home;

import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Scanner;

public class ArrayListHome {
    public static void main(String[] args) throws FileNotFoundException {
        ArrayList<String> stringList = new ArrayList<>();

        try (Scanner scanner = new Scanner(new FileInputStream("input.txt"))) {
            while (scanner.hasNextLine()) {
                stringList.add(scanner.nextLine());
            }
        }

        System.out.println("Список строк stringList = " + stringList + System.lineSeparator());

        Integer[] integersArray1 = new Integer[]{1, 2, 3, 4, 5, 6, 7, 8};
        ArrayList<Integer> integers1 = new ArrayList<>(Arrays.asList(integersArray1));

        int j = integers1.size();

        for (int i = 0; i < j; i++) {
            Integer element = integers1.get(i);

            if (element % 2 == 0) {
                integers1.remove(element);
                j--;
                i--;
            }
        }

        System.out.println("Список целых чисел integers1 = " + integers1 + System.lineSeparator());


        Integer[] integersArray2 = new Integer[]{1, 2, 7, 3, 4, 2, 5, 6, 2, 7, 1, 8};
        ArrayList<Integer> integers2 = new ArrayList<>(Arrays.asList(integersArray2));

        j = integers2.size() - 1;

        for (int i = 0; i < j; i++) {
            Integer element = integers2.get(i);

            while (integers2.indexOf(element) != integers2.lastIndexOf(element)) {
                integers2.remove(integers2.lastIndexOf(element));
                j--;
                i--;
            }
        }

        System.out.println("Список целых чисел integers2 = " + integers2 + System.lineSeparator());
    }
}