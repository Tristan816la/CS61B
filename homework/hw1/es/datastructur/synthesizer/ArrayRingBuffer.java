package es.datastructur.synthesizer;

import java.util.Iterator;

public class ArrayRingBuffer<T> implements BoundedQueue<T> {
    /* Index for the next dequeue or peek. */
    private int first;
    /* Index for the next enqueue. */
    private int last;
    /* Variable for the fillCount. */
    private int fillCount;
    /* Array for storing the buffer data. */
    private T[] rb;

    /**
     * Create a new ArrayRingBuffer with the given capacity.
     */
    public ArrayRingBuffer(int capacity) {
        rb = (T[]) new Object[capacity];
        first = 0;
        last = 0;
        fillCount = 0;
    }

    @Override
    public int capacity() {
        return rb.length;
    }

    @Override
    public int fillCount() {
        return fillCount;
    }

    /**
     * Adds x to the end of the ring buffer. If there is no room, then
     * throw new RuntimeException("Ring buffer overflow").
     */
    @Override
    public void enqueue(T x) {
        if (isFull())
            throw new RuntimeException("Ring buffer overflow");
        rb[last] = x;
        last = (last + 1) % capacity();
        fillCount++;
    }

    /**
     * Dequeue oldest item in the ring buffer. If the buffer is empty, then
     * throw new RuntimeException("ring buffer underflow").
     */
    @Override
    public T dequeue() {
        if (isEmpty())
            throw new RuntimeException("ring buffer underflow");
        T result = rb[first];
        first = (first + 1) % capacity();
        fillCount--;
        return result;
    }

    /**
     * Return oldest item, but don't remove it. If the buffer is empty, then
     * throw new RuntimeException("Ring buffer underflow").
     */

    @Override
    public T peek() {
        if (isEmpty())
            throw new RuntimeException("Ring buffer underflow");
        return rb[first];
    }

    @Override
    public Iterator<T> iterator() {
        return new ArrayRingBufferIterator();
    }

    private class ArrayRingBufferIterator implements Iterator<T> {
        int currentPos;
        int count;

        public ArrayRingBufferIterator() {
            currentPos = first;
            count = 0;
        }

        public boolean hasNext() {
            return count < fillCount;
        }

        public T next() {
            T result = rb[currentPos];
            currentPos = (currentPos + 1) % capacity();
            count++;
            return result;
        }
    }

    @Override
    public boolean equals(Object o) {
        if (o == this)
            return true;
        if (o.getClass() != this.getClass())
            return false;
        ArrayRingBuffer<T> other = (ArrayRingBuffer<T>) o;
        if (other.fillCount() != fillCount())
            return false;
        Iterator<T> thisIt = iterator();
        Iterator<T> otherIt = other.iterator();
        while (thisIt.hasNext() && otherIt.hasNext()) {
            if (thisIt.next() != otherIt.next())
                return false;
        }
        return true;
    }
}
