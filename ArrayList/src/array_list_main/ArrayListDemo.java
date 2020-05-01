package array_list_main;

import array_list.MyArrayList;

public class ArrayListDemo {
    public static void main(String[] args) {
        String [] strings = new String[]{"one", "two", "three", "four"};

        MyArrayList<String> stringsList1 = new MyArrayList<>(strings);
        MyArrayList<String> stringsList2 = new MyArrayList<>(strings, 10);
        MyArrayList<String> stringsList3 = new MyArrayList<>(7);
        MyArrayList<String> stringsList4 = new MyArrayList<>(stringsList1);

        System.out.println(stringsList1);

    }

}
