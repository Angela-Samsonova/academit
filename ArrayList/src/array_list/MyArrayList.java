package array_list;

import java.util.*;

public class MyArrayList<E> implements List<E> {
    private E[] items;
    private int size;
    private int modCount;

    public MyArrayList(int capacity) {
        checkCapacity(capacity);

        //noinspection unchecked
        items = (E[]) new Object[capacity];
        size = 0;
        modCount = 0;
    }

    public MyArrayList(MyArrayList<E> list) {
        checkArrayList(list);

        items = list.items;
        size = list.size;
        modCount = 0;
    }

    public MyArrayList(E[] array) {
        checkArray(array);

        items = array;
        size = array.length;
        modCount = 0;
    }

    public E[] getItems() {
        return items;
    }

    public void setItems(E[] array) {
        checkArray(array);

        if (array.length > items.length) {
            ensureCapacity(array.length);
        }

        items = array;
        size = array.length;
        modCount++;
    }

    public int getCapacity() {
        return items.length;
    }

    public void setCapacity(int n) {
        items = Arrays.copyOf(items, n);
    }

    public void setSize(int size) {
        this.size = size;
    }

    public int getModCount() {
        return modCount;
    }

    public void checkArrayList(MyArrayList<E> list) {
        if (list == null) {
            throw new IllegalArgumentException("list must be not null");
        }
    }

    public void checkArray(E[] objects) {
        if (objects == null) {
            throw new IllegalArgumentException("array must be not null");
        }
    }

    public void checkIndex(int i) {
        if (i < 0) {
            throw new IllegalArgumentException("index " + i + " must be >= 0");
        }

        if (i > size()) {
            throw new IndexOutOfBoundsException("index " + i + " must be <= list size " + size());
        }
    }

    public void checkCapacity(int capacity) {
        if (capacity < 0) {
            throw new IllegalArgumentException("capacity = " + capacity + " must be >= 0");
        }
    }

    public void checkCollection(Collection<E> collection) {
        if (collection == null) {
            throw new IllegalArgumentException("collection must be not null");
        }
    }

    @Override
    public String toString() {
        return Arrays.toString(items);
    }

    @Override
    public int size() {
        return size;
    }

    @Override
    public boolean isEmpty() {
        return size == 0;
    }

    @Override
    public boolean contains(Object o) {
        for (Object obj : items) {
            if (obj != null && obj.equals(o)) {
                return true;
            }
        }

        return false;
    }

    @Override
    public Iterator<E> iterator() {
        return new MyListIterator();
    }

    @Override
    public Object[] toArray() {
        return items;
    }

    @Override
    public boolean add(Object o) {
        if ((size + 1) > getCapacity()) {
            ensureCapacity(size + 1);
        }

        //noinspection unchecked
        items[size] = (E) o;
        ++size;
        ++modCount;

        return true;
    }

    @Override
    public boolean remove(Object o) {
        if (!contains(o)) {
            return false;
        }

        for (int i = size - 1; i >= 0; i--) {
            if (items[i].equals(o)) {
                if (i != size - 1) {
                    System.arraycopy(items, i + 1, items, i, size - (i + 1));
                }

                --size;
                ++modCount;
            }
        }

        return true;
    }

    @Override
    public boolean addAll(Collection collection) {
        //noinspection unchecked
        checkCollection(collection);
        //noinspection unchecked
        E[] colItems = (E[]) collection.toArray();

        if ((size + collection.size()) > items.length) {
            ensureCapacity(size + collection.size());
        }

        System.arraycopy(colItems, 0, items, size, colItems.length);

        size = size + colItems.length;
        ++modCount;

        return true;
    }

    @Override
    public boolean addAll(int i, Collection collection) {
        checkIndex(i);
        //noinspection unchecked
        checkCollection(collection);

        //noinspection unchecked
        E[] colItems = (E[]) collection.toArray();

        if ((size + collection.size()) > items.length) {
            ensureCapacity(size + collection.size());
        }

        if (i == size - 1) {
            addAll(collection);
        } else {
            System.arraycopy(colItems, 0, items, i, colItems.length);
        }

        size = items.length + colItems.length;
        ++modCount;

        return true;
    }

