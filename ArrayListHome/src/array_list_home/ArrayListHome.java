package array_list_home;

import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.util.ArrayList;
import java.util.Scanner;

public class ArrayListHome {
    public static void readLinesFromFile(String path) throws FileNotFoundException {
        ArrayList<String> listOfStringsFromFile = new ArrayList<>();

        try (Scanner scanner = new Scanner(new FileInputStream(path))) {
            while (scanner.hasNextLine()) {
                listOfStringsFromFile.add(scanner.nextLine());
            }
        }

        System.out.println("Список строк файла stringList = " + listOfStringsFromFile + System.lineSeparator());
    }

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

    public static ArrayList<Integer> removeDuplicates(ArrayList<Integer> list) {
        if (list == null) {
            throw new IllegalArgumentException("list must be not null");
        }

        ArrayList<Integer> newIntegersList = new ArrayList<>();

        for (Integer integer : list) {
            if (!newIntegersList.contains(integer)) {
                newIntegersList.add(newIntegersList.size(), integer);
            }
        }

        return newIntegersList;
    }
}