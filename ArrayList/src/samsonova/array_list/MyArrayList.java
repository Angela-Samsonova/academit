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

        if (list.size() == 0) {
            //noinspection unchecked
            items = (E[]) new Object[]{};
        }

        //noinspection unchecked
        items = (E[]) new Object[list.size()];
        size = list.size();

        int j = 0;

        for (E e : list) {
            items[j] = e;
            j++;
        }

    }

    public MyArrayList(E[] array) {
        checkArray(array);

        items = Arrays.copyOf(array, array.length);
        size = array.length;
    }

    private void checkList(List<E> list) {
        if (list == null) {
            throw new IllegalArgumentException("List must be not null");
        }
    }

    private void checkArray(E[] elements) {
        if (elements == null) {
            throw new IllegalArgumentException("Array must be not null");
        }
    }

    private void checkIndex(int index) {
        if (index < 0) {
            throw new IndexOutOfBoundsException("Index " + index + " must be >= 0");
        }

        if (index >= items.length) {
            throw new IndexOutOfBoundsException("Index " + index + " must be < array list size");
        }
    }

    private void checkCapacity(int capacity) {
        if (capacity < 0) {
            throw new IllegalArgumentException("Capacity = " + capacity + " must be >= 0");
        }
    }

    private void checkCollection(Collection<?> collection) {
        if (collection == null) {
            throw new IllegalArgumentException("Collection must be not null");
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
        items = Arrays.copyOf(items, (items.length + 1) * 2);
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
        for (int i = 0; i < items.length; i++) {
            if (items[i] != null) {
                items[i] = null;
            }
        }

        size = 0;
    }

    @Override
    public boolean contains(Object o) {
        return indexOf(o) != -1;
    }

    @Override
    public boolean containsAll(Collection<?> collection) {
        checkCollection(collection);

        for (Object collectionElement : collection) {
            if (!contains(collectionElement)) {
                return false;
            }
        }

        return true;
    }

    @Override
    public boolean add(E element) {
        add(size, element);

        return true;
    }

    @Override
    public void add(int index, E element) {
        if (size + 1 > items.length) {
            increaseCapacity();
        }

        checkIndex(index);

        if (index < size) {
            System.arraycopy(items, index, items, index + 1, size - index);
        }

        items[index] = element;

        ++size;
        ++modCount;
    }

    @Override
    public boolean addAll(int index, Collection<? extends E> collection) {
        checkCollection(collection);

        if (collection.size() == 0) {
            return true;
        }

        ensureCapacity(size + collection.size());

        checkIndex(index);

        System.arraycopy(items, index, items, index + collection.size(), size - index);

        int oldSize = size;

        for (E element : collection) {
            items[index] = element;
            index++;
            size++;
        }

        if (oldSize != size) {
            ++modCount;

            return true;
        }

        return false;
    }

    @Override
    public boolean addAll(Collection<? extends E> collection) {
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

        items[size - 1] = null;
        --size;
        ++modCount;

        return oldData;
    }

    @Override
    public boolean remove(Object o) {
        if (indexOf(o) != -1) {
            remove(indexOf(o));

            return true;
        }

        return false;
    }

    @Override
    public boolean removeAll(Collection<?> collection) {
        checkCollection(collection);

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
        checkCollection(collection);

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
        return Arrays.copyOf(items, size);
    }

    @Override
    public <T> T[] toArray(T[] array) {
        if (array.length < size) {
            //noinspection unchecked
            return (T[]) Arrays.copyOf(items, size, array.getClass());
        }

        int i = 0;
        while (i < size) {
            //noinspection unchecked
            array[i] = (T) items[i];
            i++;
        }

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
                throw new NoSuchElementException("Next element doesn't exist");
            }

            if (currentModCount != modCount) {
                throw new ConcurrentModificationException("The list has changed!");
            }

            ++currentIndex;
            return items[currentIndex];
        }

    }

    @Override
    public String toString() {
        if (size == 0) {
            return "[]";
        }

        StringBuilder sb = new StringBuilder("[");

        for (E e : items) {
            if (e != null) {
                sb.append(e).append(", ");
            }
        }

        sb.replace(sb.length() - 2, sb.length(), "]");

        return sb.toString();
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