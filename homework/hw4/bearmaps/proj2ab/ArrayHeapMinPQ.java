package bearmaps.proj2ab;

import java.util.*;

public class ArrayHeapMinPQ<T> implements ExtrinsicMinPQ<T> {
    private class Node {
        T value;
        double priority;

        Node(T v, double p) {
            priority = p;
            value = v;
        }
    }

    private ArrayList<Node> pq;
    private HashMap<T, Integer> itemIndex;

    public ArrayHeapMinPQ() {
        pq = new ArrayList<>();
        itemIndex = new HashMap<>();
    }

    /* Adds an item with the given priority value. Throws an
     * IllegalArgumentExceptionb if item is already present.
     * You may assume that item is never null. */
    @Override
    public void add(T item, double priority) {
        if (contains(item)) throw new IllegalArgumentException("cannot add item that is present");
        pq.add(new Node(item, priority));
        itemIndex.put(item, pq.size() - 1);
        swim(pq.size() - 1);
    }


    /* Returns true if the PQ contains the given item. */
    @Override
    public boolean contains(T item) {
        if (pq.isEmpty())
            return false;
        return itemIndex.containsKey(item);
    }

    /* Returns the minimum item. Throws NoSuchElementException if the PQ is empty. */
    @Override
    public T getSmallest() {
        if (pq.isEmpty()) throw new NoSuchElementException();
        return pq.get(0).value;
    }

    /* Removes and returns the minimum item. Throws NoSuchElementException if the PQ is empty. */
    @Override
    public T removeSmallest() {
        if (pq.isEmpty()) throw new NoSuchElementException();
        T min = pq.get(0).value;
        swap(pq.get(size() - 1), pq.get(0));
        pq.remove(size() - 1);
        sink(0);
        itemIndex.remove(min);
        return min;
    }


    /* Returns the number of items in the PQ. */
    @Override
    public int size() {
        return pq.size();
    }

    /* Changes the priority of the given item. Throws NoSuchElementException if the item
     * doesn't exist. */
    @Override
    public void changePriority(T item, double priority) {
        if (pq.isEmpty() || !contains(item)) throw new NoSuchElementException();
        int index = itemIndex.get(item);
        double oldPriority = pq.get(index).priority;
        pq.get(index).priority = priority;
        if (priority < oldPriority) {
            swim(index);
        } else {
            sink(index);
        }
    }

    /**
     * Helper Methods
     */
    private void swim(int k) {
        while (k > 0 && smaller(pq.get(k), pq.get(parent(k)))) {
            swap(pq.get(k), pq.get(parent(k)));
            k = parent(k);
        }
    }

    private int parent(int k) {
        return (k - 1) / 2;
    }

    private int leftChild(int k) {
        return 2 * k + 1;
    }

    private int rightChild(int k) {
        return 2 * k + 2;
    }

    private void swap(Node a, Node b) {
        int aIndex = itemIndex.get(a.value);
        int bIndex = itemIndex.get(b.value);
        Collections.swap(pq, aIndex, bIndex);
        itemIndex.put(a.value, bIndex);
        itemIndex.put(b.value, aIndex);
    }


    private boolean smaller(Node a, Node b) {
        return a.priority < b.priority;
    }

    private void sink(int a) {
        while (leftChild(a) < pq.size() - 1) {
            if (smaller(pq.get(leftChild(a)), pq.get(a))) {
                swap(pq.get(a), pq.get(leftChild(a)));
                a = leftChild(a);
            } else if (smaller(pq.get(rightChild(a)), pq.get(a))) {
                swap(pq.get(a), pq.get(rightChild(a)));
                a = rightChild(a);
            } else {
                return;
            }
        }
    }
}