    @Override
    public void clear() {
        setSize(0);
    }

    @Override
    public E get(int i) {
        checkIndex(i);

        return items[i];
    }

    @Override
    public E set(int i, E o) {
        checkIndex(i);

        E oldData = items[i];
        items[i] = o;

        return oldData;
    }

    @Override
    public void add(int i, Object o) {
        checkIndex(i);

        if (size + 1 > items.length) {
            ensureCapacity(size + 1);
        }

        if (i != size + 1) {
            System.arraycopy(items, i, items, i + 1, size - i);
        }

        //noinspection unchecked
        items[i] = (E) o;

        ++size;
        ++modCount;
    }

    @Override
    public E remove(int i) {
        checkIndex(i);

        E oldData = items[i];

        if (i != size - 1) {
            System.arraycopy(items, i + 1, items, i, size - i);
        }

        --size;
        ++modCount;

        return oldData;
    }

    @Override
    public int indexOf(Object o) {
        for (int i = 0; i < size; i++) {
            if (items[i].equals(o)) {
                return i;
            }
        }

        return -1;
    }

    @Override
    public int lastIndexOf(Object o) {
        for (int i = size - 1; i >= 0; i--) {
            if (items[i].equals(o)) {
                return i;
            }
        }

        return -1;
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
        return new ArrayList<>();
    }

    @Override
    public boolean retainAll(Collection collection) {
        //noinspection unchecked
        checkCollection(collection);

        Object[] collectionElements = collection.toArray();

        if (Arrays.equals(items, collectionElements)) {
            return true;
        }

        int initialSize = size;

        for (E element : items) {
            if (!collection.contains(element)) {
                remove(element);
            }
        }

        return initialSize != size;
    }

    @Override
    public boolean removeAll(Collection collection) {
        //noinspection unchecked
        checkCollection(collection);

        int initialSize = size;

        for (int i = size - 1; i >= 0; i--) {
            if (collection.contains(items[i])) {
                if (i != size - 1) {
                    System.arraycopy(items, i + 1, items, i, size - (i + 1));
                }

                --size;
                ++modCount;
            }
        }

        return initialSize != size;
    }

    @Override
    public boolean containsAll(Collection collection) {
        //noinspection unchecked
        checkCollection(collection);

        Object[] collectionElements = collection.toArray();

        if (Arrays.equals(items, collectionElements)) {
            return true;
        }

        for (Object collectionElement : collectionElements) {
            if (!contains(collectionElement)) {
                return false;
            }
        }

        return true;
    }

    @Override
    public <T> T[] toArray(T[] t) {
        if (t.length < size) {
            //noinspection unchecked
            return (T[]) Arrays.copyOf(items, size, t.getClass());
        }

        //noinspection unchecked
        T[] initArray = (T[]) Arrays.copyOf(items, size, t.getClass());
        System.arraycopy(initArray, 0, t, 0, size);

        if (t.length > size) {
            t[size] = null;
        }

        return t;
    }

    public void increaseCapacity() {
        items = Arrays.copyOf(items, items.length * 2);
    }

    public void ensureCapacity(int n) {
        checkCapacity(n);

        if (items.length < n) {
            items = Arrays.copyOf(items, n);
            setCapacity(n);
        }
    }

    public void trimToSize() {
        if (items.length > size) {
            setCapacity(size);
            items = Arrays.copyOf(items, size);
        }
    }

    private class MyListIterator implements Iterator<E> {
        private int currentIndex = -1;
        private final int currentModCount = modCount;

        @Override
        public boolean hasNext() {
            return currentIndex + 1 < size;
        }

        @Override
        public E next() {
            if (!hasNext()) {
                throw new NoSuchElementException("next element doesn't exist");
            }

            if (currentModCount != modCount) {
                throw new ConcurrentModificationException("number of elements has changed");
            }

            ++currentIndex;
            return items[currentIndex];
        }
    }
}