package array_list_home;

import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.util.ArrayList;
import java.util.Scanner;

public class ArrayListHome {
    public static ArrayList<String> getStringsFromFile(String path) {
        try (Scanner scanner = new Scanner(new FileInputStream(path))) {
            ArrayList<String> fileStringsList = new ArrayList<>();

            while (scanner.hasNextLine()) {
                fileStringsList.add(scanner.nextLine());
            }

            return fileStringsList;
        } catch (FileNotFoundException e) {
            System.out.println("FileNotFoundException: " + e.getMessage());

            return null;
        }
    }

    public static void removeEvenNumbers(ArrayList<Integer> list) {
        if (list == null) {
            throw new IllegalArgumentException("list must be not null");
        }

        for (int i = 0; i < list.size(); i++) {
            if (list.get(i) % 2 == 0) {
                list.remove(i);
                i--;
            }
        }
    }

    public static ArrayList<Integer> getUniqueItemsList(ArrayList<Integer> list) {
        if (list == null) {
            throw new IllegalArgumentException("list must be not null");
        }

        ArrayList<Integer> uniqueItemsList = new ArrayList<>();

        for (Integer integer : list) {
            if (!uniqueItemsList.contains(integer)) {
                uniqueItemsList.add(integer);
            }
        }

        return uniqueItemsList;
    }
}