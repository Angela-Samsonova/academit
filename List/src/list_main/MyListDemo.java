package list_main;

import list.ListItem;
import list.SinglyLinkedList;

import java.util.Arrays;

public class MyListDemo {
    public static void main(String[] args) {
        SinglyLinkedList<Integer> integersList1 = new SinglyLinkedList<>(new ListItem<>(0), 4);
        integersList1.setHead(new ListItem<>(10));
        integersList1.insertAsFirstItem(new ListItem<>(11));
        integersList1.insertAsFirstItem(new ListItem<>(12));
        integersList1.insertAsFirstItem(new ListItem<>(13));

        SinglyLinkedList<Integer> integersList2 = new SinglyLinkedList<>(new ListItem<>(0), 5);
        integersList2.insertAsFirstItem(new ListItem<>(1));
        System.out.println("actual size = " + integersList2.getSize() + " " + Arrays.toString(integersList2.toArray()));
        integersList2.insertAsFirstItem(new ListItem<>(2));
        System.out.println("actual size = " + integersList2.getSize() + " " + Arrays.toString(integersList2.toArray()));
        integersList2.insertAsFirstItem(new ListItem<>(6));
        integersList2.insertAsFirstItem(new ListItem<>(8));
        integersList2.insertAsFirstItem(new ListItem<>(8));
        integersList2.setCount(7);
        integersList2.insertAsFirstItem(new ListItem<>(5));

        System.out.println("integersList2 = " + Arrays.toString(integersList2.toArray()));
        System.out.println("getCount() result = " + integersList2.getCount());
        System.out.println("getSize() result = " + integersList2.getSize());
        System.out.println("getFirstItemData() result = " + integersList2.getFirstItemData());
        System.out.println("getHead() result = " + integersList2.getHead());
        System.out.println("getItemDataByIndex(2) result = " + integersList2.getItemDataByIndex(2));
        System.out.println("setItemDataByIndex(3, 2) result = " + integersList2.setItemDataByIndex(3, 2) + ", integersList2 = " + Arrays.toString(integersList2.toArray()));
        System.out.println("deleteItemByIndex(1) result = " + integersList2.deleteItemByIndex(1) + ", integersList2 = " + Arrays.toString(integersList2.toArray()));

        integersList2.insertItemByIndex(new ListItem<>(4), 1);
        System.out.println("insertItemByIndex(new ListItem<>(4), 1) result = " + Arrays.toString(integersList2.toArray()));

        System.out.println("deleteItemByValue(7) result = " + integersList2.deleteItemByValue(7) + " " + Arrays.toString(integersList2.toArray()));
        System.out.println("deleteItemByValue(4) result = " + integersList2.deleteItemByValue(4) + " " + Arrays.toString(integersList2.toArray()));

        integersList2.insertAsFirstItem(new ListItem<>(8));
        System.out.println("insertAsFirstItem(new ListItem<>(8)) result = " + Arrays.toString(integersList2.toArray()));

        System.out.println("deleteFirstItem() result = " + integersList2.deleteFirstItem() + ", " + Arrays.toString(integersList2.toArray()));

        System.out.println("integersList1 = " + Arrays.toString(integersList1.toArray()));
        integersList2.copyList(integersList1);
        System.out.println("copyList(integersList1) result = " + Arrays.toString(integersList2.toArray()));

        integersList2.revertList();
        System.out.println("revertList() result = " + Arrays.toString(integersList2.toArray()));
    }
}