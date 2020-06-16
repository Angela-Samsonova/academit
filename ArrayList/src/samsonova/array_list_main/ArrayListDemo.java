package samsonova.array_list_main;

import samsonova.array_list.MyArrayList;

import java.util.Arrays;
import java.util.Iterator;
import java.util.List;


public class ArrayListDemo {
    public static void main(String[] args) {
        String[] strings1 = new String[]{"one", "two", "three", "four", "two"};
        String[] strings2 = new String[]{"one", "two", "three", "four", null, null};
        List<String> list = Arrays.asList(strings1);

        MyArrayList<String> stringsList1 = new MyArrayList<>(strings1);
        MyArrayList<String> stringsList2 = new MyArrayList<>(stringsList1);
        MyArrayList<String> stringsList3 = new MyArrayList<>(7);
        MyArrayList<String> stringsList4 = new MyArrayList<>(stringsList1);
        MyArrayList<String> stringsList5 = new MyArrayList<>(5);
        MyArrayList<String> stringsList6 = new MyArrayList<>(strings2);
        MyArrayList<String> stringsList7 = new MyArrayList<>(list);

        System.out.println("stringsList1 = " + stringsList1);
        System.out.println("index of \"two\" in stringsList1 = " + stringsList1.indexOf("two"));
        System.out.println("index of \"tw\" in stringsList1 = " + stringsList1.indexOf("tw"));
        System.out.println("last index of \"two\" in stringsList1 = " + stringsList1.lastIndexOf("two"));
        System.out.println("stringsList1.isEmpty() = " + stringsList1.isEmpty());
        System.out.println("stringsList1.toArray() = " + stringsList1);
        System.out.println("stringsList2 = " + (stringsList2));
        stringsList2.add("five");
        System.out.println("stringsList2.add(\"five\") = " + stringsList2);
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
        System.out.println("stringsList2.add(\"six\") = " + stringsList2.add("six"));
        System.out.println("stringsList2 = " + stringsList2);
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
        stringsList2.trimToSize();
        System.out.println("stringsList2.trimToSize() = " + stringsList2);
        System.out.println("stringsList3 = " + stringsList3);
        stringsList3.ensureCapacity(5);
        System.out.println("stringsList3.isEmpty() = " + stringsList3.isEmpty());
        stringsList3.addAll(0, stringsList4);
        System.out.println("stringsList3 = stringsList3.addAll(0, stringsList4) = " + stringsList3);

        System.out.println("stringsList4 = " + stringsList4);

        System.out.println("stringsList5 = " + stringsList5);
        stringsList5.add("two");
        System.out.println("stringsList5.add(\"two\") = " + stringsList5);
        System.out.println("stringsList5.containsAll(stringsList1) = " + stringsList5.containsAll(stringsList1));
        System.out.println("stringsList1.containsAll(stringsList5) = " + stringsList1.containsAll(stringsList5));
        stringsList5.trimToSize();
        System.out.println("stringsList5.trimToSize() = " + stringsList5);
        stringsList5.add("four");
        System.out.println("stringsList5.add(\"four\") = " + stringsList5);
        stringsList5.add("one");
        System.out.println("stringsList5.add(\"one\") = " + stringsList5);
        System.out.println("stringsList5.contains(\"one\") = " + stringsList5.contains("one"));
        stringsList5.remove("four");
        System.out.println("stringsList5.remove(\"four\") = " + stringsList5);
        stringsList5.trimToSize();
        System.out.println("stringsList5.trimToSize() = " + stringsList5);

        System.out.println("stringsList5.size() = " + stringsList5.size());
        System.out.println("stringsList5 = stringsList5.remove(\"four\") = " + stringsList5);
        System.out.println("stringsList5.contains(\"four\") = " + stringsList5.contains("four"));
        System.out.println("stringsList5.size() = " + stringsList5.size());
        stringsList5.add("four");
        System.out.println("stringsList5.add(\"four\") = " + stringsList5);

        System.out.println("stringsList6 = " + stringsList6);
        System.out.println("stringsList6.addAll(4, stringsList2) = " + stringsList6.addAll(4, stringsList2));
        System.out.println("stringsList6 = " + stringsList6);
        System.out.println("stringsList6.addAll(4, stringsList3) = " + stringsList6.addAll(4, stringsList3));
        System.out.println("stringsList6 = " + stringsList6);

        System.out.println("stringsLis7 = " + stringsList7);

        //noinspection ForLoopReplaceableByForEach
        for (Iterator<String> i = stringsList5.iterator(); i.hasNext(); ) {
            String text = i.next();
            System.out.print(text + " ");
        }
    }
}