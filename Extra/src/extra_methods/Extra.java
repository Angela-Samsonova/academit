package extra_methods;

import java.util.ArrayList;

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

}
