package samsonova.array_list;

import java.util.*;

public class MyArrayList<E> implements List<E> {
    private E[] items;
    private int size;
    private int modCount;

    public MyArrayList(int capacity) {
        checkCapacity(capacity);

        //noinspection unchecked
        items = (E[]) new Object[capacity];
    }

    public MyArrayList(List<E> list) {
        checkList(list);

        //noinspection unchecked
        items = (E[]) new Object[list.size()];
        size = list.size();

        for (int i = 0; i < list.size(); i++) {
            items[i] = list.get(i);
        }
    }

    public MyArrayList(E[] array) {
        checkArray(array);

        items = Arrays.copyOf(array, array.length);
        size = array.length;
    }

    private void checkList(List<E> list) {
        if (list == null) {
            throw new IllegalArgumentException("list must be not null");
        }
    }

    private void checkArray(E[] elements) {
        if (elements == null) {
            throw new IllegalArgumentException("array must be not null");
        }
    }

    private void checkIndex(int index) {
        if (index < 0) {
            throw new IndexOutOfBoundsException("index " + index + " must be >= 0");
        }

        if (index >= items.length) {
            throw new IndexOutOfBoundsException("index " + index + " must be < array list size");
        }
    }

    private void checkCapacity(int capacity) {
        if (capacity < 0) {
            throw new IllegalArgumentException("capacity = " + capacity + " must be >= 0");
        }
    }

    private void checkCollection(Collection<E> collection) {
        if (collection == null) {
            throw new IllegalArgumentException("collection must be not null");
        }
    }

    @Override
    public E get(int index) {
        checkIndex(index);

        return items[index];
    }

    @Override
    public E set(int index, E element) {
        checkIndex(index);

        E oldData = items[index];
        items[index] = element;

        return oldData;
    }

    private void increaseCapacity() {
        items = Arrays.copyOf(items, items.length * 2);
    }

    public void ensureCapacity(int n) {
        checkCapacity(n);

        if (items.length < n) {
            items = Arrays.copyOf(items, n);
        }
    }

    @Override
    public int size() {
        return size;
    }

    public void trimToSize() {
        if (items.length > size) {
            items = Arrays.copyOf(items, size);
        }
    }

    @Override
    public boolean isEmpty() {
        return size == 0;
    }

    @Override
    public void clear() {
        for (E item : items) {
            item = null;
        }

        this.size = 0;
    }

    @Override
    public boolean contains(Object o) {
        return indexOf(o) != -1;
    }

    @Override
    public boolean containsAll(Collection<?> collection) {
        //noinspection unchecked
        checkCollection((Collection<E>) collection);

        for (Object collectionElement : collection) {
            if (!contains(collectionElement)) {
                return false;
            }
        }

        return true;
    }

    @Override
    public boolean add(E element) {
        ensureCapacity(size + 1);
        add(size, element);

        return true;
    }

    @Override
    public void add(int index, E element) {
        checkIndex(index);

        if (size + 1 > items.length) {
            increaseCapacity();
        }

        if (index < size) {
            System.arraycopy(items, index, items, index + 1, size - index);
        }

        items[index] = element;

        ++size;
        ++modCount;
    }

    @Override
    public boolean addAll(int index, Collection<? extends E> collection) {
        checkIndex(index);
        //noinspection unchecked
        checkCollection((Collection<E>) collection);

        ensureCapacity(size + collection.size());

        System.arraycopy(items, index, items, index + collection.size(), items.length - (index + collection.size()));

        int oldSize = size;

        for (E element : collection) {
            add(index, element);
            index += 1;
        }

        if (oldSize != size) {
            ++modCount;

            return true;
        }

        return false;
    }

    @Override
    public boolean addAll(Collection<? extends E> collection) {
        //noinspection unchecked
        checkCollection((Collection<E>) collection);

        ensureCapacity(size + collection.size());

        addAll(size, collection);

        return false;
    }

    @Override
    public E remove(int index) {
        checkIndex(index);

        E oldData = items[index];

        if (index != size - 1) {
            System.arraycopy(items, index + 1, items, index, size - 1 - index);
        }

        --size;
        ++modCount;

        return oldData;
    }

    @Override
    public boolean remove(Object o) {
        if (indexOf(o) == -1) {
            return false;
        }

        if (indexOf(o) != size - 1) {
            remove(indexOf(o));
        }

        ++modCount;

        return true;
    }

    @Override
    public boolean removeAll(Collection<?> collection) {
        //noinspection unchecked
        checkCollection((Collection<E>) collection);

        int initialSize = size;

        for (int i = size - 1; i >= 0; i--) {
            if (collection.contains(items[i])) {
                remove(i);
            }
        }

        return initialSize != size;
    }

    @Override
    public boolean retainAll(Collection<?> collection) {
        //noinspection unchecked
        checkCollection((Collection<E>) collection);

        int initialSize = size;

        for (int i = size - 1; i >= 0; i--) {
            if (!collection.contains(items[i])) {
                remove(i);
            }
        }

        return initialSize != size;
    }

    @Override
    public int indexOf(Object o) {
        for (int i = 0; i < size; i++) {
            if (Objects.equals(items[i], o)) {
                return i;
            }
        }

        return -1;
    }

    @Override
    public int lastIndexOf(Object o) {
        for (int i = size - 1; i >= 0; i--) {
            if (Objects.equals(items[i], o)) {
                return i;
            }
        }

        return -1;
    }

    @Override
    public Object[] toArray() {
        MyArrayList<E> copiedList = new MyArrayList<>(items);
        copiedList.trimToSize();

        return copiedList.items;
    }

    @Override
    public <T> T[] toArray(T[] array) {
        if (array.length < size) {
            //noinspection unchecked
            return (T[]) Arrays.copyOf(items, size, array.getClass());
        }
        //noinspection unchecked
        array = (T[]) Arrays.copyOf(items, size, array.getClass());

        if (array.length > size) {
            array[size] = null;
        }

        return array;
    }

    @Override
    public Iterator<E> iterator() {
        return new MyListIterator();
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
                throw new ConcurrentModificationException("current modifications count" + currentModCount + "doesn't match" +
                        "modifications count" + modCount);
            }

            ++currentIndex;
            return items[currentIndex];
        }

    }

    @Override
    public String toString() {
        MyArrayList<E> copiedList = new MyArrayList<>(items);
        copiedList.trimToSize();

        return Arrays.toString(copiedList.items);
    }

    //не надо
    @Override
    public ListIterator<E> listIterator() {
        // noinspection ConstantConditions
        return null;
    }

    //не надо
    @Override
    public ListIterator<E> listIterator(int i) {
        // noinspection ConstantConditions
        return null;
    }

    //не надо
    @Override
    public List<E> subList(int i, int i1) {
        // noinspection ConstantConditions
        return null;
    }
}