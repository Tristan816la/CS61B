/**
 * LinkedListDeque
 */

public class LinkedListDeque<T> implements Deque<T> {

    private class Node {
        private T item;
        private Node prev;
        private Node next;

        private Node(T x, Node p, Node n) {
            item = x;
            prev = p;
            next = n;
        }
    }

    /**
     *  The first item (if it exists) in the deque is the sentinel.next
     */
    private Node sentinel;
    private int size;

    /**
     * Create an empty deque
     */
    public LinkedListDeque() {
        sentinel = new Node(null, null, null);
        sentinel.prev = sentinel;
        sentinel.next = sentinel;
        size = 0;
    }

    /**
     * Return the number of items in the deque
     */
    @Override
    public int size() {
        return size;
    }

    /**
     * Add an item of type T to the front of the deque
     */
    @Override
    public void addFirst(T item) {
        sentinel.next = new Node(item, sentinel, sentinel.next);
        sentinel.next.next.prev = sentinel.next;
        size++;
    }

    /**
     * Add an item of type T to the back of the deque
     */
    @Override
    public void addLast(T item) {
        sentinel.prev = new Node(item, sentinel.prev, sentinel);
        sentinel.prev.prev.next = sentinel.prev;
        size++;
    }

    /**
     * Remove and return the item at the front of the deque
     * If no such item exists, return null
     */
    @Override
    public T removeFirst() {
        T toRemove = sentinel.next.item;
        sentinel.next.next.prev = sentinel;
        sentinel.next = sentinel.next.next;
        if (!isEmpty()) {
            size--;
        }
        return toRemove;

    }

    /**
     * Remove and return the item at the back of the deque
     * If no such item exists, return null
     */
    @Override
    public T removeLast() {
        T toRemove = sentinel.prev.item;
        sentinel.prev.prev.next = sentinel;
        sentinel.prev = sentinel.prev.prev;
        if (!isEmpty()) {
            size--;
        }
        return toRemove;
    }

    /**
     * Print the items in the deque from first to last, separated by a space
     * Once all the items have been printed, print out a new line
     */
    @Override
    public void printDeque() {
        Node toPrint = sentinel.next;
        for (int i = 0; i < size; i++) {
            System.out.print(toPrint.item + " ");
            toPrint = toPrint.next;
        }
        System.out.println();
    }

    /**
     * Get the item at the given index, where 0 is the front,
     * 1 is the next item, and so forth. I fno such item exists,
     * return null. Must not alter the deque
     */
    @Override
    public T get(int index) {
        Node toGet = sentinel.next;
        for (int i = 0; i < index; i++) {
            toGet = toGet.next;
        }
        return toGet.item;
    }

    /**
     * Same as get, but uses recursion
     * First, need a private helper method
     */
    private T getRecursive(int index, Node curr) {

        if (index == 0) {
            return curr.item;
        }
        return getRecursive(index - 1, curr.next);
    }

    public T getRecursive(int index) {
        return getRecursive(index, sentinel.next);
    }

    /**
     * Create a deep copy of other
     */
    public LinkedListDeque(LinkedListDeque other) {
        sentinel = new Node(null, null, null);
        sentinel.prev = sentinel;
        sentinel.next = sentinel;
        size = 0;

        for (int i = 0; i < other.size(); i++) {
            addLast((T) other.get(i)); // (T) is cast, since type of other is unknown
        }
    }

}