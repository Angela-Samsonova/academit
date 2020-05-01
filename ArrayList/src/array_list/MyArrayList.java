package array_list;

import java.util.*;

public class MyArrayList<E> implements List<E> {
    private E[] items;
    private int capacity;
    private int length;
    private int modCount;

    public MyArrayList(int capacity) {
        this.capacity = capacity;
        //noinspection unchecked
        this.items = (E[]) new Object[capacity];
        length = 0;
    }

    public MyArrayList(MyArrayList<E> myArrayList) {
        if (myArrayList == null) {
            throw new IllegalArgumentException("myArrayList must be not null");
        }

        items = myArrayList.items;
        capacity = myArrayList.capacity;
        length = myArrayList.length;
        modCount = myArrayList.modCount;
    }

    public MyArrayList(E[] objects) {
        items = objects;
        capacity = objects.length;
        length = objects.length;
    }

    public MyArrayList(E[] objects, int length) {
        if (objects.length > length) {
            throw new IndexOutOfBoundsException("array length must be <= list length");
        }

        this.items = objects;
        this.length = length;
    }

    @Override
    public int size() {
        return length;
    }

    @Override
    public boolean isEmpty() {
        return length == 0;
    }

    @Override
    public boolean contains(Object o) {
        return Arrays.binarySearch(items, o) >= 0;
    }

    //TODO
    @Override
    public Iterator<E> iterator() {
        return new MyListIterator();
        //должен кидать исключение, если коллекция уменьшилась во время обхода
        // поле count
        //во все методы для изменения этот счетчик, увел-ся на 1
        //у Iterator - поле, которое запоминает текущее значение. При вызове next должен проверить, что счетчик не поменялся
        //если поменялся, кидаем искл-е

    }

    @Override
    public Object[] toArray() {
        Object[] objects = new Object[length];
        System.arraycopy(items, 0, objects, 0, length);

        return objects;
    }

    //TODO
    @Override
    public boolean add(Object o) {
        if (length >= items.length) {
            increaseCapacity();
        }
        items[length] = (E) o;
        ++length;
    }

    //TODO
    @Override
    public boolean remove(Object o) {
        return false;
        //можно обычный remove
    }

    //TODO
    @Override
    public boolean addAll(Collection collection) {
        return false;
    }

    //TODO
    @Override
    public boolean addAll(int i, Collection collection) {
        return false;
    }

    //TODO
    @Override
    public void clear() {


    }

    //TODO
    @Override
    public E get(int i) {
        //TODO бросить исключение если выход за length
        return this.items[i];
    }

    //TODO
    @Override
    public Object set(int i, Object o) {
        //TODO бросить исключение если выход за length
        items[i] = (E) o;
    }

    //TODO
    @Override
    public void add(int i, Object o) {

    }

    //TODO
    @Override
    public E remove(int i) {
        // TODO выход за границы
        if (i < length - 1) {
            System.arraycopy(items, i + 1,
                    items, i, length - i - 1);
        }
        --length;
        return null;
    }

    //TODO
    @Override
    public int indexOf(Object o) {
        return 0;
    }

    //TODO
    @Override
    public int lastIndexOf(Object o) {
        return 0;
    }

    //не надо
    @Override
    public ListIterator<E> listIterator() {
        //noinspection unchecked,ConstantConditions
        return (ListIterator<E>) new MyListIterator();
    }

    //не надо
    @Override
    public ListIterator<E> listIterator(int i) {
        //noinspection unchecked,ConstantConditions
        return (ListIterator<E>) new MyListIterator();
    }

    //не надо
    @Override
    public List<E> subList(int i, int i1) {
        return null;
    }

    //TODO
    @Override
    public boolean retainAll(Collection collection) {
        return false;
    }

    @Override
    public boolean removeAll(Collection collection) {
        return false;
    }

    //TODO
    @Override
    public boolean containsAll(Collection collection) {
        return false;
    }

    //TODO
    @Override
    public Object[] toArray(Object[] objects) {

        System.arraycopy(items, 0, objects, 0, length);

        return objects;
        // return new Object[0];
    }

    private void increaseCapacity() {
        items = Arrays.copyOf(items, items.length * 2);
    }

    public void ensureCapacity(int n) {

    }

    public void trimToSize() {

    }

//    Метод ensureCapacity int гарантирует, что вместимость списка
//    будет >= указанного числа. Если вместимость и так не меньше, то
//    ничего не будет сделано. Иначе массив пересоздастся , но будет
//    иметь вместимость не менее указанной

//    trimToSize () урезает внутренний массив до размера списка
//    полезно, если в списке было много элементов, но стало мало

    private class MyListIterator implements Iterator<E> {
        private int currentIndex = -1;
        private int currentModCount = modCount;

        @Override
        public boolean hasNext() {
            return currentIndex + 1 < length;
        }

        @Override
        public E next() {
            if (!hasNext()) {
                throw new NoSuchElementException("no next element");
            }


            if (currentModCount != modCount) {
                throw new ConcurrentModificationException ("number of items has changed");
            }

            ++currentIndex;
            return items[currentIndex];
        }
    }
}