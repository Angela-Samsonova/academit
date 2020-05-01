package array_list_home;

import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.util.ArrayList;
import java.util.Scanner;

public class ArrayListHome {
    public static ArrayList<String> getLinesFromFile(String path) {
        ArrayList<String> fileStringsList = new ArrayList<>();

        try (Scanner scanner = new Scanner(new FileInputStream(path))) {
            while (scanner.hasNextLine()) {
                fileStringsList.add(scanner.nextLine());
            }
        } catch (FileNotFoundException e) {
            e.printStackTrace();
        }

        return fileStringsList;
    }

    public static void removeEvenItems(ArrayList<Integer> list) {
        if (list == null) {
            throw new IllegalArgumentException("list must be not null");
        }

        int size = list.size();

        for (int i = 0; i < size; i++) {
            list.remove(list.get(i));
            size--;
        }

        System.out.println("Список после удаления четных элементов = " + list + System.lineSeparator());
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