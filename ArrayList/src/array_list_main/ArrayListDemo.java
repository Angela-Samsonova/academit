package array_list_main;

import array_list.MyArrayList;

import java.util.Arrays;
import java.util.Iterator;


public class ArrayListDemo {
    public static void main(String[] args) {
        String[] strings1 = new String[]{"one", "two", "three", "four", "two"};
        String[] strings2 = new String[]{"one", "two", "three"};
        String[] strings3 = new String[]{};
        String[] strings4 = new String[]{"one", "two", "three", "four", null, null};

        MyArrayList<String> stringsList1 = new MyArrayList<>(strings1);
        MyArrayList<String> stringsList2 = new MyArrayList<>(stringsList1);
        MyArrayList<String> stringsList3 = new MyArrayList<>(7);
        MyArrayList<String> stringsList4 = new MyArrayList<>(stringsList1);
        MyArrayList<String> stringsList5 = new MyArrayList<>(5);
        MyArrayList<String> stringsList6 = new MyArrayList<>(strings3);
        MyArrayList<String> stringsList7 = new MyArrayList<>(strings4);

        System.out.println("stringsList7 = " + stringsList7);
        System.out.println("stringsList7.addAll(4, stringsList2) = " + stringsList7.addAll(4, stringsList2));
        System.out.println("stringsList7 = " + stringsList7);

        System.out.println("stringsList1 = " + stringsList1);
        System.out.println("index of \"two\" in stringsList1 = " + stringsList1.indexOf("two"));
        System.out.println("index of \"tw\" in stringsList1 = " + stringsList1.indexOf("tw"));
        System.out.println("last index of \"two\" in stringsList1 = " + stringsList1.lastIndexOf("two"));
        System.out.println("stringsList1.getCapacity() = " + stringsList1.getCapacity());
        System.out.println("stringsList1.isEmpty() = " + stringsList1.isEmpty());
        System.out.println("stringsList1.toArray() = " + stringsList1);
        System.out.println("stringsList2 = " + (stringsList2));
        stringsList2.add("five");
        System.out.println("stringsList2.add(\"five\") = " + stringsList2);
        System.out.println("stringsList2.getModCount() = " + stringsList2.getModCount());
        System.out.println("stringsList2.remove(\"tw\") = " + stringsList2.remove("tw"));
        stringsList2.remove("two");
        System.out.println("stringsList2.remove(\"two\") = " + stringsList2);
        stringsList2.trimToSize();
        System.out.println("stringsList2.trimToSize() = " + stringsList2);
        stringsList2.addAll(stringsList1);
        System.out.println("stringsList2.addAll(stringsList1) = " + stringsList2);
        stringsList2.retainAll(stringsList1);
        System.out.println("stringsList2.retainAll(stringsList1) = " + stringsList2);
        stringsList2.trimToSize();
        System.out.println("stringsList2.trimToSize() = " + stringsList2);
        stringsList2.removeAll(stringsList1);
        System.out.println("stringsList2.removeAll(stringsList1) = " + stringsList2);
        System.out.println("stringsList2 size = " + stringsList2.size());
        stringsList2.trimToSize();
        System.out.println("stringsList2.trimToSize() = " + stringsList2);
        stringsList2.add("six");
        System.out.println("stringsList2.add(\"six\") = " + stringsList2);
        stringsList2.add("seven");
        System.out.println("stringsList2.add(\"seven\") = " + stringsList2);
        System.out.println("stringsList2 size = " + stringsList2.size());
        stringsList2.clear();
        System.out.println("stringsList2 after clear() = " + stringsList2);
        System.out.println("stringsList2 size = " + stringsList2.size());
        System.out.println("stringsList2 capacity = " + stringsList2.getCapacity());
        stringsList2.trimToSize();
        System.out.println("stringsList2.trimToSize() = " + stringsList2);
        System.out.println("stringsList2 capacity = " + stringsList2.getCapacity());

        System.out.println("stringsList3 = " + stringsList3);
        stringsList3.increaseCapacity();
        System.out.println("stringsList3.increaseCapacity(), stringsList3.getCapacity() = " + stringsList3.getCapacity());
        System.out.println("stringsList3.isEmpty() = " + stringsList3.isEmpty());
        System.out.println("stringsList3.getItems() = " + Arrays.toString(stringsList3.getItems()));
        stringsList3.addAll(0, stringsList4);
        System.out.println("stringsList3 = stringsList3.addAll(0, stringsList4) = " + stringsList3);

        System.out.println("stringsList4 = " + stringsList4);
        System.out.println("stringsList4.getCapacity() = " + stringsList4.getCapacity());

        System.out.println("stringsList5 = " + stringsList5);
        stringsList5.setItems(strings2);
        System.out.println("stringsList5.setItems(strings2) = " + stringsList5);
        System.out.println("stringsList5.getCapacity() = " + stringsList5.getCapacity());
        stringsList5.setCapacity(7);
        System.out.println("stringsList5.setCapacity(7) + stringsList5.getCapacity() = " + stringsList5.getCapacity());
        System.out.println("stringsList5.getModCount() =  " + stringsList5.getModCount());
        System.out.println("stringsList5 = " + stringsList5);
        System.out.println("stringsList5.containsAll(stringsList1) = " + stringsList5.containsAll(stringsList1));
        System.out.println("stringsList1.containsAll(stringsList5) = " + stringsList1.containsAll(stringsList5));
        stringsList5.trimToSize();
        System.out.println("stringsList5.trimToSize() = " + stringsList5);
        System.out.println("stringsList1.containsAll(stringsList5) = " + stringsList1.containsAll(stringsList5));
        stringsList5.add("four");
        System.out.println("stringsList5.add(\"four\") = " + stringsList5);

        stringsList5.remove("four");
        stringsList5.trimToSize();
        System.out.println("stringsList5 = stringsList5.remove(\"four\") = " + stringsList5);
        System.out.println("stringsList5.size() = " + stringsList5.size());
        System.out.println("stringsList5.contains(\"one\") = " + stringsList5.contains("one"));

        System.out.println("stringsList6 = " + stringsList6);
        System.out.println("stringsList6.isEmpty() = " + stringsList6.isEmpty());

        //noinspection ForLoopReplaceableByForEach
        for (Iterator<String> i = stringsList5.iterator(); i.hasNext(); ) {
            String text = i.next();
            System.out.println(text);
        }
    }
}