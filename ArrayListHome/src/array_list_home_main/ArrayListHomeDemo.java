package array_list_home_main;

import array_list_home.ArrayListHome;

import java.io.FileNotFoundException;
import java.util.ArrayList;
import java.util.Arrays;

public class ArrayListHomeDemo {
    public static void main(String[] args) {
        System.out.println("Strings list from the file = " + System.lineSeparator()
                + ArrayListHome.getStringsFromFile("input.txt") + System.lineSeparator());

        ArrayList<Integer> integersList1 = new ArrayList<>(Arrays.asList(1, 2, 2, 3, 4, 5, 8, 6, 7, 8));
        ArrayListHome.removeEvenNumbers(integersList1);
        System.out.println("List after even numbers removal  = " + integersList1 + System.lineSeparator());

        ArrayList<Integer> integersList2 = new ArrayList<>(Arrays.asList(1, 2, 7, 3, 4, 2, 5, 6, 2, 7, 1, 8));
        System.out.println("New list of unique items = " + ArrayListHome.getUniqueItemsList(integersList2));
    }
}