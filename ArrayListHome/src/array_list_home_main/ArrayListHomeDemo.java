package array_list_home_main;

import array_list_home.ArrayListHome;

import java.io.FileNotFoundException;
import java.util.ArrayList;
import java.util.Arrays;

public class ArrayListHomeDemo {
    public static void main(String[] args) throws FileNotFoundException {
        System.out.println("Список строк fileStringsList из файла = " + System.lineSeparator()
                + ArrayListHome.getLinesFromFile("input.txt") + System.lineSeparator());

        ArrayList<Integer> integersList1 = new ArrayList<>(Arrays.asList(1, 2, 3, 4, 5, 6, 7, 8));
        ArrayListHome.removeEvenItems(integersList1);

        ArrayList<Integer> integersList2 = new ArrayList<>(Arrays.asList(1, 2, 7, 3, 4, 2, 5, 6, 2, 7, 1, 8));
        System.out.println("Новый список без дубликатов = " + ArrayListHome.getUniqueItemsList(integersList2));
    }
}