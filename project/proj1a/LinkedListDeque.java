public class LinkedListDeque<T> {
    private class Node {
        Node() {
            next = null;
            value = null;
        }

        Node(T val, Node ne) {
            value = val;
            next = ne;
        }

        Node prev;
        Node next;
        T value;
    }

    private Node last;
    private Node sentinel;
    private int size;

    public LinkedListDeque() {
        sentinel = new Node();
        size = 0;
        last = sentinel;
    }

    public LinkedListDeque(LinkedListDeque other) {
        sentinel = new Node();
        Node head = other.sentinel.next;
        Node p = sentinel;
        while (head != null) {
            Node copy = new Node(head.value, null);
            p.next = copy;
            p = copy;
            head = head.next;
        }
        size = other.size;
        last = p;
    }

    public void addFirst(T item) {
        Node addItem = new Node(item, null);
        addItem.next = sentinel.next;
        addItem.prev = sentinel;
        sentinel.next = addItem;
        if (last == sentinel) { // An edge case where addFirst is equal to addLast
            last = addItem;
        }
        size++;
    }

    public void addLast(T item) {
        Node addItem = new Node(item, null);
        last.next = addItem;
        addItem.prev = last;
        last = addItem;
        size++;
    }

    public boolean isEmpty() {
        return size == 0;
    }

    public int size() {
        return size;
    }

    public void printDeque() {
        if (sentinel.next == null) return;
        for (Node i = sentinel.next; i != null; i = i.next) {
            System.out.print(i.value + " ");
        }
        System.out.println();
    }

    public T removeFirst() {
        if (sentinel.next == null)
            return null;
        T result = sentinel.next.value;
        if (sentinel.next.next != null) {
            sentinel.next.next.prev = sentinel;
            sentinel.next = sentinel.next.next;
        } else {
            sentinel.next = null;
        }
        size--;
        return result;
    }

    public T removeLast() {
        if (sentinel.next == null)
            return null;
        if (last == sentinel.next) // The case when the last Node is the first node
            return removeFirst();
        T result = last.value;
        last = last.prev;
        last.next = null;
        size--;
        return result;
    }

    public T get(int index) {
        int i = 0;
        if (index > size - 1)
            return null;
        Node temp = sentinel.next;
        for (; i <= index; i++) {
            temp = temp.next;
        }
        return temp.value;
    }

    private T getHelper(Node a, int index) {
        if (a == null)
            return null;
        if (index == 0)
            return a.value;
        return getHelper(a.next, index - 1);
    }

    public T getRecursive(int index) {
        if (index > size - 1)
            return null;
        return getHelper(sentinel.next, index);
    }
}