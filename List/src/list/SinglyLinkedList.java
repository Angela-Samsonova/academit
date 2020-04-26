package list;

public class SinglyLinkedList<E> {
    private ListItem<E> head;
    private int count;

    public SinglyLinkedList(ListItem<E> head, int count) {
        this.head = head;
        this.count = count;
    }

    public ListItem<E> getHead() {
        return head;
    }

    public void setHead(ListItem<E> head) {
        this.head = head;
    }

    public int getCount() {
        return count;
    }

    public void setCount(int count) {
        this.count = count;
    }

    public int getSize() {
        int sizeCount = 0;

        for (ListItem<E> e = head; e != null; e = e.getNext()) {
            sizeCount++;
        }

        return sizeCount;
    }

    public E getFirstItemData() {
        if (head == null) {
            throw new IndexOutOfBoundsException("This list is empty");
        } else {
            return head.getData();
        }
    }

    public E getItemDataByIndex(int index) {
        if (index < 0 || index > count - 1) {
            throw new IllegalArgumentException("Index cannot be < 0 or >= count");
        }

        if (head == null) {
            throw new IndexOutOfBoundsException("This list is empty");
        }

        if (index == 0) {
            return head.getData();
        }

        int tmpIndex = 0;

        for (ListItem<E> e = head; e != null; e = e.getNext()) {
            if (tmpIndex == index) {
                return e.getData();
            }

            tmpIndex++;
        }

        return null;
    }

    public E setItemDataByIndex(E data, int index) {
        if (index < 0 || index > count - 1) {
            throw new IllegalArgumentException("Index cannot be < 0 or >= count");
        }

        if (head == null) {
            throw new IndexOutOfBoundsException("This list is empty");
        }

        if (index == 0) {
            E oldData = head.getData();
            head.setData(data);
            return oldData;
        }

        int tmpIndex = 0;

        for (ListItem<E> e = head; e != null; e = e.getNext()) {
            if (tmpIndex == index) {
                E oldData = e.getData();
                e.setData(data);
                return oldData;
            }

            tmpIndex++;
        }

        return null;
    }

    public E deleteItemByIndex(int index) {
        if (index < 0 || index > count - 1) {
            throw new IllegalArgumentException("Index cannot be < 0 or >= count");
        }

        if (head == null) {
            throw new IndexOutOfBoundsException("This list is empty");
        }

        if (index == 0) {
            return deleteFirstItem();
        }

        int tmpIndex = 0;

        for (ListItem<E> e = head, prev = null; e != null; prev = e, e = e.getNext()) {
            if (tmpIndex == index) {
                E value = e.getData();
                prev.setNext(e.getNext());
                count--;

                return value;
            }

            tmpIndex++;
        }

        return null;
    }

    public void insertAsFirstItem(ListItem<E> e) {
        if (e == null) {
            throw new IllegalArgumentException("List item must be not null");
        }

        e.setNext(head);
        head = e;

        if (getSize() - 1 == count) {
            count++;
        }
    }

    public void insertItemByIndex(ListItem<E> item, int index) {
        if (index < 0 || index > count - 1) {
            throw new IllegalArgumentException("Index cannot be < 0 or >= count");
        }

        if (head == null) {
            throw new IndexOutOfBoundsException("This list is empty");
        }

        if (item == null) {
            throw new IllegalArgumentException("Item must be not null");
        }

        if (index == 0) {
            insertAsFirstItem(item);
        }

        int tmpIndex = 0;

        for (ListItem<E> e = head, prev = null; e != null; prev = e, e = e.getNext()) {
            if (tmpIndex == index) {
                item.setNext(e);
                if (prev != null) {
                    prev.setNext(item);

                    count++;
                }
            }

            tmpIndex++;
        }
    }

    public boolean deleteItemByValue(E data) {
        if (head == null) {
            throw new IndexOutOfBoundsException("This list is empty");
        }

        if (data == null) {
            throw new IllegalArgumentException("Data must be not null");
        }

        for (ListItem<E> e = head, prev = null; e != null; prev = e, e = e.getNext()) {
            if (e.getData() == data) {
                if (prev != null) {
                    prev.setNext(e.getNext());
                    count--;

                    return true;
                }
            }
        }

        return false;
    }

    public E deleteFirstItem() {
        E data = head.getData();
        head = head.getNext();
        count--;

        return data;
    }

    public void revertList() {
        int firstIndex = 0;
        int lastIndex = getSize() - 1;

        while (lastIndex > firstIndex) {
            E temp = getItemDataByIndex(firstIndex);
            setItemDataByIndex(getItemDataByIndex(lastIndex), firstIndex);
            setItemDataByIndex(temp, lastIndex);

            firstIndex++;
            lastIndex--;
        }
    }

    public void copyList(SinglyLinkedList<E> list) {
        for (ListItem<E> e = head, item = list.getHead(); e != null && item != null; e = e.getNext(), item = item.getNext()) {
            e.setData(item.getData());
        }
    }

    public Object[] toArray() {
        Object[] result = new Object[getSize()];
        int i = 0;

        for (ListItem<E> e = head; e != null; e = e.getNext()) {
            result[i++] = e.getData();
        }

        return result;
    }
}