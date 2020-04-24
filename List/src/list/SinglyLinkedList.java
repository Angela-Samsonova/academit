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

    //получение размера списка
    public int getSize(SinglyLinkedList<E> list) {
        return list.getCount();
    }

    //получение текущего размера списка
    public int getActualSize() {
        int count = 0;

        if (head != null) {
            count++;

            while (head.getNext() != null) {
                count++;
                head = head.getNext();
            }
        } else {
            return 0;
        }

        return count;
    }

    // получение значение первого элемента
    public E getFirstItemData() {
        if (head == null) {
            throw new IndexOutOfBoundsException("This list is empty");
        } else {
            return head.getData();
        }
    }

// получение/изменение значения по указанному индексу.
//  Изменение значения по индексу пусть выдает старое значение.
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
            tmpIndex++;

            if (tmpIndex == index) {
                return e.getData();
            }
        }

        return null;
    }

    public E setElementDataByIndex(E data, int index) {
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
            tmpIndex++;

            if (tmpIndex == index) {
                E oldData = e.getData();
                e.setData(data);
                return oldData;
            }
        }

        return null;
    }

    // 	удаление элемента по индексу, пусть выдает значение элемента

    public E deleteElement(int index) {
        //return head.getData();
    }

    // вставка элемента в начало
    public void insertAsFirstElement(ListItem<E> e) {
        e.setNext(head);
        head = e;
    }

    // вставка элемента по индексу

    public void insertElement(ListItem<E> e, int index) {

    }

    //  удаление узла по значению, пусть выдает true, если элемент был удален

    public boolean deleteElementByValue(E data) {

    }

    // удаление первого элемента, пусть выдает значение элемента
    public E deleteFirstElement() {
        ListItem<E> e = head;
        head = head.getNext();
        return e.getData();
    }
    // проход по списку   for (ListItem<Integer> p = head; p != null; p = p.getNext()) { System.out.println(p.getData()); }


// разворот списка за линейное время


    // копирование списка
    public void copy(SinglyLinkedList<E> list) {
        count = list.count;
        head = list.head;

        for (ListItem<E> e = head; e != null; e = e.getNext()) {


            if (tmpIndex == index) {
                E oldData = e.getData();
                e.setData(data);
                return oldData;
            }


        }

    }

